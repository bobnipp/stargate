#!/bin/bash

n=0
until [ $n -ge 120 ]
do
  aws cloudformation create-stack --stack-name stargate-test --template-body file://cloudformation/cloudformation.yml --parameters ParameterKey=stargateDbUser,ParameterValue=someone ParameterKey=uaaDbUser,ParameterValue=someone ParameterKey=stargateVersion,ParameterValue=0.0.3 ParameterKey=dockerHost,ParameterValue=docker-di2e.di2e.net ParameterKey=dockerUsername,ParameterValue=jeremy.gustine ParameterKey=dockerPassword,ParameterValue=Educate70authority! ParameterKey=stargateDbPassword,ParameterValue=abcd123456 ParameterKey=uaaDbPassword,ParameterValue=bcda654321 && break
  n=$[$n+1]
  sleep 30
done