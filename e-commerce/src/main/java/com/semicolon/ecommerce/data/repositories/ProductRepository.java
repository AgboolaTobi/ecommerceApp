package com.semicolon.ecommerce.data.repositories;

import com.semicolon.ecommerce.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductName(String productName);

}
