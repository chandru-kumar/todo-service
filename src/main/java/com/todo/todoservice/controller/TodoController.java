package com.todo.todoservice.controller;

import com.todo.todoservice.model.Todo;
import com.todo.todoservice.repository.TodoRepository;
import com.todo.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        List<Todo> todos = todoService.getTodos();
        todoService.saveTodos(todos, todoRepository);
        return todos;
    }

    @GetMapping("/login")
    public boolean login() {
        todoService.login();
        return Boolean.TRUE;
    }


}
