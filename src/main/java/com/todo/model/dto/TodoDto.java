package com.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {

    @Id
    private String id;

    @NotBlank
    @Size(max = 100)
    private String title;

    private Boolean isCompleted;

    private Date createdAt;
}