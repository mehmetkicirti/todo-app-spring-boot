package com.mehmetkicirti.todoapp.repository;

import com.mehmetkicirti.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,String> {
    List<Todo> getTodosByCategory_Id(String categoryId);
}
