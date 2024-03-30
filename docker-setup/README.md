# OpenTelemetry and Elastic Demo

## Requirements
JAVA 17, Docker, OpenTelemetry agent


## Steps to run demo application
To run the demo application, switch to this - OTEL-COLLECTOR directory and run(Windows machine, Haven't tried in MAC or Linux):

```shell
docker compose up -d
```

The following docker containers should be up and running:

[+] Running 2/2
✔ Network otel-collector_default        Created
✔ Container otel-collector-collector-1  Started


//To run the application, navigate to the jarfile folder - jarfile/todo-service-0.0.1-SNAPSHOT.jar and 
run below command after setting Elastic account - https://cloud.elastic.co/login and having the opentelemetry java agent jar file:
```shell
java -javaagent:"C:\OpenTelemetry Demo\opentelemetry-javaagent.jar" -Dotel.resource.attributes=service.name=otel-experimental-service,service.version=1.1,deployment.environment=production -Dotel.exporter.otlp.endpoint=https://c446926c25214da193d1c7823ce45178.apm.us-central1.gcp.cloud.es.io:443 -Dotel.exporter.otlp.headers="Authorization=Bearer aIc7oI1OE1DQowIiEf" -Dotel.metrics.exporter="otlp" -Dotel.logs.exporter="otlp" -jar todo-service-0.0.1-SNAPSHOT.jar
```

Hit the apis to see the metrics, logs and traces in Elastic:
http://localhost:8282/api/login
http://localhost:8282/api/todos