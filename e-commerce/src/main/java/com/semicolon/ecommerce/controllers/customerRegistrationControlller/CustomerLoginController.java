package com.semicolon.ecommerce.controllers.customerRegistrationControlller;

import com.semicolon.ecommerce.dtos.request.CustomerLoginRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.customer.CustomerLoginService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class CustomerLoginController {
    private final CustomerLoginService customerLoginService;

    @PostMapping("login")

    public ResponseEntity<ApiResponse> login(@RequestBody CustomerLoginRequest customerLoginRequest) throws CustomerException {
        return new ResponseEntity<>(customerLoginService.customerLogin(customerLoginRequest), HttpStatus.FOUND);
    }
}
