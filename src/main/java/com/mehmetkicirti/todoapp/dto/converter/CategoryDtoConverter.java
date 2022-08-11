package com.mehmetkicirti.todoapp.dto.converter;

import com.mehmetkicirti.todoapp.dto.CategoryDto;
import com.mehmetkicirti.todoapp.model.Category;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryDtoConverter {

    public CategoryDto convertTo(Category from){
        if(!Optional.ofNullable(from).isPresent()) return new CategoryDto();
        return new CategoryDto(
                from.getId(),
          from.getName(),
          from.getCreationDate()
        );
    }

    public Category convertTo(CategoryDto from){
        if(!Optional.ofNullable(from).isPresent()) return new Category();
        return new Category(
          from.getName(),
                from.getCreationDate(),
                from.getName()
        );
    }
}
