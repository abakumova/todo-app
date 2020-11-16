package com.todo.controller;

import com.todo.model.Todo;
import com.todo.model.dto.todo.TodoDto;
import com.todo.service.TodoService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
@RequestMapping(path = "/ta/apis/v1")
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @ApiOperation(value = "Returns all todos", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @ApiOperation(value = "Returns todo by ID", response = Todo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@ApiParam(value = "Todo id to receive", required = true) @PathVariable("id") String id) {
        return todoService.getTodoById(id);
    }

    @ApiModelProperty(value = "Creates todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @ApiOperation(value = "Creates todo", response = Todo.class)
    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody TodoDto todoDto) {
        return todoService.create(todoDto);
    }

    @ApiOperation(value = "Updates todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@ApiParam(value = "Todo id to update", required = true) @PathVariable("id") String id, @Valid @RequestBody TodoDto todo) {
        return todoService.update(id, todo);
    }

    @ApiOperation(value = "Deletes todo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Client error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @DeleteMapping("/todos/{id}")
    public void deleteTodo(@ApiParam(value = "Todo id to delete", required = true) @PathVariable("id") String id) {
        todoService.delete(id);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some parameters are invalid")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleException() {
        log.info("Entered value - Unable to create/update todo");
    }
}