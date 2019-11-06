## Map of the World
We are utilizing a beta version of Map of the World for our mapping functionality. This version
of the API requires permission to use. Any user of our application will need to have a 
certificate installed on their machine that their browser will provide to the Map of the 
World API.

### Gaining access to Map of the World

#### Contacting MoW developers

Contact Adam Lloyd (adam.j.lloyd@lmco.com) to get your Map of the World certificate.

#### Importing existing MoW certificates

You will need to download the certificate for Map of the World from here: https://drive.google.com/drive/folders/14Vd8tQsviu_JsdeurkA8OPs7hSQwEAeg?usp=sharing

###### OSX
1. Open Keychain Access
1. Select Login in the Keychains list
1. Click File -> Import Items...
1. Select the .pfx file to import
1. Enter the certificate password when prompted

###### Windows - Chrome
1. Click on the Options button (three dots) and select Settings
1. In the search bar at the top, type in `manage certificates`
1. Click on the highlighted option `Manage certificates` (you may have to scroll to find the option)
1. Click Import
1. Navigate through the Certificate Import Wizard, selecting your `.pfx` file when prompted.
1. Enter the password `password` when prompted
1. Restart the browser after importing the certificate

###### Windows - IE 11
1. Click on Tools and then select Internet Options
1. Click on the Content tab and then click the Certificates button
1. Click Import
1. Navigate through the Certificate Import Wizard, selecting your `.pfx` file when prompted.
1. Enter the password `password` when prompted
1. Restart the browser after importing the certificate

###### Windows - Firefox
1. Click on the Options button (three dots) and select Options
1. Click on Privacy & Security
1. Scroll to the bottom of the screen and click on the View Certificates button
1. Ensure that the `Your Certificates` tab is selected
1. Click Import
1. Select your `.pfx` file
1. Enter the password `password` when prompted
1. Restart the browser after importing the certificate

#### Accessing API documentation with Chrome
1. Make sure you completely close and restart Chrome after following the steps above
1. Navigate to (https://mapbeta.gvslabs.com/api/)
1. Select PKI Certificate from the login options if you don't get the certificate popup
1. Select the certificate you want to use from the popop
1. You'll have to enter an administrator password once or twice
1. Success! (hopefully)

#### Enabling Chrome to automatically select your certificate

This is an essential step for enabling our acceptance tests around mapping. Create a file called `com.google.Chrome.plist`
in the `/Library/Preferences` directory and add the following contents:
```
<plist version="1.0″>
<dict>
  <key>AutoSelectCertificateForUrls</key>
   <array>
     <string>{"pattern":"[*.]gxiaccess.gxaccess.com[*.}","filter":{"ISSUER":{"CN":"GX Test Root CA"}}}</string>
   </array>
</dict>
</plist>
```

#### Useful documentation
Note that accessing the MoW documentation requires a valid certificate.

https://mapbeta.gvslabs.com/api/examples/jsexamples/index.html
https://mapbeta.gvslabs.com/api/developer.html (Use this link to find the domain for the MoW API for your particular environment classification)