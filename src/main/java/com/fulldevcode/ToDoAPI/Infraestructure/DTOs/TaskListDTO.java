package com.fulldevcode.ToDoAPI.Infraestructure.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskListDTO {

    private Integer id;

    private String name;

    private String description;

    private Boolean state;

    private Integer categoryId;

    private String categoryName;

    public TaskListDTO (Integer id, String name, String description, Boolean state, Integer categoryId, String categoryName)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}
