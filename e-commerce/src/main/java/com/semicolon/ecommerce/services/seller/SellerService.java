package com.semicolon.ecommerce.services.seller;

import com.semicolon.ecommerce.data.models.Seller;

public interface SellerService {
    Seller save(Seller seller);

    Seller findByEmailAddress(String emailAddress);
}
