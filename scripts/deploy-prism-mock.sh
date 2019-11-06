#!/bin/bash

set -e

cf login -a api.run.pivotal.io -u pivotal.stargate@gmail.com -p ${CF_PASSWORD}
cd components/prism-mock
unzip -oq wiremock-standalone-2.18.0.jar -d toDeploy
cp -r __files toDeploy/__files
cp -r mappings toDeploy/mappings
cd toDeploy
cf push prism-mock -u process -b java_buildpack
cd ..
rm -rf toDeploy
