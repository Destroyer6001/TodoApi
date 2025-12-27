package com.fulldevcode.ToDoAPI.Infraestructure.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryListDTO {

    private Integer id;

    private String  name;

    private Long numTask;

    public CategoryListDTO (Integer id, String name, Long numTask)
    {
        this.id = id;
        this.name = name;
        this.numTask = numTask;
    }

}
