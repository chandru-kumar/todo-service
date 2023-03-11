Dependencies:
OpenJDK 17

To run this application:

`mvn spring-boot:run`
or
`mvn clean package`
Navigate to target folder.
`java -jar *.jar`

To run it in Docker:

`docker build -t todo-app .`
`docker run -p 8282:8080 todo-app`

To push to your docker registry:
`docker tag todo-app <YOUR_DOCKER_HUB_NAME>/todo-app`
`docker image push <YOUR_DOCKER_HUB_NAME>/todo-app`

Download zipkin and run it - port 9411
