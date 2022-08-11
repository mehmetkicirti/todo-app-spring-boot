package com.mehmetkicirti.todoapp.dto.converter;

import com.mehmetkicirti.todoapp.dto.TodoDto;
import com.mehmetkicirti.todoapp.model.Todo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TodoDtoConverter {
    private final CategoryDtoConverter categoryDtoConverter;
    public TodoDtoConverter(CategoryDtoConverter categoryDtoConverter) {
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public TodoDto convertTo(Todo from){
        if(!Optional.ofNullable(from).isPresent()) return new TodoDto();
        return new TodoDto(
                from.getId(),
                from.getCreationDate(),
                from.getName(),
                from.getStatus(),
                from.isCompleted(),
                categoryDtoConverter.convertTo(from.getCategory())
        );
    }

    public Todo convertTo(TodoDto from){
        if(!Optional.ofNullable(from).isPresent()) return new Todo();
        return new Todo(
                from.getId(),
                from.getCreationDate(),
                from.getName(),
                from.getStatus(),
                from.isCompleted(),
                categoryDtoConverter.convertTo(from.getCategory())
        );
    }
}
