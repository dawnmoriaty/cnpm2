package com.example.demo2.services.impl;

import com.example.demo2.models.dto.CategoriesDTO;
import com.example.demo2.models.entity.Categories;
import com.example.demo2.repositories.CategoryRepository;
import com.example.demo2.services.ICategoriesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService implements ICategoriesService {
    private final CategoryRepository categoryRepository;
    @Override
    public Categories addCategory(CategoriesDTO categoriesDTO) {
        Categories categories = new Categories();
        categories.setCategoryName(categoriesDTO.getCategoryName());
        Categories savedCategory = categoryRepository.save(categories);

        log.info("New category '{}' created with ID:",categoriesDTO.getCategoryName(),savedCategory.getCategoryId());
        return savedCategory;
    }

    @Override
    public List<Categories> getAllCategories() {
        List<Categories> categoriesList =  categoryRepository.findAll();
        if(categoriesList.isEmpty()) {
            log.warn("No categories found in the database.");
            return null;
        }else{
            log.info("Retrieved {} categories from the database.", categoriesList.size());
            return categoriesList;
        }
    }

    @Override
    public Categories updateCategory(Long id, CategoriesDTO categoriesDTO) {
        Optional<Categories> opt = categoryRepository.findById(id);
        if (opt.isPresent()){
            Categories categories = opt.get();
            categories.setCategoryName(categoriesDTO.getCategoryName());
            Categories updatedCategory = categoryRepository.save(categories);

            log.info("Category with ID: {} updated to new name: '{}'",id, updatedCategory.getCategoryName());
            return updatedCategory;
        }else {
            log.error("Category with ID: {} not found for update.", id);
            return null;
        }
    }

    @Override
    public Categories getCategoryById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Category ID must not be null");
        }
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
