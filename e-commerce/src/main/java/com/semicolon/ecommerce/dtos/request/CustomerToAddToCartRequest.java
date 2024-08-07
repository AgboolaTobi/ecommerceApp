package com.semicolon.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor



public class CustomerToAddToCartRequest {
    private String customerEmail;
    private String productName;
    private int quantity;
    private String password;

}
