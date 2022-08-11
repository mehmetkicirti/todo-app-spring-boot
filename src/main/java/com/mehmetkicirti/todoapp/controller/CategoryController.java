package com.mehmetkicirti.todoapp.controller;

import com.mehmetkicirti.todoapp.dto.CategoryDto;
import com.mehmetkicirti.todoapp.dto.request.CreateCategoryRequest;
import com.mehmetkicirti.todoapp.dto.request.UpdateCategoryRequest;
import com.mehmetkicirti.todoapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getTodoById(@PathVariable("id") String id){
        CategoryDto category = categoryService.getCategoryById(id);
        if(Optional.ofNullable(category).isPresent()) return ResponseEntity.ok(category);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getTodoById(){
        List<CategoryDto> categories = categoryService.getCategories();
        if(Optional.ofNullable(categories).isPresent()) return ResponseEntity.ok(categories);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createTodo(@RequestBody CreateCategoryRequest request){
        return ResponseEntity.ok(categoryService.createCategory(request));
    }

    @PutMapping
    public ResponseEntity<CategoryDto> createTodo(@RequestBody UpdateCategoryRequest request){
        return ResponseEntity.ok(categoryService.updateCategory(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
