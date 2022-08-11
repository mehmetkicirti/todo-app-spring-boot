package com.mehmetkicirti.todoapp.service;

import com.mehmetkicirti.todoapp.constant.ExceptionMessage;
import com.mehmetkicirti.todoapp.dto.CategoryDto;
import com.mehmetkicirti.todoapp.dto.converter.CategoryDtoConverter;
import com.mehmetkicirti.todoapp.dto.request.CreateCategoryRequest;
import com.mehmetkicirti.todoapp.dto.request.UpdateCategoryRequest;
import com.mehmetkicirti.todoapp.exception.ResourceNotFoundException;
import com.mehmetkicirti.todoapp.model.Category;
import com.mehmetkicirti.todoapp.repository.CategoryRepository;
import com.mehmetkicirti.todoapp.utility.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;
    private final Clock clock;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter categoryDtoConverter, Clock clock) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
        this.clock = clock;
    }

    public CategoryDto getCategoryById(String id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
        return categoryDtoConverter.convertTo(category);
    }

    public List<CategoryDto> getCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoConverter::convertTo)
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CreateCategoryRequest request){
        Category category = new Category(
                DateTimeUtils.getDateTimeConfig(clock).getLocalDateTimeNow(),
                request.getName()
        );
        return categoryDtoConverter.convertTo(category);
    }

    public CategoryDto updateCategory(UpdateCategoryRequest request){
        if(Optional.ofNullable(request).isPresent()){
            Category category = categoryRepository.findById(request.getId())
                    .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
            category.setName(request.getName());
            category.setUpdatedDate(DateTimeUtils.getDateTimeConfig(clock).getLocalDateTimeNow());

            return categoryDtoConverter.convertTo(category);
        }
        return null;
    }

    public void deleteCategory(String categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
        categoryRepository.delete(category);
    }
}
