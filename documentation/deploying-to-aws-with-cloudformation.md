# Deploying IMM to AWS with CloudFormation

## Create the AWS infrastructure
Install the AWS CLI: `https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html`

Run `aws configure`. Provide your access key, secret access key, and region.

#### S3
Create the `stargate-backups-cloudformation` S3 bucket: `aws s3api create-bucket --bucket stargate-backups-cloudformation`

Upload the Redis DB backup file: `aws s3 cp dump.rdb s3://stargate-backups-cloudformation/`

Get your account's canonical id: `aws s3api list-buckets --output text`

Get the canonical id for your region, see this document: `https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/backups-seeding-redis.html#backups-seeding-redis-grant-access`

Update the permissions on the Redis Db backup file:
```
aws s3api put-object-acl --bucket stargate-backups-cloudformation --key dump.rdb --grant-read id="region canonical id" --grant-read-acp id="region canonical id" --grant-full-control id="your account's canonical id"
```

## Create the stack/run the template

Before attempting this operation, there are a few things to keep in mind.

1. Clone the source repository: `https://bitbucket.di2e.net/scm/stargate/stargate.git`
 The directions assume you are in the root directory of this repository.
1. You'll need the credentials for this docker repository: `docker-di2e.di2e.net`.
1. You'll need to know which version of Stargate you are trying to deploy. Latest is currently `0.0.3`.

#### Creating a stack using the AWS CLI

You'll need to run the `aws cloudformation create-stack` command. Here is an example:

```
aws cloudformation create-stack --stack-name stargate-cloudformation --template-body file://cloudformation/cloudformation.yml --parameters ParameterKey=stargateDbUser,ParameterValue=<SOME_USERNAME> ParameterKey=uaaDbUser,ParameterValue=<ANOTHER_USERNAME> ParameterKey=stargateVersion,ParameterValue=0.0.3 ParameterKey=dockerHost,ParameterValue=docker-di2e.di2e.net ParameterKey=dockerUsername,ParameterValue=<DOCKER_USERNAME> ParameterKey=dockerPassword,ParameterValue=<DOCKER_PASSOWRD> ParameterKey=stargateDbPassword,ParameterValue=<STARGATE_DB_PASSWORD> ParameterKey=uaaDbPassword,ParameterValue=<UAA_DB_PASSWORD> ParameterKey=amiId,ParameterValue=<AMI_ID>
```

SOME_USERNAME: create a username
ANOTHER_USERNAME: create another unique username
DOCKER_USERNAME: your docker credentials
DOCKER_PASSWORD: your docker credentials
STARGATE_DB_PASSWORD: provide a db password
STARGATE_UAA_PASSWORD: provide a db password
AMI_ID: the Amazon machine image ID to use for the EC2 instances (we tested with ami-009d6802948d06e52)

 The DB Passwords must contain only alphanumeric characters and be between 8 and 41 characters in length.
 The DB Usernames must contain only alphanumeric characters.

This will take some time. You can check status by running this command:  `aws cloudformation describe-stacks --stack-name stargate-cloudformation --query 'Stacks[0].StackStatus`.
It will say 'CREATE_IN_PROGRESS' while working, and 'CREATE_COMPLETE' when ready.


#### Creating a stack using the AWS Web Console

1. Navigate [here](https://console.aws.amazon.com/cloudformation) and login if necessary
1. Click on 'Create Stack'
1. Click on 'Upload a template file'
1. Click on 'Choose File' and use the file browser to choose `clouformation/cloudformation.yml`
1. Click on 'Next' and fill in the inputs.
  1. Stack name: can be whatever you want
  1. stargateDbUser: create a username
  1. uaaDbUser: create another username
  1. stargateVersion: `0.0.3`
  1. dockerHost: `docker-di2e.di2e.net`
  1. dockerUsername: your docker credentials
  1. dockerPassword: your docker credentials
  1. stargateDbPassword: provide a db password
  1. uaaDbPassword: provide a db password

 The DB Passwords must contain only alphanumeric characters and be between 8 and 41   characters in length.
1. Click on 'Next' to get to the options page.
1. You can ignore all options and just click 'Next' again to get to the review page.
1. Click on 'Create', and you will be automatically taken to the Stack list.
1. To view progress on your new stack, click on the stack name in the list.
1. Once things are ready, you should see the 'Stack status' display shift from 'CREATE_IN_PROGRESS' to 'CREATE_COMPLETE'.
1. To test it out, go to the [EC2 console](https://console.aws.amazon.com/ec2/v2) and find the public DNS for the app called 'stargate-app-cloudformation'. Navigate to that host on port 8081. Example: <PUBLIC DNS(IPv4)>:8081

## Initialize UAA

**NOTE** You will need to configure the UAA users the first time the application is launched. 
On your local machine, run the following command:

`./scripts/uaa-init-cloudformation.sh`

**NOTE** You can verify the application is launched running the following command:

`aws ec2 describe-instances --filters "Name=tag:Name,Values=stargate-app-cloudformation" "Name=instance-state-name,Values=running" --query 'Reservations[0].Instances[0].PublicDnsName' --output text`

Verify that the application is launched by visiting `http://<hostname here>:8081` in your browser. Log in to the application with username: `user1` and password: `user1`.

You may need to install the certificate to access the mapping portion of our application. See the Map of the World section of the README for information.

You're done!
