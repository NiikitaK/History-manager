# Build Application

`mvn clean install spring-boot:run`

# Build Docker 
`docker build -t historyapplication .`

## Install in Docker

### Create network 

`docker network create histapp`

### Run application:

```
docker run \
--name=mongo \
--rm \
--network=histapp mongo

docker run \
--name=historyapplication \
--rm \
--network=histapp \
-p 8080:8080 \
-e MONGO_URL=mongodb://mongo:27017/dev \
nikich/historyapplication:1.0
```

### Stop application

`docker stop mongo historyapplication`

# Install in Kubernetes 

## Install in minikube

### Run minikube

`minikube start`

### Deploy application via kubernetes templates

`kubectl apply -f kube`

### Deploy application via helm

`helm install . -f '${your_path_to_project}/SpringBootApp/helm/charts/historyapp/values.yaml --generate-name`
`helm install . -f '${your_path_to_project}/SpringBootApp/helm/charts/mongo/values.yaml --generate-name`

### Other commands

```
helm list
helm uninstall "name chart"
minikube service historyapplication --url
```