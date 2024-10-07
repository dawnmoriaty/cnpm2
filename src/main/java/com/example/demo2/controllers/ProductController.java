package com.example.demo2.controllers;

import com.example.demo2.models.dto.ProductsDTO;
import com.example.demo2.models.entity.Categories;
import com.example.demo2.models.entity.Products;
import com.example.demo2.repositories.CategoryRepository;
import com.example.demo2.services.impl.FileService;
import com.example.demo2.services.impl.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private ProductsService productsService;
    private CategoryRepository categoryRepository;
    private FileService fileService;
    @GetMapping("/listProduct")
    public String listProduct(Model model) {
        List<Products> productsList = productsService.getAllProducts();
        model.addAttribute("productsList", productsList);
        return "listProduct";
    }
    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        ProductsDTO productsDTO = new ProductsDTO();
        model.addAttribute("products", productsDTO);
        List<Categories> categoriesList = categoryRepository.findAll();
        model.addAttribute("categories", categoriesList);
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute ProductsDTO productsDTO, Model model){
        Products products = productsService.addingProduct(productsDTO);
        model.addAttribute("products", products);
        return "redirect:/products/listProduct";
    }
    @GetMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, Model model){

        Products product = productsService.getProductById(id);
        ProductsDTO productDTO = ProductsDTO.builder()
                .productName(product.getProductName())
                .color(product.getColor())
                .price(product.getPrice())
                .status(product.getStatus())
                .size(product.getSize())
                .dateIn(product.getDateIn())
                .dateOut(product.getDateOut())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .build();
        List<Categories> categoriesList = categoryRepository.findAll();
        model.addAttribute("categoriesList", categoriesList);
        model.addAttribute("productsUpdate", productDTO);
        return "updateProduct";
    }
    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@ModelAttribute ProductsDTO productsDTO, @PathVariable Long id,Model model) {
        Products products = productsService.updateProduct(id, productsDTO);
        model.addAttribute("productsUpdate", products);
        return "redirect:/products/listProduct";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return "redirect:/products/listProduct";
    }
}
