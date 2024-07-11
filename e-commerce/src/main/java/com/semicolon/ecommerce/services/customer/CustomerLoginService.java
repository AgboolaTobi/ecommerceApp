package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Customer;
import com.semicolon.ecommerce.dtos.request.CustomerLoginRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerLoginService {
    private final CustomerService customerService;

    public ApiResponse customerLogin(CustomerLoginRequest customerLoginRequest) throws CustomerException {

        Customer existingCustomer = customerService.findCustomerByEmail(customerLoginRequest.getCustomerEmailAddress());
        if (existingCustomer==null) throw  new CustomerException(GenerateApiResponse.CUSTOMER_ACCOUNT_NOT_FOUND);

        String password = existingCustomer.getPassword();
        if (!password.equals(customerLoginRequest.getPassword()))throw new CustomerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);

        existingCustomer.setLocked(false);
        customerService.save(existingCustomer);

        return  GenerateApiResponse.login(existingCustomer.getName() + " , you have " + GenerateApiResponse.SUCCESSFULLY_LOGGED_IN);
    }
}
