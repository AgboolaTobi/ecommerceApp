package com.semicolon.ecommerce.data.repositories;

import com.semicolon.ecommerce.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
