#!/bin/bash

export JAVA_HOME=$(update-alternatives --list | grep java_sdk_1.8.0_openjdk | cut -f 3)
cd /tmp/uaa && UAA_CONFIG_URL=file://$PWD/uaa_config.yml ./gradlew run