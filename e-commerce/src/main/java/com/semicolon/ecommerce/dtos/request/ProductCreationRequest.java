package com.semicolon.ecommerce.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCreationRequest {

    private String sellerEmailAddress;
    private String productName;
    private int quantity;
    private double price;
    private String productCategory;
}
