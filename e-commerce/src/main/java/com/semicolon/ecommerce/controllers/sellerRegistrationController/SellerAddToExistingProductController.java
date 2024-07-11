package com.semicolon.ecommerce.controllers.sellerRegistrationController;

import com.semicolon.ecommerce.dtos.request.SellerAddToExistingProductRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.exceptions.UserLoginException;
import com.semicolon.ecommerce.services.product.SellerAddProductInStoreService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor

public class SellerAddToExistingProductController {
    private final SellerAddProductInStoreService sellerAddProductInStoreService;

    @PostMapping("addToAnExistingProduct")
    public ResponseEntity<ApiResponse> addToAnExistingProduct(@RequestBody SellerAddToExistingProductRequest sellerAddToExistingProductRequest) throws SellerException, ProductException, StoreException, UserLoginException {

        return new ResponseEntity<>(sellerAddProductInStoreService.addToExistingProductInStore(sellerAddToExistingProductRequest), HttpStatus.OK);
    }

}
