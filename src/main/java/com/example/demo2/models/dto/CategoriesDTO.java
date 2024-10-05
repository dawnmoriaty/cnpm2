package com.example.demo2.models.dto;

import com.example.demo2.models.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriesDTO {
    private String categoryName;
    private String description;
    List<Products> product;
}
