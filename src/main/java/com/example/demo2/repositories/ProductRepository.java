package com.example.demo2.repositories;

import com.example.demo2.models.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Long> {
    Products findByProductName(String productName);
}
