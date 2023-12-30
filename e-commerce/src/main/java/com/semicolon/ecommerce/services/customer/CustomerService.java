package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Customer findCustomerByEmail(String email);

}
