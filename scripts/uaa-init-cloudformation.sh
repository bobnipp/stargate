#!/bin/bash

set -e

STARGATE_HOST=$(aws ec2 describe-instances --filters "Name=tag:Name,Values=stargate-app-cloudformation" "Name=instance-state-name,Values=running" --query 'Reservations[0].Instances[0].PublicDnsName' --output text)
UAA_HOST=$(aws ec2 describe-instances --filters "Name=tag:Name,Values=stargate-uaa-cloudformation" "Name=instance-state-name,Values=running" --query 'Reservations[0].Instances[0].PublicDnsName' --output text)

function validate_response {
    OPERATION=$1
    EXPECTED_RESPONSE_CODE=$2
    RESPONSE=$3

    response_code=$(echo $RESPONSE | head -n 1 | cut -d " " -f 2)
    if [ $response_code != $EXPECTED_RESPONSE_CODE ]
    then
        echo "ERROR: Unexpected response code for operation: $OPERATION"
        echo $RESPONSE
        exit 1
    fi
}

# get token
get_token_response=$(curl "http://$UAA_HOST:8080/uaa/oauth/token" -s -i -X POST \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -H 'Accept: application/json' \
    -d 'client_id=admin&client_secret=adminsecret&grant_type=client_credentials&token_format=opaque')
validate_response 'get-token' '200' "$get_token_response"
TOKEN=$(echo $get_token_response | tail -n 1 | cut -d "\"" -f 4)


# create the 'stargate' client
create_client_response=$(curl "http://$UAA_HOST:8080/uaa/oauth/clients" -s -i -X POST \
    -H 'Content-Type: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -H 'Accept: application/json' \
    -d '{
  "scope" : [ "stargate.read", "stargate.write", "openid", "profile", "email", "address", "phone" ],
  "client_id" : "stargate",
  "client_secret" : "stargatesecret",
  "authorized_grant_types" : [ "authorization_code", "refresh_token", "client_credentials", "password" ],
  "redirect_uri" : [ "http://'"$STARGATE_HOST:8081"'/**", "http://localhost:8081/**" ],
  "authorities" : [ "uaa.resource" ],
  "name" : "stargate"
}')
validate_response 'create-client' '201' "$create_client_response"


# create user 'user1'
create_user_response=$(curl "http://$UAA_HOST:8080/uaa/Users" -s -i -X POST \
    -H 'Accept: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -H 'Content-Type: application/json' \
    -d '{
  "externalId" : "user1",
  "userName" : "user1",
  "name" : {
    "formatted" : "user1",
    "familyName" : "1",
    "givenName" : "user"
  },
  "emails" : [ {
    "value" : "user1@example.com",
    "primary" : true
  } ],
  "active" : true,
  "origin" : "uaa",
  "verified" : true,
  "password" : "user1"
 }')
validate_response 'create-user' '201' "$create_user_response"
USER_ID_1=$(echo $create_user_response | tail -n 1 | cut -d "\"" -f 6)

# create user 'james.spader'
create_user_response=$(curl "http://$UAA_HOST:8080/uaa/Users" -s -i -X POST \
    -H 'Accept: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -H 'Content-Type: application/json' \
    -d '{
  "externalId" : "james.spader",
  "userName" : "james.spader",
  "name" : {
    "formatted" : "james spader",
    "familyName" : "spader",
    "givenName" : "james"
  },
  "emails" : [ {
    "value" : "james.spader@example.com",
    "primary" : true
  } ],
  "active" : true,
  "origin" : "uaa",
  "verified" : true,
  "password" : "stargat3"
 }')
validate_response 'create-user' '201' "$create_user_response"
USER_ID_2=$(echo $create_user_response | tail -n 1 | cut -d "\"" -f 6)


# create 'stargate.read' group
create_read_group_response=$(curl "http://$UAA_HOST:8080/uaa/Groups" -s -i -X POST \
    -H 'Content-Type: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -d '{
  "displayName" : "stargate.read",
  "members" : [ {
    "origin" : "uaa",
    "type" : "USER",
    "value" : "'"$USER_ID_1"'"
  }, {
    "origin" : "uaa",
    "type" : "USER",
    "value" : "'"$USER_ID_2"'"
  } ]
}')
validate_response 'create-stargate-read-group' '201' "$create_read_group_response"


# create 'stargate.write' group
create_write_group_response=$(curl "http://$UAA_HOST:8080/uaa/Groups" -s -i -X POST \
    -H 'Content-Type: application/json' \
    -H "Authorization: Bearer $TOKEN" \
    -d '{
  "displayName" : "stargate.write",
  "members" : [ {
    "origin" : "uaa",
    "type" : "USER",
    "value" : "'"$USER_ID_1"'"
  }, {
    "origin" : "uaa",
    "type" : "USER",
    "value" : "'"$USER_ID_2"'"
  } ]
}')
validate_response 'create-stargate-write-group' '201' "$create_write_group_response"


echo "Done!"