#!/bin/bash

set -e

cf login -a https://api.system.vi2e.io -u ryan.andrews -p ${CF_PASSWORD} --skip-ssl-validation -s dev

cd components/prism-mock
unzip -oq wiremock-standalone-2.18.0.jar -d toDeploy
cp -r __files toDeploy/__files
cp -r mappings toDeploy/mappings
cd toDeploy
cf push prism-mock -u process -b java_buildpack_offline
cd ..
rm -rf toDeploy
