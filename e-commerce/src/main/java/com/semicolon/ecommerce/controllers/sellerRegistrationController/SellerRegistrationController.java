package com.semicolon.ecommerce.controllers.sellerRegistrationController;


import com.semicolon.ecommerce.dtos.request.SellerRegistrationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.seller.SellerRegistrationService;
import com.semicolon.ecommerce.utils.ApiResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class SellerRegistrationController {

    private final SellerRegistrationService sellerRegistrationService;

    @PostMapping("registerSeller")
    public ResponseEntity<ApiResponse> registerSeller(@RequestBody SellerRegistrationRequest sellerRegistrationRequest) throws SellerException {
        return new ResponseEntity<>(sellerRegistrationService.register(sellerRegistrationRequest), HttpStatus.CREATED);
    }
}
