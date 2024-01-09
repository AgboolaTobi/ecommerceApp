package com.semicolon.ecommerce.services.customer;

import com.semicolon.ecommerce.data.models.Cart;
import com.semicolon.ecommerce.data.models.Customer;
import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.dtos.request.PaymentRequest;
import com.semicolon.ecommerce.exceptions.CustomerException;
import com.semicolon.ecommerce.services.cart.CartService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class PaymentOfSelectedProductService {
    private final CustomerService customerService;
    private final CartService cartService;

    public ApiResponse payForCartedProducts(PaymentRequest paymentRequest) throws CustomerException {
        Customer customer = customerService.findCustomerByEmail(paymentRequest.getCustomerEmail());
        if (customer ==null) throw new CustomerException(GenerateApiResponse.ACCOUNT_NOT_YET_CREATED);

        Cart cart = customer.getCart();

        if (cart==null) throw new CustomerException(GenerateApiResponse.CUSTOMER_DOES_NOT_HAVE_A_CART_YET);

        List<Product> listOfProductInCart = cart.getListOfProducts();

        if (listOfProductInCart==null) throw new CustomerException(GenerateApiResponse.NO_PRODUCT_IN_CART);


        BigDecimal totalPriceOfProductInCart = BigDecimal.ZERO;
        for (Product eachProduct: listOfProductInCart){
            int quantity = eachProduct.getQuantity();
            BigDecimal price = eachProduct.getPrice();


            BigDecimal subTotal = (price.multiply(BigDecimal.valueOf(quantity)));

            totalPriceOfProductInCart = totalPriceOfProductInCart.add(subTotal);

        }


        cart.setListOfProducts(new ArrayList<>(listOfProductInCart));

        Cart pricedCart = cartService.save(cart);

        customer.setCart(pricedCart);

        customerService.save(customer);


        return GenerateApiResponse.added(GenerateApiResponse.TOTAL_PRICE_OF_PRODUCT_IN_CART + totalPriceOfProductInCart);

    }

}
