#!/bin/zsh

set -e

uaac target https://stargate-uaa-demo.apps.vi2e.io --skip-ssl-validation

uaac token client get admin -s adminsecret

uaac client add stargate --name stargate --scope stargate.read,stargate.write,openid,profile,email,address,phone -s stargatesecret --authorized_grant_types authorization_code,refresh_token,client_credentials,password --authorities uaa.resource --redirect_uri https://stargate-demo.apps.vi2e.io/\*\*,http://stargate-demo.apps.vi2e.io/\*\*,http://localhost:8081/\*\*

uaac user add user1 -p user1 --emails user1@user1.com
uaac user add james.spader -p stargat3 --emails james.spader@example.com

#Add resource.read and resource.write scopes
uaac group add stargate.read
uaac group add stargate.write

# Assign user1 both stargate.read, stargate.write scopes..
uaac member add stargate.read user1
uaac member add stargate.write user1
uaac member add stargate.read james.spader
uaac member add stargate.write james.spader

