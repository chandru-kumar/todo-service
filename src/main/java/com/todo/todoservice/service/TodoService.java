package com.todo.todoservice.service;

import com.todo.todoservice.TodoServiceApplication;
import com.todo.todoservice.model.Todo;
import com.todo.todoservice.repository.TodoRepository;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.api.trace.TracerProvider;
import io.opentelemetry.context.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final String TODO_API_URL = "https://jsonplaceholder.typicode.com/todos";

    private final RestTemplate restTemplate;

    OpenTelemetry openTelemetry = TodoServiceApplication.initializeOpenTelemetry();

    TracerProvider tracerProvider = openTelemetry.getTracerProvider();

    private final Tracer tracer;

    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        tracer = tracerProvider.get("io.opentelemetry.example.ZipkinExample");
    }

    public List<Todo> getTodos() {
        Todo[] todos = restTemplate.getForObject(TODO_API_URL, Todo[].class);
        return Arrays.stream(todos).collect(Collectors.toList());
    }

    public void saveTodos(List<Todo> todos, TodoRepository todoRepository) {
        todoRepository.saveAll(todos);
    }

    public void loginTest() {
        authenticateUser();
        generateKey();
        fetchDashboardDetailsTest();
    }

    private void fetchDashboardDetailsTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void authenticateUser() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateKey() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void login() {
        // Generate span
        Span span = tracer.spanBuilder("login").startSpan();
        try (Scope scope = span.makeCurrent()) {
            // Add some Event to the span
            span.addEvent("Login Event");
            // execute my use case - here we simulate a wait
            authenticate();
            generateAPIKey();
            fetchDashboardDetails();
            getFavourites();
        } finally {
            span.end();
        }
    }


    void authenticate() {
        Span childSpan = tracer.spanBuilder("authenticateUser")
                // NOTE: setParent(...) is not required;
                // `Span.current()` is automatically added as the parent
                .startSpan();
        try(Scope scope = childSpan.makeCurrent()) {
            try {
                childSpan.setAttribute("User", "AdminUser");
                Thread.sleep(350);
            } catch (InterruptedException e) {
                // ignore in an example
            }
        } finally {
            childSpan.end();
        }
    }

    void generateAPIKey() {
        Span childSpan = tracer.spanBuilder("generateAPIKey")
                // NOTE: setParent(...) is not required;
                // `Span.current()` is automatically added as the parent
                .startSpan();
        try(Scope scope = childSpan.makeCurrent()) {
            try {
                childSpan.setAttribute("Key", "Generated Key- Yshkjd713gjkg1kj4h");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // ignore in an example
            }
        } finally {
            childSpan.end();
        }
    }

    void fetchDashboardDetails() {
        Span childSpan = tracer.spanBuilder("fetchDashboardDetails")
                // NOTE: setParent(...) is not required;
                // `Span.current()` is automatically added as the parent
                .startSpan();
        try(Scope scope = childSpan.makeCurrent()) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // ignore in an example
            }
        } finally {
            childSpan.end();
        }
    }

    private void getFavourites() {
        Span childSpan = tracer.spanBuilder("getFavourites")
                // NOTE: setParent(...) is not required;
                // `Span.current()` is automatically added as the parent
                .startSpan();
        try(Scope scope = childSpan.makeCurrent()) {
            try {
                childSpan.setStatus(StatusCode.ERROR);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // ignore in an example
            }
        } finally {
            childSpan.end();
        }
    }
}
