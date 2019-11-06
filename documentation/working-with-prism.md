## Working with PRISM


#### Running the prism-adapter and dependencies
1. Run through root gradlew
    1. `./gradlew runPrismAdapter`
    1. `./gradlew runPrismMock`
1. Run manually
    1. `./gradlew components:prism-adapter:bootRun`
    1. `cd ./prism-mock`
    1. `java -jar wiremock-standalone-2.18.0.jar --port=9999`

#### Communicating with PRISM
Our connectcion to  PRISM is dependent on a whitelist outside of our control. We have the following IP addresses whitelisted:
* 63.238.47.2 (Polaris Alpha network)
* 50.232.79.2 (Pivotal network)
* 50.58.48.214 (Pivotal network)
* 96.127.111.218 (Prism Proxy EC2 instance on our AWS GovCloud account)
* 52.61.176.170 (PCF NAT EC2 instance on our AWS GovCloud account)

If you are attempting to access PRISM from an IP listed above, you will be granted access. To access PRISM from a network
outside of those IP addresses, you can use the Prism Proxy.

#### Use the Prism Proxy
Shell into the Prism Proxy EC2 instance on GovCloud (ask a fellow developer for the Stargate-PRISM-GovCloud.pem file):

`ssh -i ~/.ssh/Stargate-PRISM-GovCloud.pem ubuntu@96.127.111.218`

Navigate to the prism-proxy directory:

`cd ~/workspace/stargate/prism-proxy`

Start the proxy in the background:

`pm2 start app.js -- --http`

You can now access PRISM by visiting http://96.127.111.218:5050

#### Importing PRISM's self-signed certificate into Java trust store
You may need to interact with PRISM using a variety of Java clients. PRISM's certificate is self-signed and presents
a host name that the site is not served from. To import the certificate into your java trust store, perform the following
steps:
* Export the certificate from a web browser (e.g. with Firefox https://www.sslsupportdesk.com/export-certificate-firefox/)
* Locate your cacerts file. It should be at: `$(/usr/libexec/java_home)/jre/lib/security/cacerts`
* Convert the exported certificate from PEM format to DER format: `openssl x509 -outform der -in certificate.pem -out certificate.der`
* Import the DER certificate into the Java trust store: `keytool -import -alias prism-cert -keystore cacerts -file certificate.der`

#### Retrieving SOAP xml response
Updating prism-mock may be required at times if new or different data from a SOAP request is required.
1. Run prism-proxy app
    1. `cd prism-proxy`
    1. `yarn install`
    1. `node app.js --http`
1. Run prism-adapter and point to the prism proxy (http://localhost:5050). Remember to make sure you are not using prism-mock.
1. Run `sudo tcpdump -s 0 -i any -A tcp port 5050` - this will send the SOAP response to the screen.