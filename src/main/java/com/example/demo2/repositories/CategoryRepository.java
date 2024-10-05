package com.example.demo2.repositories;

import com.example.demo2.models.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories,Long> {
    Categories findByCategoryName(String categoryName);
}
