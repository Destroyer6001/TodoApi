package com.fulldevcode.ToDoAPI.Infraestructure.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {

    private Integer id;

    private String name;

    private String description;

    private Boolean state;

    private Integer categoryId;
}
