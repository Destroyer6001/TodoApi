package com.fulldevcode.ToDoAPI.Domain.Services;

import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.ApiResponseDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryListDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.Interface.ICategory;
import com.fulldevcode.ToDoAPI.Infraestructure.Models.CategoryEntity;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final ICategory categoryRespository;

    public CategoryService(ICategory categoryRepository)
    {
        this.categoryRespository = categoryRepository;
    }

    /**
     * Obtiene la lista completa de categorías registradas en el sistema.
     *
     * @return ApiResponseDTO con la lista de categorías
     */
    public ApiResponseDTO<List<CategoryListDTO>> GetAllCategories()
    {
        try
        {
            List<CategoryListDTO> Categories = this.categoryRespository.GetAllCategories();
            return ApiResponseDTO.Success("Se ha obtenido con exito la lista de categorias", Categories);
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
     * Obtiene una categoría específica por su identificador.
     *
     * @param id Identificador de la categoría
     * @return ApiResponseDTO con la categoría encontrada o un mensaje de error
     */
    public ApiResponseDTO<CategoryDTO> GetByIdCategory(Integer id)
    {
        try
        {
            Optional<CategoryEntity> Category = this.categoryRespository.findById(id);

            if (Category.isEmpty())
            {
                String message = "La categoria seleccionada no se encuentra registrada en el sistema";
                return  ApiResponseDTO.Error(message);
            }

            return ApiResponseDTO.Success("La category ha sido encontrada con exito", ResponseCategory(Category.get()));
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
     * Crea una nueva categoría en el sistema.
     *
     * @param categoryDTO DTO con la información de la categoría a crear
     * @return ApiResponseDTO con la categoría creada o un mensaje de error
     */
    public ApiResponseDTO<CategoryDTO> CreateCategory(CategoryDTO categoryDTO)
    {
        try
        {
            Optional<CategoryEntity> CategorySearch = this.categoryRespository.SearchCategoryByName(categoryDTO.getName());

            if (CategorySearch.isPresent())
            {
                String message = "Ya hay una categoria creada con este nombre";
                return ApiResponseDTO.Error(message);
            }

            CategoryEntity category = new CategoryEntity();
            category.setName(categoryDTO.getName());
            this.categoryRespository.save(category);

            return ApiResponseDTO.Success("Se ha creado con exito la nueva categoria", ResponseCategory(category));
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
     * Actualiza el nombre de una categoría existente.
     *
     * @param id Identificador de la categoría a actualizar
     * @param categoryDTO DTO con los nuevos datos de la categoría
     * @return ApiResponseDTO con la categoría actualizada o un mensaje de error
     */
    public ApiResponseDTO<CategoryDTO> UpdateCategory(Integer id, CategoryDTO categoryDTO)
    {
        try
        {
            Optional<CategoryEntity> CategorySearch = this.categoryRespository.SearchCategoryByNameAndId(categoryDTO.getName(), id);

            if (CategorySearch.isPresent())
            {
                String message = "Ya hay una categoria registrada en el sistema con ese nombre";
                return ApiResponseDTO.Error(message);
            }

            Optional<CategoryEntity> CategorySearchUpdate = this.categoryRespository.findById(id);

            if (CategorySearchUpdate.isEmpty())
            {
                String message = "La categoria seleccionada no se encuentra registra en el sistema";
                return ApiResponseDTO.Error(message);
            }

            CategoryEntity category = CategorySearchUpdate.get();
            category.setName(categoryDTO.getName());
            this.categoryRespository.save(category);

            return ApiResponseDTO.Success("La categoria ha sido actualizada con exito", ResponseCategory(category));
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
     * Elimina una categoría existente del sistema.
     *
     * @param id Identificador de la categoría a eliminar
     * @return ApiResponseDTO con la categoría eliminada o un mensaje de error
     */
    public ApiResponseDTO<CategoryDTO> DeleteCategory(Integer id)
    {
        try
        {
            Optional<CategoryEntity> Category = this.categoryRespository.findById(id);

            if (Category.isEmpty())
            {
                String message = "Lo sentimos pero la categoria seleccionada no se encuentra registrada en el sistema";
                return ApiResponseDTO.Error(message);
            }

            this.categoryRespository.delete(Category.get());
            return ApiResponseDTO.Success("La categoria ha sido eliminada con exito", ResponseCategory(Category.get()));
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
     * Mapea una entidad CategoryEntity a un CategoryDTO.
     *
     * @param category Entidad de categoría
     * @return DTO de categoría
     */
    private CategoryDTO ResponseCategory(CategoryEntity category)
    {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }
}
