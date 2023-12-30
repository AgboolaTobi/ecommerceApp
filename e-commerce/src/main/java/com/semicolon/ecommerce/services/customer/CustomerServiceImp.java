package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Customer;
import com.semicolon.ecommerce.data.repositories.CustomerRepository;
import com.semicolon.ecommerce.data.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;



    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
