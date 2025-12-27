package com.fulldevcode.ToDoAPI.Aplication.Controllers;

import com.fulldevcode.ToDoAPI.Domain.Services.CategoryService;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.ApiResponseDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryListDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas
 * con las categorías del sistema.
 * Expone endpoints para:
 * - Consultar todas las categorías.
 * - Obtener el detalle de una categoría.
 * - Crear, actualizar y eliminar categorías.
 * Todas las respuestas siguen el formato estándar ApiResponseDTO,
 * garantizando consistencia en la comunicación con el cliente.
 */
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    /**
     * Obtiene la lista de todas las categorías registradas en el sistema.
     *
     * @return ApiResponseDTO que contiene:
     *         - Una lista de categorías (CategoryListDTO) si la operación es exitosa.
     *         - Un mensaje de error en caso de que ocurra una excepción.
     * Método HTTP: GET
     * Endpoint: /api/categories
     */
    @GetMapping
    public ApiResponseDTO<List<CategoryListDTO>> IndexCategory()
    {
        ApiResponseDTO<List<CategoryListDTO>> categories = this.categoryService.GetAllCategories();
        return categories;
    }

    /**
     * Obtiene la información detallada de una categoría a partir de su identificador.
     *
     * @param id Identificador único de la categoría.
     * @return ApiResponseDTO que contiene:
     *         - El detalle de la categoría (CategoryDTO) si existe.
     *         - Un mensaje de error si la categoría no se encuentra registrada.
     * Método HTTP: GET
     * Endpoint: /api/categories/{id}
     */
    @GetMapping("/{id}")
    public ApiResponseDTO<CategoryDTO> SearchByIdCategory(@PathVariable Integer id)
    {
        ApiResponseDTO<CategoryDTO> category = this.categoryService.GetByIdCategory(id);
        return category;
    }

    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param categoryDTO Objeto DTO que contiene los datos de la categoría a crear.
     * @return ApiResponseDTO que contiene:
     *         - La categoría creada (CategoryDTO) si la operación es exitosa.
     *         - Un mensaje de error si ya existe una categoría con el mismo nombre
     *           o si los datos enviados son inválidos.
     * Método HTTP: POST
     * Endpoint: /api/categories
     */
    @PostMapping
    public ApiResponseDTO<CategoryDTO> CreateCategory(@RequestBody CategoryDTO categoryDTO)
    {
        ApiResponseDTO<CategoryDTO> category = this.categoryService.CreateCategory(categoryDTO);
        return category;
    }

    /**
     * Actualiza la información de una categoría existente.
     *
     * @param id Identificador único de la categoría a actualizar.
     * @param categoryDTO Objeto DTO con la información actualizada de la categoría.
     * @return ApiResponseDTO que contiene:
     *         - La categoría actualizada (CategoryDTO) si la operación es exitosa.
     *         - Un mensaje de error si la categoría no existe
     *           o si ya existe otra categoría con el mismo nombre.
     * Método HTTP: PUT
     * Endpoint: /api/categories/{id}
     */
    @PutMapping("/{id}")
    public ApiResponseDTO<CategoryDTO> UpdateCategory (@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO)
    {
        ApiResponseDTO<CategoryDTO> category = this.categoryService.UpdateCategory(id, categoryDTO);
        return category;
    }

    /**
     * Elimina una categoría del sistema a partir de su identificador.
     *
     * @param id Identificador único de la categoría a eliminar.
     * @return ApiResponseDTO que contiene:
     *         - La información de la categoría eliminada (CategoryDTO) si la operación es exitosa.
     *         - Un mensaje de error si la categoría no se encuentra registrada en el sistema.
     * Método HTTP: DELETE
     * Endpoint: /api/categories/{id}
     */
    @DeleteMapping("/{id}")
    public ApiResponseDTO<CategoryDTO> DeleteCategory(@PathVariable Integer id)
    {
        ApiResponseDTO<CategoryDTO> category = this.categoryService.DeleteCategory(id);
        return category;
    }
}
