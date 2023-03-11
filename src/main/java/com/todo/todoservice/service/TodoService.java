package com.todo.todoservice.service;

import com.todo.todoservice.model.Todo;
import com.todo.todoservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final String TODO_API_URL = "https://jsonplaceholder.typicode.com/todos";

    private Logger log = Logger.getLogger("TodoService");

    private final RestTemplate restTemplate;

    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Todo> getTodos() {
        Todo[] todos = restTemplate.getForObject(TODO_API_URL, Todo[].class);
        log.log(Level.INFO, "Fetched todos");
        return Arrays.stream(todos).collect(Collectors.toList());
    }

    public void saveTodos(List<Todo> todos, TodoRepository todoRepository) {
        todoRepository.saveAll(todos);
    }

    public void login() {
        authenticateUser();
        generateKey();
        fetchDashboardDetails();
    }

    private void fetchDashboardDetails() {
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
}
