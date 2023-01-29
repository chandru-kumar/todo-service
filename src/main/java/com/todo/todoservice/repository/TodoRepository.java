package com.todo.todoservice.repository;

import com.todo.todoservice.model.Todo;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TodoRepository extends CrudRepository<Todo, Integer> {

}
