package com.todo.todoservice;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

@SpringBootApplication
@EnableAutoConfiguration
public class TodoServiceApplication {

	private static final String SERVICE_NAME = "LoginService";

	public static void main(String[] args) {
		SpringApplication.run(TodoServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static OpenTelemetry initializeOpenTelemetry() {
		String endpoint = String.format("http://localhost:9411/api/v2/spans");
		ZipkinSpanExporter zipkinExporter = ZipkinSpanExporter.builder().setEndpoint(endpoint).build();

		Resource serviceNameResource =
				Resource.create(Attributes.of(AttributeKey.stringKey("service.name"), SERVICE_NAME));

		// Set to process the spans by the Zipkin Exporter
		SdkTracerProvider tracerProvider =
				SdkTracerProvider.builder()
						.addSpanProcessor(SimpleSpanProcessor.create(zipkinExporter))
						.setResource(Resource.getDefault().merge(serviceNameResource))
						.build();
		OpenTelemetrySdk openTelemetry =
				OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).buildAndRegisterGlobal();

		// add a shutdown hook to shut down the SDK
		Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::close));

		// return the configured instance so it can be used for instrumentation.
		return openTelemetry;
	}

}
