## UAA

#### Running UAA locally
**Note:** if you are using `./scripts/start` to run the app there should be no need to ever do this)
1. `cd ~/workspace`
1. `git clone git://github.com/cloudfoundry/uaa.git`
1. `gem install cf-uaac`
1. `cd ~/workspace/stargate`
1. `./scripts/createkeys.sh`
1. `cd ~/workspace/uaa`
1. `UAA_CONFIG_URL=file://$PWD/../stargate/uaa_config.yml ./gradlew run` (ignore cert warnings)
1. In a new tab: `cd ~/workspace/stargate`
1. `./scripts/uaa-init-local.sh` - This step configures some default users / groups in memory, and therefore needs to be run each time you boot uaa
1. `SERVER_PORT=8081 ./gradlew bootRun`
1. Visit localhost:8081 in a browser
1. When redirected to the uaa login page, login as user1/user1

#### Deploying UAA to Cloud Foundry
_NOTE_ - This should rarely need to be done
1. `cd ~/workspace`
1. `git clone git://github.com/cloudfoundry/uaa.git`
1. `cd uaa`
1. `git checkout tags/4.14.0`
1. OPTIONAL `./gradlew manifests -Dapp=stargate-uaa -Dapp-domain=cfapps.io` (only necessary if you need to re-generate ua-cf-application.yml, which lives in the root directory of ~/workspace/stargate) 
1. If you did the step above, remember to regenerate jwt keys (with createkeys.sh, see Running UAA locally) and copy them into the `jwt` key underneath the `UAA_CONFIG_YAML: |` key in ua-cf-application.yml
    1. Add the configuration back in to the ua-cf-application.yml, make sure it is under the `UAA_CONFIG_YAML: |` key:
        ```
        zones:
            internal:
                hostnames:
                    - stargate-uaa.cfapps.io
        database:
            # url: <url starting with jdbc here>
            # username: <db username>
            # password: <db password>
            maxactive: 15
            maxidle: 5
            minidle: 1
        
        LOGIN_SECRET: loginsecret
        issuer:
        uri: http://stargate-uaa.cfapps.io
        
        encryption:
            active_key_label: stargate
            encryption_keys:
                - label: stargate
                  passphrase: stargatePasswordSHA512
        
        uaa:
            clients:
                admin:
                    id: admin
                    secret: adminsecret
                    authorized-grant-types: client_credentials
                    authorities: clients.read,clients.write,clients.secret,uaa.admin,scim.read,scim.write,password.write
        ```
1. `./gradlew cloudfoundry-identity-uaa:war` - this will generate a deployable .war file at `~/workspace/uaa/uaa/build/libs`
1. `cd ~workspace/stargate`
1. Make sure you are authenticated with cf cli
1. `cf push -f uaa-cf-application.yml`
1. Initialize uaa - `./scripts/uaa-init-cf-gov-cloud.sh`

