package com.mehmetkicirti.todoapp.dto.request;

import com.mehmetkicirti.todoapp.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTodoRequest {
    private String id;
    private String name;
    private TodoStatus status;
    private boolean isCompleted;
    @Nullable
    private String categoryId;
}
