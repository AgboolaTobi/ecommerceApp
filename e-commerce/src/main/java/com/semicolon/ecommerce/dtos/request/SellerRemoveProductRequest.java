package com.semicolon.ecommerce.dtos.request;

import com.semicolon.ecommerce.data.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class SellerRemoveProductRequest {
    private String sellerEmailAddress;
    private String password;
    private String productName;
    private ProductCategory productCategory;
    private int quantity;


}
