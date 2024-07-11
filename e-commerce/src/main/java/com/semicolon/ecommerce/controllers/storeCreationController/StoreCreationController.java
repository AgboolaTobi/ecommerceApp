package com.semicolon.ecommerce.controllers.storeCreationController;


import com.semicolon.ecommerce.dtos.request.StoreCreationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.store.StoreCreationService;
import com.semicolon.ecommerce.utils.ApiResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StoreCreationController {

    public final StoreCreationService storeCreationService;

    @PostMapping("createStore")

    public ResponseEntity<ApiResponse> createStore(@RequestBody StoreCreationRequest storeCreationRequest) throws SellerException {

        return new ResponseEntity<>(storeCreationService.createStore(storeCreationRequest), HttpStatus.CREATED);
    }
}
