package com.mehmetkicirti.todoapp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseModel {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private LocalDateTime creationDate;
    @Nullable
    private LocalDateTime updatedDate;

    protected BaseModel(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    protected BaseModel(String id, LocalDateTime creationDate){
        this.id = id;
        this.creationDate = creationDate;
    }

    protected BaseModel(LocalDateTime creationDate, @Nullable LocalDateTime updatedDate){
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

    protected BaseModel(String id, LocalDateTime creationDate, @Nullable LocalDateTime updatedDate){
        this.id = id;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }
}
