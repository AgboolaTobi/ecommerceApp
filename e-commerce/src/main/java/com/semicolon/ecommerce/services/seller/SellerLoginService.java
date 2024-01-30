package com.semicolon.ecommerce.services.seller;

import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.dtos.request.SellerLoginRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SellerLoginService {
    private final SellerService sellerService;

    public ApiResponse sellerLogin(SellerLoginRequest sellerLoginRequest) throws SellerException {

        Seller existingSeller = sellerService.findByEmailAddress(sellerLoginRequest.getSellerEmail());
        if (existingSeller==null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);

        String password = existingSeller.getPassword();
        if (!password.equals(sellerLoginRequest.getPassword())) throw new SellerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);

        existingSeller.setLocked(false);
        sellerService.save(existingSeller);
        return GenerateApiResponse.login(existingSeller.getSellerName() + " , you have " + GenerateApiResponse.SUCCESSFULLY_LOGGED_IN);

    }
}
