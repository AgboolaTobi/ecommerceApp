package com.semicolon.ecommerce.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class StoreCreationRequest {

    private String storeName;
    private String sellerEmailAddress;
    private String password;

}


