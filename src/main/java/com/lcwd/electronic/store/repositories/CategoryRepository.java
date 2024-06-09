package com.lcwd.electronic.store.repositories;

import com.lcwd.electronic.store.dtos.ProjectionDTO;
import com.lcwd.electronic.store.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {


    @Query(value="select category_title from categories",nativeQuery = true)
    public List<ProjectionDTO> getCategoryTitle();








}
