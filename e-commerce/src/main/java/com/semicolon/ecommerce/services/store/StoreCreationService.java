package com.semicolon.ecommerce.services.store;

import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.data.models.Store;
import com.semicolon.ecommerce.dtos.request.StoreCreationRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.services.seller.SellerService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class StoreCreationService {

    private final SellerService sellerService;
    private final StoreService storeService;

    public ApiResponse createStore(StoreCreationRequest storeCreationRequest) throws SellerException {
        Seller seller = sellerService.findByEmailAddress(storeCreationRequest.getSellerEmailAddress());
        if (seller == null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);
        if (seller.isLocked()) throw new SellerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);
        Store sellerStore = storeService.findByStoreName(storeCreationRequest.getStoreName());
        if (sellerStore!=null) throw new SellerException(GenerateApiResponse.SELLER_STORE_ALREADY_EXIST);
        Store store = new Store();

        store.setStoreName(storeCreationRequest.getStoreName());
        String password = seller.getPassword();
        if (!password.equals(storeCreationRequest.getPassword())) throw new SellerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);
        store.setSellerEmailAddress(storeCreationRequest.getSellerEmailAddress());

        Store savedStore = storeService.save(store);

        seller.setStore(savedStore);
        sellerService.save(seller);

        return GenerateApiResponse.created(GenerateApiResponse.STORE_SUCCESSFULLY_CREATED);
    }

}
