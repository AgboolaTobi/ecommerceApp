package com.semicolon.ecommerce.controllers.productCreationController;


import com.semicolon.ecommerce.dtos.request.ProductCreationRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.services.product.SellerCreateProductInStoreService;
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
    public final SellerCreateProductInStoreService sellerCreateProductInStoreService;

    @PostMapping("addProductToStore")

    public ResponseEntity<ApiResponse> addProductToStore(@RequestBody ProductCreationRequest productCreationRequest) throws SellerException, ProductException, StoreException {

        return new ResponseEntity<>(sellerCreateProductInStoreService.createProductInStore(productCreationRequest), HttpStatus.CREATED);
    }

}
