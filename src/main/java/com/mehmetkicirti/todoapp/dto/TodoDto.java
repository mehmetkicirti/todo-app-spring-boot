package com.mehmetkicirti.todoapp.dto;

import com.mehmetkicirti.todoapp.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private String id;
    private LocalDateTime creationDate;
    private String name;
    private TodoStatus status;
    private boolean isCompleted;
    private CategoryDto category;
}
