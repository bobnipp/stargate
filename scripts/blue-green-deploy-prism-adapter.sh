#!/bin/bash

CURRENT_DIR="$(dirname "$0")"
if [ "$1" = "govcloud" ];
then
    export PROJECT=prism-adapter
    export MANIFEST_PATH=components/prism-adapter/manifest-govcloud.yml
    "$CURRENT_DIR/blue-green-deploy-govcloud.sh"
elif [ "$1" = "demo" ];
then
    export PROJECT=prism-adapter-demo
    export MANIFEST_PATH=components/prism-adapter/manifest-demo.yml
    "$CURRENT_DIR/blue-green-deploy-demo.sh"
else
    export PROJECT=prism-adapter
    export MANIFEST_PATH=components/prism-adapter/manifest.yml
    "$CURRENT_DIR/blue-green-deploy.sh"
fi
