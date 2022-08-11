package com.mehmetkicirti.todoapp.dto.request;

import com.mehmetkicirti.todoapp.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTodoRequest {
    private String name;
    private TodoStatus status;
}
