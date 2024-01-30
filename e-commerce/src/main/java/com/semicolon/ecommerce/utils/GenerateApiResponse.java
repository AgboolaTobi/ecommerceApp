package com.semicolon.ecommerce.utils;

import org.springframework.http.HttpStatus;

public class GenerateApiResponse {


    public static final String SELLER_ALREADY_EXIST ="Seller with this information already exist" ;
    public static final String ACCOUNT_SUCCESSFULLY_CREATED = "Account successfully created";
    public static final String SELLER_ACCOUNT_NOT_YET_CREATED = "You do not have an account yet!";
    public static final Object STORE_SUCCESSFULLY_CREATED = "You have successfully created a seller's store ";
    public static final String PRODUCT_SUCCESSFULLY_ADDED = "You have successfully added a product to your store";
    public static final String CUSTOMER_REGISTRATION_DETAILS_ALREADY_EXIST = "This registration details is already used. Kindly review your details and try again.";
    public static final String ACCOUNT_NOT_YET_CREATED = "You do not have an account yet. Kindly register to place an order.";
    public static final String PRODUCT_IS_CURRENTLY_UNAVAILABLE = "Product is currently unavailable";
    public static final String  PRODUCT_SUCCESSFULLY_ADDED_TO_CART = "You have successfully added product to cart";
    public static final String PRODUCT_IS_CURRENTLY_OUT_OF_STOCK = "Product is currently out of stock. Kindly check back at a later time. Thank you";
    public static final String REQUIRED_QUANTITY_IS_UNAVAILABLE_AT_THE_MOMENT = "Kindly order a lower quantity";
    public static final String SELLER_STORE_NOT_YET_CREATED = "You do not have a seller store";
    public static final String PRODUCT_ALREADY_EXIST = "Product already exist";
    public static final String CUSTOMER_DOES_NOT_HAVE_A_CART_YET = "Customer do not have a cart yet";
    public static final String NO_PRODUCT_IN_CART = "You have not selected any product into your cart";
    public static final String TOTAL_PRICE_OF_PRODUCT_IN_CART = "The total cost of selected products in cart = ";
    public static final String PRODUCT_SUCCESSFULLY_REMOVED_FROM_STORE = "You have successfully removed this product from store";
    public static final String PRODUCT_YOU_INTEND_TO_REMOVE_IS_NOT_PRESENT_IN_STORE = "The product you intend to remove is not available in store";
    public static final String SELLER_STORE_ALREADY_EXIST = "Seller store with this store name already exist";
    public static final String QUANTITY_NOT_AVAILABLE = "The quantity is unavailable";
    public static final String INVALID_USER_CREDENTIALS = "Invalid user credentials";
    public static final String CUSTOMER_ACCOUNT_NOT_FOUND = "Customer account not found";
    public static final String SUCCESSFULLY_LOGGED_IN = "logged in successfully";
    public static final String USER_NOT_LOGGED_IN = "Kindly log in to continue";

    public static ApiResponse created(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse added(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse removed(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(true)
                .build();
    }

    public static ApiResponse login(Object data) {
        return ApiResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.FOUND)
                .statusCode(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .build();
    }
}
