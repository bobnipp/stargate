#!/bin/bash

set -e

cf login -a api.run.pivotal.io -u pivotal.stargate@gmail.com -p ${CF_PASSWORD}

CURRENT_PROD=$(cf apps | grep ${PROJECT}.cfapps.io | awk '{print $1}')
echo "*** Current production app: ${CURRENT_PROD} ***"
if [ "$CURRENT_PROD" = "$PROJECT-green" ]; then
    NEW_PROD=${PROJECT}-blue
else
    NEW_PROD=${PROJECT}-green
fi

cf push ${NEW_PROD} -f ${MANIFEST_PATH}

cf map-route ${NEW_PROD} cfapps.io --hostname ${PROJECT}

cf unmap-route ${CURRENT_PROD} cfapps.io --hostname ${PROJECT}