package com.semicolon.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerCreationRequest {
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;


}
