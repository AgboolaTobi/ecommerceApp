package com.semicolon.ecommerce.controllers.sellerRegistrationController;

import com.semicolon.ecommerce.dtos.request.SellerLoginRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.seller.SellerLoginService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class SellerLoginController {
    private final SellerLoginService sellerLoginService;

    @PostMapping("sellerLogin")
    public ResponseEntity<ApiResponse> login(@RequestBody SellerLoginRequest sellerLoginRequest) throws SellerException {
        return new ResponseEntity<>(sellerLoginService.sellerLogin(sellerLoginRequest), HttpStatus.FOUND);
    }
}
