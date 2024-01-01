package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Cart;
import com.semicolon.ecommerce.data.models.Customer;
import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.dtos.request.CustomerToAddToCartRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.cart.CartService;
import com.semicolon.ecommerce.services.product.ProductService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class CustomerToAddToCartService {

    private final CustomerService customerService;
    private final ProductService productService;
    private final CartService cartService;

    public ApiResponse customerToAddToCart(CustomerToAddToCartRequest customerToAddToCartRequest) throws CustomerException {

        Customer customer = customerService.findCustomerByEmail(customerToAddToCartRequest.getCustomerEmail());
        if (customer ==null) throw new CustomerException(GenerateApiResponse.ACCOUNT_NOT_YET_CREATED);

        Cart existingCart = customer.getCart();


        Product inStockProduct = productService.findProductByName(customerToAddToCartRequest.getProductName());

        if (inStockProduct==null) throw new CustomerException(GenerateApiResponse.PRODUCT_IS_CURRENTLY_UNAVAILABLE);
        if (inStockProduct.getQuantity()< 1) throw new CustomerException(GenerateApiResponse.PRODUCT_IS_CURRENTLY_OUT_OF_STOCK);

        List<Product> listOfProducts =existingCart.getListOfProducts();


        if (listOfProducts ==null){

            listOfProducts = new ArrayList<>();
        }


        if (inStockProduct.getQuantity() < customerToAddToCartRequest.getQuantity()) throw new CustomerException(GenerateApiResponse.REQUIRED_QUANTITY_IS_UNAVAILABLE_AT_THE_MOMENT);

        listOfProducts.add(inStockProduct);


        existingCart.setListOfProducts(new ArrayList<>(listOfProducts));

        Cart updatedCart = cartService.save(existingCart);

        customer.setCart(updatedCart);

        customerService.save(customer);


        return GenerateApiResponse.added(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED_TO_CART);
    }

}
