package com.semicolon.ecommerce.services.registration;


import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.dtos.request.RegistrationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.seller.SellerService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class RegistrationService {

    private final SellerService sellerService;
    private final ModelMapper modelMapper;

    public ApiResponse register(RegistrationRequest registrationRequest) throws SellerException {
        boolean isRegistered = sellerService.findByEmailAddress(registrationRequest.getEmailAddress())!= null;
        if (isRegistered) throw new SellerException(GenerateApiResponse.SELLER_ALREADY_EXIST);

        Seller seller = modelMapper.map(registrationRequest, Seller.class);
        sellerService.save(seller);
        return GenerateApiResponse.created(GenerateApiResponse.ACCOUNT_SUCCESSFULLY_CREATED);
    }
}
