package com.fulldevcode.ToDoAPI.Infraestructure.Interface;

import com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryListDTO;
import com.fulldevcode.ToDoAPI.Infraestructure.Models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategory extends JpaRepository<CategoryEntity, Integer> {

    @Query("""
            SELECT new com.fulldevcode.ToDoAPI.Infraestructure.DTOs.CategoryListDTO(
                c.id,
                c.name,
                COUNT(t.id)
            )
            FROM CategoryEntity c
            LEFT JOIN c.tasks t
            GROUP BY c.id, c.name
            """)
    List<CategoryListDTO> GetAllCategories();

    @Query("""
            SELECT c
            FROM CategoryEntity c
            WHERE c.name = :name
            """)
    Optional<CategoryEntity> SearchCategoryByName(@Param("name") String name);

    @Query("""
            SELECT c
            FROM CategoryEntity c
            WHERE c.name = :name AND c.id != :id
            """)
    Optional<CategoryEntity> SearchCategoryByNameAndId (@Param("name") String name, @Param("id") Integer id);

}
