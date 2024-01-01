package com.semicolon.ecommerce.controllers.customerRegistrationControlller;

import com.semicolon.ecommerce.dtos.request.PaymentRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.customer.PaymentOfSelectedProductService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor


public class PaymentOfSelectedProductController {

    private final PaymentOfSelectedProductService paymentOfSelectedProductService;

    @PostMapping("getTotalCostOfProductInCart")
    public ResponseEntity<ApiResponse> getTotalCostOfProductInCart(@RequestBody PaymentRequest paymentRequest) throws CustomerException {

        return new ResponseEntity<>(paymentOfSelectedProductService.payForCartedProducts(paymentRequest), HttpStatus.OK);
    }
}
