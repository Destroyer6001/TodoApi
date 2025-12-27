package com.fulldevcode.ToDoAPI.Infraestructure.Interface;

import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskListDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.Models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITask extends JpaRepository<TaskEntity, Integer> {

     @Query("""
             SELECT new com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskListDTO(
                t.id,
                t.name,
                t.description,
                t.state,
                t.category.id,
                t.category.name
             )
             FROM TaskEntity t
             """)
    List<TaskListDTO> GetAllTask();
}
