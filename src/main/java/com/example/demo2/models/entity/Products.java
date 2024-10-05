package com.example.demo2.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="product_name")
    private String productName;
    @Column(name="description")
    private String description;
    @Column(name="quantity")
    private Integer quantity;
    @Column(name="size")
    private Integer size;
    @Column(name="color")
    private String color;
    @Column(name="image")
    private String image;
    @Column(name="price")
    private Double price;
    @Column(name="status")
    private String status;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name="date_in")
    private Date dateIn;
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "date_out")
    private Date dateOut;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    Categories categories;
}
