#!/bin/bash

CURRENT_DIR="$(dirname "$0")"
if [ "$1" = "govcloud" ]
then
    export PROJECT=stargate
    export MANIFEST_PATH=components/api/manifest-govcloud.yml
    "$CURRENT_DIR/blue-green-deploy-govcloud.sh"
elif [ "$1" = "demo" ]
then
    export PROJECT=stargate-demo
    export MANIFEST_PATH=components/api/manifest-demo.yml
    "$CURRENT_DIR/blue-green-deploy-demo.sh"
else
    export PROJECT=stargate
    export MANIFEST_PATH=components/api/manifest.yml
    "$CURRENT_DIR/blue-green-deploy.sh"
fi
