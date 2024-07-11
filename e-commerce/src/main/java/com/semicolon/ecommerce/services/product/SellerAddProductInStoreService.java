package com.semicolon.ecommerce.services.product;

import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.data.models.Store;
import com.semicolon.ecommerce.dtos.request.SellerAddToExistingProductRequest;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.exceptions.UserLoginException;
import com.semicolon.ecommerce.services.seller.SellerService;
import com.semicolon.ecommerce.services.store.StoreService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class SellerAddProductInStoreService {

    private final SellerService sellerService;
    private  final StoreService storeService;
    private final ProductService productService;


    public ApiResponse addToExistingProductInStore(SellerAddToExistingProductRequest sellerAddToExistingProductRequest) throws SellerException, StoreException, UserLoginException {
        Seller seller = sellerService.findByEmailAddress(sellerAddToExistingProductRequest.getSellerEmailAddress());
        if(seller==null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);

        if (seller.isLocked()) throw new UserLoginException(GenerateApiResponse.USER_NOT_LOGGED_IN);
        if (!seller.getPassword().equals(sellerAddToExistingProductRequest.getPassword()))throw new UserLoginException(GenerateApiResponse.USER_NOT_LOGGED_IN);

        Store store = seller.getStore();
        if (store.getStoreName()==null)throw new StoreException(GenerateApiResponse.SELLER_STORE_NOT_YET_CREATED);


        List<Product> listOfProduct = store.getListOfProducts();


        Product quantityToAddToExistingProductInStore = productService.findProductByName(sellerAddToExistingProductRequest.getProductName());
        quantityToAddToExistingProductInStore.setProductCategory(sellerAddToExistingProductRequest.getProductCategory());
        quantityToAddToExistingProductInStore.setProductName(sellerAddToExistingProductRequest.getProductName());

        int quantityToAddToListOfProductInStore = 0;
        for (Product eachProduct: listOfProduct){

            quantityToAddToListOfProductInStore = eachProduct.getQuantity() + sellerAddToExistingProductRequest.getQuantity();
            if (quantityToAddToExistingProductInStore.getQuantity() < 1) throw new StoreException(GenerateApiResponse.QUANTITY_NOT_AVAILABLE);
        }

        quantityToAddToExistingProductInStore.setQuantity(quantityToAddToListOfProductInStore);


        listOfProduct.add(quantityToAddToExistingProductInStore);
        store.setListOfProducts(new ArrayList<>(listOfProduct));
        Store updatedStore = storeService.save(store);
        seller.setStore(updatedStore);
        sellerService.save(seller);

        return GenerateApiResponse.removed(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED);
    }
}
