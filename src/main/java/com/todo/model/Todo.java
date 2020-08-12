package com.todo.model;

import com.todo.model.dto.TodoDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@Document(collection = "todos")
public class Todo {

    public Todo(TodoDto todoDto) {
        BeanUtils.copyProperties(todoDto, this);
    }

    @Id
    private String id;

    @Indexed
    private String title;

    @CreatedDate
    private Date createdAt;

    private Boolean isCompleted;

    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt == null ? null : new Date(createdAt.getTime());
    }
}