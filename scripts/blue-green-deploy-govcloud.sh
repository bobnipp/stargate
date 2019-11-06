#!/bin/bash

set -e

cf login -a https://api.system.vi2e.io -u ryan.andrews -p ${CF_PASSWORD} --skip-ssl-validation -s dev
cf target -s dev


CURRENT_PROD=$(cf apps | grep ${PROJECT}.apps.vi2e.io | awk '{print $1}')
echo "*** Current production app: ${CURRENT_PROD} ***"
if [ "$CURRENT_PROD" = "$PROJECT-green" ]; then
    NEW_PROD=${PROJECT}-blue
else
    NEW_PROD=${PROJECT}-green
fi

cf push ${NEW_PROD} -f ${MANIFEST_PATH}

cf map-route ${NEW_PROD} apps.vi2e.io --hostname ${PROJECT}

cf unmap-route ${CURRENT_PROD} apps.vi2e.io --hostname ${PROJECT}