#!/bin/bash

cd tmp
java -jar -Dspring.profiles.active=aws prism-adapter-0.0.1-SNAPSHOT.jar
