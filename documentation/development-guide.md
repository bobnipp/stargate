## Development Guide

#### Setting up a developer machine
1. Install basic dependencies (note: these are all installed on a Mac by running the Pivotal workstation setup -- https://github.com/pivotal-legacy/workstation-setup)
    1. java 8
    1. node
    1. IntelliJ
1. Install Docker
1. `npm install -g yarn`
1. `yarn global add @angular/cli`
1. `sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"`
1. Install wget
    1. OSX: `brew install wget`
    1. Windows: 'https://eternallybored.org/misc/wget/', `https://gist.github.com/evanwill/0207876c3243bbb6863e65ec5dc3f058`
1. Add `alias git=git-together` to `~/.zshrc`
1. Set up the database:
    1. `brew install mysql@5.7` (note: if you are already on a higher version and are experiencing issues, try the steps found [here](https://stackoverflow.com/questions/9624774/after-mysql-install-via-brew-i-get-the-error-the-server-quit-without-updating/36156848#36156848))
    1. Start the server and log in: `mysql.server start && mysql -uroot`
    1. You may need to run the following command to make the `mysql` command accessible: `echo 'export PATH="/usr/local/opt/mysql@5.7/bin:$PATH"' >> ~/.zshrc && source ~/.zshrc`
    1. Create the stargate database: `create database stargate;` and the stargate test database: `create database stargate_test;`
1. Setup ruby environment
    1. `brew install rbenv`
    1. `rbenv install 2.5.1`
    1. `rbenv global 2.5.1`
    1. `echo 'eval "$(rbenv init -)"' >> ~/.zshrc`
    1. `source ~/.zshrc`
    1. `gem install bundler`
1. Install `cf mysql` plugin: `cf install-plugin -r "CF-Community" mysql-plugin` 
1. Install lombok plugin in IntelliJ
    1. From IntelliJ, `cmd`+`,`
    1. From Plugins, choose Browse Repositories
    1. Search for Lombok
    1. Install Lombok Plugin
    1. Restart IntelliJ
    1. `cmd`+`,`
    1. Search for `annotation processing`
    1. Check `Enable Annotation Processing`
1. Install tmux: `brew install tmux`
1. Install and start mailcatcher: `gem install mailcatcher && mailcatcher`
1. Install Redis
    1. `brew update && brew install redis`
    1. open `/usr/local/etc/redis.conf`
    1. add `requirepass password` somewhere in the file
    1. start redis `brew services start redis`
    
#### Environment Variables
1. `CF_PASSWORD`: Password to our service account on Cloud Foundry for pushing
1. `EMAIL_PASSWORD`: Password to our project Gmail account, used by Spring Integration
1. `JIRA_USERNAME`: Username for Jira instance you're running against
1. `JIRA_PASSWORD`: Password for Jira instance you're running against
1. `JIRA_PROJECT_NAME`: The unique project you run against on your dev machine
1. `JIRA_PROJECT_KEY`: That project's key
1. `GOOGLE_MAPS_API_KEY`: Our API key for Google maps
1. `NEXUS_USERNAME`: Username with permission to download dependencies from Di2e central
1. `NEXUS_PASSWORD`: Password of user with permission to download dependencies from Di2e central
1. `PRISM_USE_MOCK`: Optional - Set this environment variable to either use the mock host or an actual PRISM instance - defaults to true
1. `PRISM_HOST`: Optional - hostname of PRISM or the proxy server that connects to it - defaults to ec2 instance
1. `PRISM_SOAP_USERNAME`: Username for our instance of PRISM SOAP API
1. `PRISM_SOAP_PASSWORD`: Password for the username configured with PRISM SOAP API
1. `PRISM_UI_USERNAME`: Username for our instance of PRISM UI API
1. `PRISM_UI_PASSWORD`: Password for the username configured with PRISM UI API

**NOTE:** It is important that the `PRISM_SOAP_USERNAME` and `PRISM_UI_USERNAME` accounts differ in order to prevent
simultaneous session errors in PRISM. 

#### Building the app
1. `./gradlew`

#### Running the app locally
1. `./scripts/start`
1. Once the `uaa` has fully started, you simply need to press `<Enter>` in the third tab. This will run the following command for you:
  1. `./scripts/uaa-init-local.sh; ./gradlew bootrun`

#### Running tests
1. UI Unit tests: `./gradlew components:ui:unit`
1. UI Acceptance tests: `./gradlew acceptance:test`
1. API Unit tests: `./gradlew components:api:test`
1. All tests: `./gradlew test`



#### Tagging new Docker images for Jenkins tests
1. Update your Dockerfile to change whatever you need
1. `docker build --build-arg nexus_username=$NEXUS_USERNAME --build-arg nexus_password=$NEXUS_PASSWORD -t docker-di2e.di2e.net/stargate:<new tag #> .`
1. `docker run -it <image id> /bin/bash` to get into the container so you can verify things work
1. Before uploading, make sure to log in
    1. `docker login docker-di2e.di2e.net`
    1. Enter your DI2E credentials when prompted
1. `docker push docker-di2e.di2e.net/stargate:<new tag #>`
1. Update Jenkinsfile to use the new tag: `agent { docker { image 'stargate:<new tag #>' ...`
1. Better yet, figure out how to publish / use the `latest` tag so we don't have to update the Jenkinsfile when we publish new images

**NOTE:** if any packages fail with a 404, try running with the --no-cache option as they may be out of date

#### Sonarqube
1. `./gradlew sonarqube -Dsonar.host.url=https://sonarqube.di2e.net -Dsonar.login=c22f28ea28f7c2b1f0dfac15d39cc9ef007b28ea --debug`
1. _NOTE_: Currently this does not succeed, as our DI2E users don't have access to create reports on Sonarqube

_NOTE_ - Omit the `./` if working on a PC

#### Generating Migrations
1. Migrations should be prefixed with `V{timestamp}__`, there's a script to do that:
  1. `./gradlew generateMigration -PmigrationName=migration_name`
  

#### Caching
To warm the cache:

1. Open Postman (or whatever http client you want) 
1. Make a POST request to `http://localhost:8082/cache` (or whatever url you're running prism adapter at)

#### S3
1. Install localstack - `pip install --user localstack`
1. You may have to update your path to include the python path (eg /Users/<user>/Library/Python/2.7/bin)
1. `localstack start`