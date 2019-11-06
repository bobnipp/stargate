## How to get whitelisted
An Jiang and Kelly Kohl helped us locate Fabio Gonzalez who was able to give us access to the unclassified test instance of PRISM.

anjiang6@deloitte.com
kelly.a.kohl2.civ@mail.mil
Fabio.Gonzalez@ngc.com

Our application requires using the credentials of a user that has been granted web interface and SOAP API access.

## PRISM Environment Variables

```
export PRISM_HOST=<prism host url> #e.g. https://ec2-96-127-47-237.us-gov-west-1.compute.amazonaws.com
export PRISM_USE_MOCK=false # Don't worry about what this means...just set it to false
export PRISM_SOAP_USERNAME=<username for the Prism SOAP API>
export PRISM_SOAP_PASSWORD=<password for the Prism SOAP API>
export PRISM_UI_USERNAME=<username for the Prism UI>
export PRISM_UI_USERNAME=<password for the Prism UI>

```

## PRISM data in our AWS-deployed application

Our instance of Prism Adapter in AWS does not have access to PRISM due to whitelist restrictions. To see real PRISM data
in the AWS-deployed version of the application, we can populate the cache on our locally running machine (which is running
on the whitelisted Pivotal network) and then copy the data from our local Redis over to ElastiCache on AWS.


#### Updating your local cache

* To use PRISM data, set `USE_PRISM_MOCK=false` when starting the adapter
* Warm the cache by sending a POST requres to `http://localhost:8082/cache`
* The data in the cache will be on your local developer machine at `/usr/local/var/db/redis/dump.rdb`


#### Updating the cached data on AWS
TODO
