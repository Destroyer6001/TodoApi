package com.fulldevcode.ToDoAPI.Aplication.Controllers;

import com.fulldevcode.ToDoAPI.Domain.Services.TaskService;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.ApiResponseDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.TaskListDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas
 * con las tareas del sistema.
 * Expone endpoints para:
 * - Consultar todas las tareas.
 * - Obtener el detalle de una tarea.
 * - Crear, actualizar y eliminar tareas.
 * - Cambiar el estado de una tarea (completada / pendiente).
 * Todas las respuestas siguen el formato estándar ApiResponseDTO,
 * garantizando consistencia y claridad en la comunicación con el cliente.
 */
@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    /**
     * Obtiene la lista de todas las tareas registradas en el sistema.
     *
     * @return ApiResponseDTO que contiene:
     *         - Una lista de tareas (TaskListDTO) si la operación es exitosa.
     *         - Un mensaje de error en caso de que ocurra una excepción.
     * Método HTTP: GET
     * Endpoint: /api/tasks
     */
    @GetMapping
    public ApiResponseDTO<List<TaskListDTO>> IndexTask()
    {
        ApiResponseDTO<List<TaskListDTO>> tasks = this.taskService.IndexTask();
        return tasks;
    }

    /**
     * Obtiene la información detallada de una tarea a partir de su identificador.
     *
     * @param id Identificador único de la tarea.
     * @return ApiResponseDTO que contiene:
     *         - El detalle de la tarea (TaskDTO) si existe.
     *         - Un mensaje de error si la tarea no se encuentra registrada.
     * Método HTTP: GET
     * Endpoint: /api/tasks/{id}
     */
    @GetMapping("/{id}")
    public ApiResponseDTO<TaskDTO> GetByIdTask(@PathVariable Integer id)
    {
        ApiResponseDTO<TaskDTO> task = this.taskService.GetByIdTask(id);
        return task;
    }

    /**
     * Crea una nueva tarea en el sistema.
     *
     * @param taskDTO Objeto DTO que contiene los datos de la tarea a crear.
     * @return ApiResponseDTO que contiene:
     *         - La tarea creada (TaskDTO) si la operación es exitosa.
     *         - Un mensaje de error si la categoría asociada no existe
     *           o si los datos enviados son inválidos.
     * Método HTTP: POST
     * Endpoint: /api/tasks
     */
    @PostMapping
    public ApiResponseDTO<TaskDTO> CreateTask (@RequestBody TaskDTO taskDTO)
    {
        ApiResponseDTO<TaskDTO> task = this.taskService.CreateTask(taskDTO);
        return task;
    }

    /**
     * Actualiza la información de una tarea existente.
     *
     * @param id Identificador único de la tarea a actualizar.
     * @param taskDTO Objeto DTO con la información actualizada de la tarea.
     * @return ApiResponseDTO que contiene:
     *         - La tarea actualizada (TaskDTO) si la operación es exitosa.
     *         - Un mensaje de error si la tarea o la categoría asociada no existen.
     * Método HTTP: PUT
     * Endpoint: /api/tasks/{id}
     */
    @PutMapping("/{id}")
    public ApiResponseDTO<TaskDTO> UpdateTask(@PathVariable Integer id, @RequestBody TaskDTO taskDTO)
    {
        ApiResponseDTO<TaskDTO> task = this.taskService.UpdateTask(id, taskDTO);
        return task;
    }

    /**
     * Cambia el estado de una tarea.
     * Permite alternar el estado entre pendiente y completada.
     *
     * @param id Identificador único de la tarea.
     * @return ApiResponseDTO que contiene:
     *         - La tarea con el estado actualizado (TaskDTO) si la operación es exitosa.
     *         - Un mensaje de error si la tarea no se encuentra registrada.
     * Método HTTP: PATCH
     * Endpoint: /api/tasks/{id}
     */
    @PatchMapping("/{id}")
    public ApiResponseDTO<TaskDTO> ChangeStateTask(@PathVariable Integer id)
    {
        ApiResponseDTO<TaskDTO> task = this.taskService.ChangeState(id);
        return task;
    }

    /**
     * Elimina una tarea del sistema a partir de su identificador.
     *
     * @param id Identificador único de la tarea a eliminar.
     * @return ApiResponseDTO que contiene:
     *         - La información de la tarea eliminada (TaskDTO) si la operación es exitosa.
     *         - Un mensaje de error si la tarea no se encuentra registrada.
     * Método HTTP: DELETE
     * Endpoint: /api/tasks/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponseDTO<TaskDTO> DeleteTask(@PathVariable Integer id)
    {
        ApiResponseDTO<TaskDTO> task = this.taskService.DeleteTask(id);
        return task;
    }
}
