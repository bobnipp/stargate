## Jira

### Deploying Jira to Cloud Foundry
1. Make sure you have the `CF_DOCKER_PASSWORD` environment variable set
1. `cd stargate-jira`
1. Update your Dockerfile to change whatever you need
1. `docker build -t docker-di2e.di2e.net/stargate-jira:<new tag #>`
1. `docker push docker-di2e.di2e.net/stargate-jira:<new tag #>`
1. `cf push stargate-jira --docker-image docker-di2e.di2e.net/stargate-jira:<new tag #> --docker-username <di2e username>`