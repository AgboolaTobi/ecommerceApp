package com.semicolon.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationRequest {

    private String sellerName;
    private String emailAddress;
    private  String address;
}
