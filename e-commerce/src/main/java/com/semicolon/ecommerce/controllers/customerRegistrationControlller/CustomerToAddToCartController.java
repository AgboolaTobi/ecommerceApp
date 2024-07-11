package com.semicolon.ecommerce.controllers.customerRegistrationControlller;

import com.semicolon.ecommerce.dtos.request.CustomerToAddToCartRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.customer.CustomerToAddToCartService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class CustomerToAddToCartController {

    private final CustomerToAddToCartService customerToAddToCartService;

    @PostMapping("addToCart")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody  CustomerToAddToCartRequest customerToAddToCartRequest) throws CustomerException {
        return new ResponseEntity<>(customerToAddToCartService.customerToAddToCart(customerToAddToCartRequest), HttpStatus.OK);
    }

}
