package com.semicolon.ecommerce.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerLoginRequest {

    private String customerEmailAddress;
    private String password;
}
