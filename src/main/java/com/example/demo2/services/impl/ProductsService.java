package com.example.demo2.services.impl;

import com.example.demo2.models.dto.ProductsDTO;
import com.example.demo2.models.entity.Categories;
import com.example.demo2.models.entity.Products;
import com.example.demo2.repositories.CategoryRepository;
import com.example.demo2.repositories.ProductRepository;
import com.example.demo2.services.IProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductsService implements IProductsService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final FileService fileService;
    @Override
    public Products addingProduct(ProductsDTO productsDTO) {
        String image = fileService.uploadImage(productsDTO.getImage());
        Long categoryId = productsDTO.getCategoryId();
        System.out.println("Category ID: " + categoryId);
        Categories categories = categoryService.getCategoryById(productsDTO.getCategoryId());
        Products products = new Products();
        products.setProductName(productsDTO.getProductName());
        products.setDescription(productsDTO.getDescription());
        products.setImage(image);
        products.setCategories(categories);
        products.setPrice(productsDTO.getPrice());
        products.setQuantity(productsDTO.getQuantity());
        products.setDateIn(productsDTO.getDateIn());
        products.setDateOut(productsDTO.getDateOut());
        products.setColor(productsDTO.getColor());
        products.setSize(productsDTO.getSize());
        products.setStatus(productsDTO.getStatus());
        return productRepository.save(products);
    }

    @Override
    public Products updateProduct(Long productId, ProductsDTO productsDTO) {
        String image = fileService.uploadImage(productsDTO.getImage());
        Products products = productRepository.findById(productId).orElse(null);
        assert products != null;
        products.setProductName(productsDTO.getProductName());
        products.setPrice(productsDTO.getPrice());
        products.setColor(productsDTO.getColor());
        products.setQuantity(productsDTO.getQuantity());
        products.setDescription(productsDTO.getDescription());
        products.setSize(productsDTO.getSize());
        products.setStatus(productsDTO.getStatus());
        products.setImage(image);
        products.setDateIn(productsDTO.getDateIn());
        products.setDateOut(productsDTO.getDateOut());
        return productRepository.save(products);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Products getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public Products getProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }


}
