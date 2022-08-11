package com.mehmetkicirti.todoapp.service;

import com.mehmetkicirti.todoapp.constant.ExceptionMessage;
import com.mehmetkicirti.todoapp.dto.CategoryDto;
import com.mehmetkicirti.todoapp.dto.TodoDto;
import com.mehmetkicirti.todoapp.dto.converter.CategoryDtoConverter;
import com.mehmetkicirti.todoapp.dto.converter.TodoDtoConverter;
import com.mehmetkicirti.todoapp.dto.request.CreateTodoRequest;
import com.mehmetkicirti.todoapp.dto.request.UpdateTodoRequest;
import com.mehmetkicirti.todoapp.exception.ResourceNotFoundException;
import com.mehmetkicirti.todoapp.model.Todo;
import com.mehmetkicirti.todoapp.repository.TodoRepository;
import com.mehmetkicirti.todoapp.utility.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final CategoryService categoryService;
    private final TodoDtoConverter todoDtoConverter;
    private final CategoryDtoConverter categoryDtoConverter;
    private final Clock clock;

    public TodoService(TodoRepository todoRepository,
                       CategoryService categoryService,
                       TodoDtoConverter todoDtoConverter,
                       CategoryDtoConverter categoryDtoConverter,
                       Clock clock) {
        this.todoRepository = todoRepository;
        this.categoryService = categoryService;
        this.todoDtoConverter = todoDtoConverter;
        this.categoryDtoConverter = categoryDtoConverter;
        this.clock = clock;
    }

    public TodoDto getTodoById(String id){
         Todo todo = todoRepository.findById(id)
                 .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
        return todoDtoConverter.convertTo(todo);
    }

    public List<TodoDto> getTodos(){
        return todoRepository.findAll()
                .stream()
                .map(todoDtoConverter::convertTo)
                .collect(Collectors.toList());
    }

    public List<TodoDto> getTodosByCategoryId(String categoryId){
        return todoRepository.getTodosByCategory_Id(categoryId)
                .stream()
                .map(todoDtoConverter::convertTo)
                .collect(Collectors.toList());
    }

    public TodoDto createTodo(CreateTodoRequest request, String categoryId){
        Todo todo = new Todo(
                DateTimeUtils.getDateTimeConfig(clock).getLocalDateTimeNow(),
                request.getName(),
                request.getStatus(),
                false
        );
        if(Optional.ofNullable(categoryId).isPresent()){
            CategoryDto category = Optional.of(categoryService.getCategoryById(categoryId))
                    .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));

           todo.setCategory(categoryDtoConverter.convertTo(category));
        }
        return todoDtoConverter.convertTo(todo);
    }

    public TodoDto updateTodo(UpdateTodoRequest request){
        if(Optional.ofNullable(request).isPresent()){
            Todo todo = todoRepository.findById(request.getId())
                    .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
            todo.setCompleted(request.isCompleted());
            todo.setName(request.getName());
            todo.setStatus(request.getStatus());
            todo.setUpdatedDate(DateTimeUtils.getDateTimeConfig(clock).getLocalDateTimeNow());

            if(Optional.ofNullable(request.getCategoryId()).isPresent() &&
                    request.getCategoryId().equalsIgnoreCase(todo.getCategory().getId())){

                CategoryDto category = Optional.of(categoryService.getCategoryById(request.getCategoryId()))
                        .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));

                todo.setCategory(categoryDtoConverter.convertTo(category));
            }
            return todoDtoConverter.convertTo(todo);
        }
        return null;
    }

    public void deleteTodo(String todoId){
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(()->new ResourceNotFoundException(ExceptionMessage.RESOURCE_NOT_FOUND));
        todoRepository.delete(todo);
    }
}
