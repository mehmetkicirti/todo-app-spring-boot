package com.mehmetkicirti.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mehmetkicirti.todoapp.enums.TodoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo extends BaseModel{
    private String name;
    private TodoStatus status;
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;
    public Todo(LocalDateTime creationDate, String name, TodoStatus status, boolean isCompleted) {
        super(creationDate);
        this.name = name;
        this.status = status;
        this.isCompleted = isCompleted;
    }
    public Todo(String id, LocalDateTime creationDate, String name, TodoStatus status, boolean isCompleted) {
        super(id, creationDate);
        this.name = name;
        this.status = status;
        this.isCompleted = isCompleted;
    }

    public Todo(String id, LocalDateTime creationDate, String name, TodoStatus status, boolean isCompleted, Category category) {
        super(id, creationDate);
        this.name = name;
        this.status = status;
        this.isCompleted = isCompleted;
        this.category = category;
    }

    public Todo(String id, LocalDateTime creationDate, LocalDateTime updatedDate, String name, TodoStatus status, boolean isCompleted) {
        super(id, creationDate, updatedDate);
        this.name = name;
        this.status = status;
        this.isCompleted = isCompleted;
    }
}
