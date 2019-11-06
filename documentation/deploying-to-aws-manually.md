# Deploying IMM to AWS

## Create the AWS infrastructure
Install the AWS CLI: `https://docs.aws.amazon.com/cli/latest/userguide/cli-chap-install.html`

Run `aws configure`. Provide your access key, secret access key, and region.

#### S3
Create the `stargate-backups` S3 bucket: `aws s3api create-bucket --bucket stargate-backups`

Upload the Redis DB backup file: `aws s3 cp dump.rdb s3://stargate-backups/`

Get your account's canonical id: `aws s3api list-buckets --output text | grep OWNER`

Get the canonical id for your region, see this document: `https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/backups-seeding-redis.html#backups-seeding-redis-grant-access`

Update the permissions on the Redis Db backup file, substituting values where necessary: 
```
aws s3api put-object-acl --bucket stargate-backups --key dump.rdb --grant-read id="region canonical id" --grant-read-acp id="region canonical id" --grant-full-control id="your account's canonical id"
```

#### ElastiCache
Create ElastiCache Redis Cluster:
```
aws elasticache create-cache-cluster --cache-cluster-id prism-cache --cache-node-type cache.t2.micro --engine redis --engine-version 5.0.0 --num-cache-nodes 1 --cache-parameter-group default.redis5.0 --snapshot-arns arn:aws:s3:::stargate-backups/dump.rdb
```

#### RDS
Create RDS MySQL Database for UAA:
```
aws rds create-db-instance --db-instance-identifier uaa-db --db-name uaa_db --db-instance-class db.t2.micro --engine MySQL --engine-version 5.7.22 --allocated-storage 20 --master-username <pick a username> --master-user-password <pick a password>
```

Create RDS MySQL Database for IMM:

```
aws rds create-db-instance --db-instance-identifier stargate-db --db-name stargate_db --db-instance-class db.t2.micro --engine MySQL --engine-version 5.7.22 --allocated-storage 20 --master-username <pick a username> --master-user-password <pick a password>
```

Note the public hostnames of the databases for later.

#### EC2

Get the VPC id: `aws ec2 describe-vpcs --filters "Name=isDefault, Values=true" --query 'Vpcs[0].VpcId'`

Create a security group. Note the Group id for the following step: 

`aws ec2 create-security-group --group-name stargate-sg --description "Security Group rules needed for Stargate" --vpc-id <VPC id>`

Add ingress rules to the security group:
```
aws ec2 authorize-security-group-ingress --group-id <Group id> --protocol tcp --port 22 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id <Group id> --protocol tcp --port 8080 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id <Group id> --protocol tcp --port 8081 --cidr 0.0.0.0/0
aws ec2 authorize-security-group-ingress --group-id <Group id> --protocol tcp --port 8082 --cidr 0.0.0.0/0
```

Validate that the rules were created correctly by running `aws ec2 describe-security-groups --group-ids <GroupId>`

Create a key pair:

```
aws ec2 create-key-pair --key-name stargateKey --query 'KeyMaterial' --output text > stargateKey.pem
chmod 400 stargateKey.pem
```

Create the EC2 instance and note the Instance id for the following step:

`aws ec2 run-instances --image-id ami-009d6802948d06e52 --count 1 --instance-type t2.medium --key-name stargateKey --security-group-ids <GroupId>`

Create a name tag for the EC2 instance:

`aws ec2 create-tags --resources <Instance id> --tags Key=Name,Value=stargate-app`

Wait for the EC2 instance to launch and initialize before proceeding.

## Prepare the EC2 instance

Get the Public DNS (IPv4) of the EC2 instance: 

`aws ec2 describe-instances --instance-ids <InstanceId> --query 'Reservations[].Instances[].PublicDnsName'`

Transfer the docker-compose file to the EC2 instance:

`scp -i stargateKey.pem docker-compose.yml ec2-user@<Public DNS>:/home/ec2-user`

SSH into the EC2 instance:

`ssh -i stargateKey.pem ec2-user@<Public DNS>`

Install and run docker with the following commands:

