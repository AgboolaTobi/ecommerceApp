package com.semicolon.ecommerce.services.seller;


import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.dtos.request.SellerRegistrationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SellerRegistrationService {

    private final SellerService sellerService;
    private final ModelMapper modelMapper;

    public ApiResponse register(SellerRegistrationRequest sellerRegistrationRequest) throws SellerException {

        boolean isRegistered = sellerService.findByEmailAddress(sellerRegistrationRequest.getEmailAddress())!= null;
        if (isRegistered) throw new SellerException(GenerateApiResponse.SELLER_ALREADY_EXIST);

        Seller seller = modelMapper.map(sellerRegistrationRequest, Seller.class);

        sellerService.save(seller);

        return GenerateApiResponse.created(GenerateApiResponse.ACCOUNT_SUCCESSFULLY_CREATED);
    }


}
