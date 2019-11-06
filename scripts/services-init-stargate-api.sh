#!/bin/sh

cd /tmp
java -jar -Dspring.profiles.active=aws stargate-0.0.1-SNAPSHOT.jar
