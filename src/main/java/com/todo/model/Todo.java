package com.todo.model;

import com.todo.model.dto.todo.TodoDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Indexed
    @NotBlank
    @Size(max = 100)
    private String title;

    @CreatedDate
    private Date createdAt;

    private Boolean isCompleted;

    public Todo(TodoDto todoDto) {
        BeanUtils.copyProperties(todoDto, this);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt == null ? new Date() : createdAt;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted != null && isCompleted;
    }
}