package com.example.demo2.services;

import com.example.demo2.models.dto.CategoriesDTO;
import com.example.demo2.models.entity.Categories;

import java.util.List;

public interface ICategoriesService {
    Categories addCategory(CategoriesDTO categoriesDTO);
    List<Categories> getAllCategories();
    Categories updateCategory(Long id, CategoriesDTO categoriesDTO);
    Categories getCategoryById(Long id);
    void deleteCategoryById(Long id);
}
