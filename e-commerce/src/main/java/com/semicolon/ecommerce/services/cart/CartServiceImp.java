package com.semicolon.ecommerce.services.cart;

import com.semicolon.ecommerce.data.models.Cart;

import com.semicolon.ecommerce.data.repositories.CartRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImp implements CartService{
    private final CartRepository cartRepository;


    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }



}
