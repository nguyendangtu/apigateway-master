# DEMO API GATEWAY
This is a simple demo about api gateway which responsibility for tracking and forwarding request from
source to destination.
#I. IMPLEMENT
##1. Internal servers.
### a. build 3 simple endpoint for GET/POST/PATCH
### b. generate keystore file
#### 'keytool -genkey -keystore ssl-internal-server.p12 -alias ssl-internal-server -keyalg RSA -sigalg SHA256withRSA -validity 365 -keysize 2048 -keypass changeit -storepass changeit'
#### 'keytool -importkeystore -srckeystore  ssl-internal-server.p12  -srcstoretype pkcs12 -destkeystore  ssl-internal-server.jks -deststoretype jks'
##2. ApiGateway
### a. build proxyRestRequest to handle in coming request
### b. apply 2 times retry and allow modify retry time from configuration
### c. define internal server 
### d. save request id and url to database
### f. generate and import keystore

#II. PREPARE INTERNAL SERVER ENDPOINT
## 1. build internal servers by maven and deploy to docker
### a. cd apigateway-master/internal-servers
### b. mvn clean install
### c. copy from internal-servers/target/internal-servers-1.0.0.jar to internal-servers/internal-servers-1.0.0.jar
### d. cd apigateway-master/internal-servers and build docker file. run command: 'docker build -t internalservers . '
### e. start internalserver docker file 'docker run --name internalservers -d -p 8444:8444 internalservers'
##2 verify internal server running 'curl -k https://192.168.99.100:8444/service1/test1?param1=1'

#III. PREPARE API GATEWAY.
##.1 build apigateway by maven and deploy to docker
### a. cd apigateway-master/apigateway
### b. mvn clean install
### c. copy from apigateway/target/apigateway-1.0.0.jar to apigateway/apigateway-1.0.0.jar
### d. cd apigateway-master/apigateway and build docker file. run command: 'docker build -t apigateway . '
### e. start apigateway docker file 'docker run --name apigateway -d -p 8443:8443 apigateway'
##2 verify apigateway server
### a. get bearer token 'curl -k --header "Content-Type: application/json" --request POST --data '{"username":"john","password":"password"}'  https://192.168.99.100:8443/authenticate'
### b. verify service1 'curl -k --header "Content-Type: application/json" --header "Authorization:Bearer ABC" https://192.168.99.100:8443/api/service1/test1/param1=1'

