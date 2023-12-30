package com.semicolon.ecommerce.controllers.productCreationController;


import com.semicolon.ecommerce.dtos.request.ProductCreationRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.services.product.ProductCreationService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor


public class ProductCreationController {
    public final ProductCreationService productCreationService;

    @PostMapping("createProduct")

    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductCreationRequest productCreationRequest) throws SellerException, ProductException, StoreException {

        return new ResponseEntity<>(productCreationService.createProduct(productCreationRequest), HttpStatus.CREATED);
    }

}