```
sudo amazon-linux-extras install docker
sudo service docker start
sudo usermod -a -G docker ec2-user
```

After running, exit the ssh session and re-establish the connection, then install docker-compose:

```
sudo curl -L https://github.com/docker/compose/releases/download/1.20.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

Log in to Di2e docker registry:

`docker login https://docker-di2e.di2e.net`

Pull down IMM images from Di2e docker registry:

```
docker pull docker-di2e.di2e.net/stargate-api:0.0.3
docker pull docker-di2e.di2e.net/stargate-prism-adapter:0.0.3
docker pull docker-di2e.di2e.net/stargate-aws-uaa:0.0.3
```

Set the following must-have environment variables in `~/.bash_profile`:

```
export EC2_HOST=<Public DNS>
export UAA_JDBC_URL=<uaa rds connection string>` #e.g. `"jdbc:mysql://<db_host>:3306/uaa_db"`
export UAA_DB_USERNAME=<uaa rds username>
export UAA_DB_PASSWORD=<uaa rds password>
export PRISM_CACHE_HOST=<prism elasticache host>
export PRISM_CACHE_PORT=<prism elasticache port>
export STARGATE_JDBC_URL=<stargate rds connection string>` #e.g. `"jdbc:mysql://stargate-db.cvhodq9sezci.us-east-1.rds.amazonaws.com:3306/stargate_db?serverTimezone=UTC&verifyServerCertificate=false&useSSL=true"`
export STARGATE_DB_USERNAME=<stargate rds username>
export STARGATE_DB_PASSWORD=<stargate rds password>
```

You may also want to set the following variables.

**Map of the World:**

```
export MOW_DOMAIN=<Map of the World domain for your environment>
```

Note that the MOW_DOMAIN variable defaults to `mapbeta.gvslabs.com` which will work on unclassified. See https://mapbeta.gvslabs.com/api/developer.html for domains for other environments.

**PRISM:**

Once you have achieved access to PRISM, you can set the following variables. These are not necessary to get the application running.  
We have provided sample PRISM data in the dump.rdb file that was uploaded earlier. See [Prism Data in AWS](prism-data-in-aws.md) for more information.

```
export PRISM_HOST=<prism host url> #e.g. https://ec2-96-127-47-237.us-gov-west-1.compute.amazonaws.com
export PRISM_USE_MOCK=false # Don't worry about what this means...just set it to false
export PRISM_SOAP_USERNAME=<username for the Prism SOAP API>
export PRISM_SOAP_PASSWORD=<password for the Prism SOAP API>
export PRISM_UI_USERNAME=<username for the Prism UI>
export PRISM_UI_USERNAME=<password for the Prism UI>

```

Continuing the installation...

You can retrieve the JDBC hostnames by running:

```
aws rds describe-db-instances --db-instance-identifier stargate-db --query 'DBInstances[0].Endpoint.Address' --output text
aws rds describe-db-instances --db-instance-identifier uaa-db --query 'DBInstances[0].Endpoint.Address' --output text
```

You can retrieve the Prism Cache hostname by running:

```
aws elasticache describe-cache-clusters --cache-cluster-id prism-cache --show-cache-node-info --query 'CacheClusters[].CacheNodes[].Endpoint.Address'
```

Apply the environment variables to the environment: `source ~/.bash_profile`

## Running the application

Run the application! `docker-compose up -d`

This will start three services: API, Prism Adapter, and UAA. It may take several minutes for all
of them to launch -- UAA in particular.

You can follow the status of the deployment with `docker logs <container name or id> --follow`.

**NOTE** You will need to configure the UAA users the first time the application is launched. 
On your local machine, run the following command:

`STARGATE_HOST=<PublicDNS> ./stargate/scripts/uaa-init-aws.sh`

Verify that the application is launched by visiting `http://<PublicDNS>:8081` in your browser (the host
is the public IP address of your EC2 instance and the port is 8081). Log in to
the application with username: `user1` and password: `user1`.

You may need to install the certificate to access the mapping portion of our application. See
the [Map of the World](map-of-the-world.md) section of the README for information.

You're done!

