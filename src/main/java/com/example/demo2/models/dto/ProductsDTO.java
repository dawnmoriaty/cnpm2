package com.example.demo2.models.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductsDTO {
    private String productName;
    private String description;
    private Integer quantity;
    private Integer size;
    private String color;
    private MultipartFile image;
    private Double price;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateIn;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOut;
    private Long categoryId;
}
