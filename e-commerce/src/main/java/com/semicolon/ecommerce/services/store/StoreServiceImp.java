package com.semicolon.ecommerce.services.store;

import com.semicolon.ecommerce.data.models.Store;

import com.semicolon.ecommerce.data.repositories.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StoreServiceImp implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store save(Store store) {
        return  storeRepository.save(store);
    }


}
