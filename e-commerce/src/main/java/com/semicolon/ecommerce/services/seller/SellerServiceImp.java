package com.semicolon.ecommerce.services.seller;


import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.data.repositories.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerServiceImp implements SellerService{

    private final SellerRepository sellerRepository;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller findByEmailAddress(String emailAddress) {
        return sellerRepository.findByEmailAddress(emailAddress);
    }
}
