package com.todo.model.dto.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

    private String id;
    private String title;
    private Boolean isCompleted;
    private Date createdAt;
}