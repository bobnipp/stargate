## Building artifacts for AWS deployment

#### Stargate-API and Prism Adapter
Build the application to create updated jar files: `./gradlew build`

#### UAA
UAA does not need to be built prior to building the UAA docker image, because this particular Dockerfile copies the
UAA code to the image. When a container is created from that image, it will compile the code and start the service.

#### Build the Docker images

API:
* Determine the latest tag in the DI2E docker registry by visiting the following URL: https://docker-di2e.di2e.net/v2/stargate-api/tags/list
* Build the API image: `docker build . --file Dockerfile_Stargate_API -t docker-di2e.di2e.net/stargate-api:<new tag #>`
* Run the image locally to ensure functionality: `docker run -p 8081:8081 docker-di2e.di2e.net/stargate-api:<new tag #>`

Prism Adapter:
* Determine the latest tag in the DI2E docker registry by visiting the following URL: https://docker-di2e.di2e.net/v2/stargate-prism-adapter/tags/list
* Build the PRISM ADAPTER image: `docker build . --file Dockerfile_Prism_Adapter -t docker-di2e.di2e.net/stargate-prism-adapter:<new tag #>`
* Run the image locally to ensure functionality: `docker run -p 8082:8082 docker-di2e.di2e.net/stargate-prism-adapter:<new tag #>`

UAA:
* Determine the latest tag in the DI2E docker registry by visiting the following URL: https://docker-di2e.di2e.net/v2/stargate-aws-uaa/tags/list
* Build the UAA image: `docker build . --file Dockerfile_UAA -t docker-di2e.di2e.net/stargate-aws-uaa:<new tag #>`
* Run the image locally to ensure functionality: `docker run -p 8080:8080 docker-di2e.di2e.net/stargate-aws-uaa:<new tag #>`
* Visit `http://localhost:8080/uaa` to validate that UAA is up.

#### Publish the images to the Di2e Docker registry
Log the docker client into the DI2E docker registry: `docker login https://docker-di2e.di2e.net`

Push the images to the registry:
```
docker push docker-di2e.di2e.net/stargate-api:<new tag #>
docker push docker-di2e.di2e.net/stargate-prism-adapter:<new tag #>
docker push docker-di2e.di2e.net/stargate-aws-uaa:<new tag #>
```

#### Update docker-compose.yml
If the version numbers of the images have been updated then be sure to update the `docker-compose.yml` file. 