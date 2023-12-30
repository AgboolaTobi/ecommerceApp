package com.semicolon.ecommerce.data.repositories;

import com.semicolon.ecommerce.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findByEmailAddress(String emailAddress);
}
