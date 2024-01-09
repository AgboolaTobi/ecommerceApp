package com.semicolon.ecommerce.controllers.sellerRegistrationController;

import com.semicolon.ecommerce.dtos.request.SellerRemoveProductRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.services.seller.SellerRemoveProductService;
import com.semicolon.ecommerce.utils.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ecommerce/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class SellerRemoveProductController {
    private final SellerRemoveProductService sellerRemoveProductService;
    @PostMapping("removeProductFromStore")
    public ResponseEntity<ApiResponse> removeProductFromStore(@RequestBody SellerRemoveProductRequest sellerRemoveProductRequest) throws SellerException, ProductException, StoreException {

        return new ResponseEntity<>(sellerRemoveProductService.removeProductFromStore(sellerRemoveProductRequest), HttpStatus.OK);
    }
}
