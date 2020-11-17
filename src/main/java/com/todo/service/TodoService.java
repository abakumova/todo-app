package com.todo.service;

import com.todo.message.ValidationMessageService;
import com.todo.model.Todo;
import com.todo.model.dto.todo.TodoDto;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    @Autowired
    ValidationMessageService message;

    private final TodoRepository todoRepository;

    public List<Todo> getAllTodos() {
        log.info("Get all todos");
        return todoRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Todo create(TodoDto todoDto) {
        log.info("Todo create: id={}, text={}", todoDto.getId(), todoDto.getTitle());
        Todo todo = new Todo(todoDto);
        todo.setIsCompleted(todoDto.getIsCompleted());
        todo.setCreatedAt(todoDto.getCreatedAt());
        todoRepository.save(todo);

        return todo;
    }

    public Todo update(String id, TodoDto todoDto) {
        log.info("Todo update: id={}", id);
        todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, message.getMessage("todo.notfound")));
        return update(getTodoById(id), todoDto);
    }

    public Todo update(Todo existed, TodoDto updated) {
        BeanUtils.copyProperties(updated, existed, "createdAt", "id");

        return todoRepository.save(existed);
    }

    public void delete(String id) {
        log.info("Todo delete: id={}", id);
        todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, message.getMessage("todo.notfound")));
        todoRepository.deleteById(id);
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, message.getMessage("todo.notfound")));
    }
}