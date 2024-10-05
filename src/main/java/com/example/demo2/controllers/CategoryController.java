package com.example.demo2.controllers;

import com.example.demo2.models.dto.CategoriesDTO;
import com.example.demo2.models.entity.Categories;
import com.example.demo2.services.impl.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/listCategory")
    public String listCategory(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "listCategory";
    }
    @GetMapping("/addCategory")
    public String addingCategory(Model model,Categories categories)
    {
        model.addAttribute("categories", categories);
        return "addCategory";
    }
    @PostMapping("/addCategory")
    public String addingCategory(@ModelAttribute CategoriesDTO categoriesDTO, Model model){
        Categories category = categoryService.addCategory(categoriesDTO);
        model.addAttribute("categories", category);
        return "redirect:/categories/listCategory";
    }

    @GetMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable Long id, Model model){
        Categories category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "updateCategory";
    }
    @PostMapping("/updateCategory/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoriesDTO categoriesDTO, Model model){
        Categories category = categoryService.updateCategory(id, categoriesDTO);
        model.addAttribute("category", category);
        return "redirect:/categories/listCategory";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return "redirect:/categories/listCategory";
    }
}
