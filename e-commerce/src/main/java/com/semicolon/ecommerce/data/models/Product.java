package com.semicolon.ecommerce.data.models;

import com.semicolon.ecommerce.data.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private BigDecimal price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
}
