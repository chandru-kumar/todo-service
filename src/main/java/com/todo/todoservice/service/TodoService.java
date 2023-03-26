package com.todo.todoservice.service;

import com.todo.todoservice.model.Todo;
import com.todo.todoservice.repository.TodoRepository;
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
    private final String USERS_API_URL = "https://jsonplaceholder.typicode.com/users";
    private final String PHOTOS_API_URL = "https://jsonplaceholder.typicode.com/photos";
    private final String COMMENTS_API_URL = "https://jsonplaceholder.typicode.com/comments";
    private final String POSTS_API_URL = "https://jsonplaceholder.typicode.com/posts";


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
        log.log(Level.INFO, "Login starts...");
        authenticateUser();
        generateKey();
        fetchDashboardDetails();
    }

    private void fetchDashboardDetails() {
        try {
            restTemplate.getForObject(PHOTOS_API_URL, Object.class);
            restTemplate.getForObject(COMMENTS_API_URL, Object.class);
            restTemplate.getForObject(POSTS_API_URL, Object.class);
            log.log(Level.INFO, "Fetched Dashboard Details");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void authenticateUser() {
        log.log(Level.INFO, "Authenticating...");
        try {
            restTemplate.getForObject(USERS_API_URL, Object.class);
            log.log(Level.INFO, "Authenticated.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void generateKey() {
        try {
            log.log(Level.INFO, "Auth key : 3njhiohxajijd2w2");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
