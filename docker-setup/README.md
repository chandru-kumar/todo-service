# OpenTelemetry Annotation Demo

To run the demo, switch to this directory and run(Windows machine, Haven't tried in MAC or Linux):

```shell
docker compose up -d
```

The demo exposes the following backends:

- Jaeger at http://localhost:16686
- Zipkin at http://localhost:9411
- Prometheus at http://localhost:9090
- Kibana at http://localhost:5601

To clean up any docker container from the demo run `docker compose down` from 
this directory.

//From Agent to Elastic
```shell
java -javaagent:"C:\OpenTelemetry Demo\opentelemetry-javaagent.jar" -Dotel.resource.attributes=service.name=otel-experimental-service,service.version=1.1,deployment.environment=production -Dotel.exporter.otlp.endpoint=https://083c75913e614011b0ef1afd98bdfd98.apm.us-central1.gcp.cloud.es.io:443 -Dotel.exporter.otlp.headers="Authorization=Bearer TwNVPjMQ7evWJZxMXS" -Dotel.metrics.exporter="otlp" -Dotel.logs.exporter="otlp" -jar todo-service-0.0.1-SNAPSHOT.jar
```

//From OTEL Collector to Elastic
```shell
java -javaagent:"C:\OpenTelemetry Demo\opentelemetry-javaagent.jar" -Dotel.resource.attributes=service.name=otel-experimental-service,service.version=1.1,deployment.environment=production -Dotel.metrics.exporter="otlp" -Dotel.logs.exporter="otlp" -jar todo-service-0.0.1-SNAPSHOT.jar
```