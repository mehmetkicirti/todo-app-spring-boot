package com.mehmetkicirti.todoapp.controller;

import com.mehmetkicirti.todoapp.dto.TodoDto;
import com.mehmetkicirti.todoapp.dto.request.CreateTodoRequest;
import com.mehmetkicirti.todoapp.dto.request.UpdateTodoRequest;
import com.mehmetkicirti.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/api/v1")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") String id){
        TodoDto todo = todoService.getTodoById(id);
        if(Optional.ofNullable(todo).isPresent()) return ResponseEntity.ok(todo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getTodoById(){
        List<TodoDto> todos = todoService.getTodos();
        if(Optional.ofNullable(todos).isPresent()) return ResponseEntity.ok(todos);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories/{categoryId}/todos")
    public ResponseEntity<List<TodoDto>> getTodosByCategoryId(@PathVariable("categoryId") String categoryId){
        List<TodoDto> todos = todoService.getTodosByCategoryId(categoryId);
        if(Optional.ofNullable(todos).isPresent()) return ResponseEntity.ok(todos);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoDto> createTodo(@RequestBody CreateTodoRequest request, @RequestParam("categoryId") String categoryId){
        return ResponseEntity.ok(todoService.createTodo(request, categoryId));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoDto> createTodo(@RequestBody UpdateTodoRequest request){
        return ResponseEntity.ok(todoService.updateTodo(request));
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") String id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }
}
