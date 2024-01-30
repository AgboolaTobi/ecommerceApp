package com.semicolon.ecommerce.data.repositories;

import com.semicolon.ecommerce.data.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreName(String storeName);
}
