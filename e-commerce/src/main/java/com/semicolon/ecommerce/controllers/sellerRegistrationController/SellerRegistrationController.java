package com.semicolon.ecommerce.controllers.sellerRegistrationController;


import com.semicolon.ecommerce.dtos.request.RegistrationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.registration.RegistrationService;
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

    private final RegistrationService registrationService;

    @PostMapping("register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest registrationRequest) throws SellerException {
        return new ResponseEntity<>(registrationService.register(registrationRequest), HttpStatus.CREATED);
    }
}
