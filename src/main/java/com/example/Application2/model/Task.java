package com.example.Application2.model;

import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;

    public Task() {
    }

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    

    public Long getId() {
        return id != null ? id : 0L; 
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
