package com.fulldevcode.ToDoAPI.Domain.Services;

import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.ApiResponseDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskListDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.Interface.ICategory;
import com.fulldevcode.ToDoAPI.Infraestructure.Interface.ITask;
import com.fulldevcode.ToDoAPI.Infraestructure.Models.CategoryEntity;
import com.fulldevcode.ToDoAPI.Infraestructure.Models.TaskEntity;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final ITask taskRepository;
    private final ICategory categoryRepository;

    public TaskService(ITask taskRepository, ICategory categoryRepository)
    {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Obtiene la lista completa de tareas registradas en el sistema.
     *
     * @return ApiResponseDTO con una lista de tareas en formato TaskListDTO.
     *         Retorna un mensaje de éxito o error según el resultado de la operación.
     */
    public ApiResponseDTO<List<TaskListDTO>> IndexTask()
    {
        try
        {
            List<TaskListDTO> Tasks = this.taskRepository.GetAllTask();
            return ApiResponseDTO.Success("Se ha obtenido con exito la lista de tareas", Tasks);
        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Obtiene el detalle de una tarea específica a partir de su identificador.
     *
     * @param id Identificador único de la tarea.
     * @return ApiResponseDTO con el detalle de la tarea en formato TaskDTO.
     *         Retorna error si la tarea no existe.
     */
    public ApiResponseDTO<TaskDTO> GetByIdTask (Integer id)
    {
        try
        {
            Optional<TaskEntity> Task = this.taskRepository.findById(id);

            if (Task.isEmpty())
            {
                String message = "La tarea seleccionada no se encuentra registrada en el sistema";
                return ApiResponseDTO.Error(message);
            }

            return ApiResponseDTO.Success("Se ha obtenido con exito el detalle de la tarea", ResponseTask(Task.get()));
        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Crea una nueva tarea y la asocia a una categoría existente.
     * La tarea se crea con estado inicial en falso (pendiente).
     *
     * @param taskDTO Objeto DTO con la información de la tarea a crear.
     * @return ApiResponseDTO con la tarea creada en formato TaskDTO.
     *         Retorna error si la categoría no existe.
     */
    public ApiResponseDTO<TaskDTO> CreateTask (TaskDTO taskDTO)
    {
        try
        {
            Optional<CategoryEntity> Category = this.categoryRepository.findById(taskDTO.getCategoryId());

            if (Category.isEmpty())
            {
                String message = "La categoria seleccionada no se encuentra registrada en el sistema";
                return ApiResponseDTO.Error(message);
            }

            TaskEntity Task = new TaskEntity();
            Task.setState(false);
            Task.setName(taskDTO.getName());
            Task.setDescription(taskDTO.getDescription());
            Task.setCategory(Category.get());
            this.taskRepository.save(Task);

            return ApiResponseDTO.Success("Se ha creado con exito la tarea", ResponseTask(Task));
        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Actualiza la información de una tarea existente.
     * Permite modificar el nombre, la descripción y la categoría asociada.
     *
     * @param id      Identificador único de la tarea a actualizar.
     * @param taskDTO Objeto DTO con la nueva información de la tarea.
     * @return ApiResponseDTO con la tarea actualizada en formato TaskDTO.
     *         Retorna error si la tarea o la categoría no existen.
     */
    public ApiResponseDTO<TaskDTO> UpdateTask(Integer id, TaskDTO taskDTO)
    {
        try
        {
            Optional<TaskEntity> TaskSearch = this.taskRepository.findById(id);

            if(TaskSearch.isEmpty())
            {
                String message = "El producto seleccionado no ha sido registrado en el sistema";
                return ApiResponseDTO.Error(message);
            }

            Optional<CategoryEntity> Category = this.categoryRepository.findById(taskDTO.getCategoryId());

            if (Category.isEmpty())
            {
                String message = "La categoria seleccionada no ha sido registrada en el sistema";
                return  ApiResponseDTO.Error(message);
            }

            TaskEntity Task = TaskSearch.get();

            Task.setName(taskDTO.getName());
            Task.setDescription(taskDTO.getDescription());
            Task.setCategory(Category.get());
            this.taskRepository.save(Task);

            return ApiResponseDTO.Success("Se ha actualizado con exito la tarea", ResponseTask(Task));

        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Cambia el estado de una tarea.
     * Si la tarea está pendiente, pasa a completada y viceversa.
     *
     * @param id Identificador único de la tarea.
     * @return ApiResponseDTO con la tarea actualizada y su nuevo estado.
     *         Retorna error si la tarea no existe.
     */
    public ApiResponseDTO<TaskDTO> ChangeState(Integer id)
    {
        try
        {
            Optional<TaskEntity> TaskSearch = this.taskRepository.findById(id);

            if (TaskSearch.isEmpty())
            {
                String message = "El producto seleccionado no se encuentra registrado en el sistema";
                return ApiResponseDTO.Error(message);
            }

            TaskEntity Task = TaskSearch.get();
            Boolean state = !Task.getState();
            Task.setState(state);
            this.taskRepository.save(Task);

            return ApiResponseDTO.Success("Se ha actualizado con exito el estado de la tarea", ResponseTask(Task));
        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Elimina una tarea del sistema a partir de su identificador.
     *
     * @param id Identificador único de la tarea a eliminar.
     * @return ApiResponseDTO con la información de la tarea eliminada.
     *         Retorna error si la tarea no existe.
     */
    public ApiResponseDTO<TaskDTO> DeleteTask(Integer id)
    {
        try
        {
            Optional<TaskEntity> Task = this.taskRepository.findById(id);

            if (Task.isEmpty())
            {
                String message = "Lo sentimos pero la tarea seleccionada no esta registrada en el sistema";
                return ApiResponseDTO.Error(message);
            }

            this.taskRepository.delete(Task.get());
            return ApiResponseDTO.Success("La tarea ha sido eliminada con exito", ResponseTask(Task.get()));
        }
        catch (PersistenceException | IllegalArgumentException ex)
        {
            String message = "Ha ocurrido un error" + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
        catch (Exception ex)
        {
            String message = "Ha ocurrido un error " + ex.getMessage();
            return  ApiResponseDTO.Error(message);
        }
    }

    /**
     * Convierte una entidad TaskEntity en un objeto TaskDTO.
     * Este método centraliza el mapeo y evita exponer entidades
     * directamente hacia la capa de presentación.
     *
     * @param task Entidad TaskEntity.
     * @return Objeto TaskDTO con la información de la tarea.
     */
    private TaskDTO ResponseTask(TaskEntity task)
    {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setState(task.getState());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setCategoryId(task.getCategory().getId());
        return taskDTO;
    }
}
