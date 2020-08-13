package com.todo.service;

import com.todo.model.Todo;
import com.todo.model.dto.todo.TodoDto;
import com.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Page<Todo> getAllTodos() {
        return todoRepository.findAll(PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt")));
    }

    public Todo create(TodoDto todoDto) {
        Todo todo = new Todo(todoDto);

        return todoRepository.save(todo);
    }

    public Todo update(String id, TodoDto todoDto) {
        return update(getTodoById(id), todoDto);
    }

    public Todo update(Todo existed, TodoDto updated) {
        BeanUtils.copyProperties(updated, existed);

        return todoRepository.save(existed);
    }

    public void delete(String id) {
        todoRepository.deleteById(id);
    }

    public Todo getTodoById(String id) {
        return todoRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}