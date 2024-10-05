package com.example.demo2.services;

import com.example.demo2.models.dto.ProductsDTO;
import com.example.demo2.models.entity.Products;

import java.util.List;

public interface IProductsService {
    Products addingProduct(ProductsDTO productsDTO);
    Products updateProduct(Long productId, ProductsDTO productsDTO);
    void deleteProduct(Long productId);
    List<Products> getAllProducts();
    Products getProductById(Long productId);
    Products getProductByName(String productName);
}
