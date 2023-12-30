package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Cart;
import com.semicolon.ecommerce.data.models.Customer;
import com.semicolon.ecommerce.dtos.request.CustomerCreationRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.cart.CartService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class CustomerCreationService {
    private  final CustomerService customerService;
    private final CartService cartService;
    private final ModelMapper modelMapper;


    public ApiResponse registerCustomer(CustomerCreationRequest customerCreationRequest) throws CustomerException {
        boolean isRegistered = customerService.findCustomerByEmail(customerCreationRequest.getEmail())!= null;
        if (isRegistered) throw new CustomerException(GenerateApiResponse.CUSTOMER_REGISTRATION_DETAILS_ALREADY_EXIST);

        Customer customer = modelMapper.map(customerCreationRequest, Customer.class);
        Cart cart = new Cart();
        Cart savedCart = cartService.save(cart);

        customer.setCart(savedCart);
        customerService.save(customer);
        return GenerateApiResponse.created(GenerateApiResponse.ACCOUNT_SUCCESSFULLY_CREATED);
    }

}
