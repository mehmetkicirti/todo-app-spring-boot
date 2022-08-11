package com.mehmetkicirti.todoapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseModel{
    private String name;
    public Category(LocalDateTime creationDate, String name){
        super(creationDate);
        this.name = name;
    }
    public Category(String id, LocalDateTime creationDate, String name){
        super(id,creationDate);
        this.name = name;
    }

    public Category(String id, LocalDateTime creationDate, @Nullable LocalDateTime updatedDate, String name){
        super(id,creationDate, updatedDate);
        this.name = name;
    }
}
