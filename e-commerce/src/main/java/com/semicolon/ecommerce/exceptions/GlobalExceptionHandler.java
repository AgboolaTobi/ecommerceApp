package com.semicolon.ecommerce.exceptions;


import com.semicolon.ecommerce.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SellerException.class)
    public ResponseEntity<ApiResponse> sellerException( SellerException sellerException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(sellerException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiResponse> productException(ProductException productException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(productException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<ApiResponse> CustomerException(CustomerException customerException){
        return new ResponseEntity<>(ApiResponse.builder()
                .data(customerException.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .build(), HttpStatus.BAD_REQUEST);
    }
}
