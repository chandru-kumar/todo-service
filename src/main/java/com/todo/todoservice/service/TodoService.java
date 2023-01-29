package com.todo.todoservice.service;

import com.todo.todoservice.model.Todo;
import com.todo.todoservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final String TODO_API_URL = "https://jsonplaceholder.typicode.com/todos";

    private final RestTemplate restTemplate;

    public TodoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Todo> getTodos() {
        Todo[] todos = restTemplate.getForObject(TODO_API_URL, Todo[].class);
        return Arrays.stream(todos).collect(Collectors.toList());
    }

    public void saveTodos(List<Todo> todos, TodoRepository todoRepository) {
        todoRepository.saveAll(todos);
    }
}
