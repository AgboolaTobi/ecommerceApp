package com.semicolon.ecommerce.controllers.customerRegistrationControlller;

import com.semicolon.ecommerce.dtos.request.CustomerCreationRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.customer.CustomerCreationService;

import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class CustomerRegistrationController {
    private final CustomerCreationService customerCreationService;

    @PostMapping("registerCustomer")
    public ResponseEntity<ApiResponse> registerCustomer(@RequestBody CustomerCreationRequest customerCreationRequest) throws CustomerException {
        return new ResponseEntity<>(customerCreationService.registerCustomer(customerCreationRequest), HttpStatus.CREATED);
    }
}
