package com.semicolon.ecommerce.data.repositories;

import com.semicolon.ecommerce.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
