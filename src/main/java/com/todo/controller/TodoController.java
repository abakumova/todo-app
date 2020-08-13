package com.todo.controller;

import com.todo.model.Todo;
import com.todo.model.dto.todo.TodoDto;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@Validated
@RequestMapping(path = "/ta/apis/v1")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Page<Todo> todoPage = todoService.getAllTodos();
        return todoPage.getContent();
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody TodoDto todo) {
        return todoService.create(todo);
    }

    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable("id") String id, @Valid @RequestBody TodoDto todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        todoService.delete(id);
    }
}