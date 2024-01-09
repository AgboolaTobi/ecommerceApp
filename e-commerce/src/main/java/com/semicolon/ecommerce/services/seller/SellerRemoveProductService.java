package com.semicolon.ecommerce.services.seller;

import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.data.models.Store;
import com.semicolon.ecommerce.dtos.request.SellerRemoveProductRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.services.product.ProductService;
import com.semicolon.ecommerce.services.store.StoreService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SellerRemoveProductService {

    private final SellerService sellerService;
    private  final StoreService storeService;
    private final ProductService productService;


    public ApiResponse removeProductFromStore(SellerRemoveProductRequest sellerRemoveProductRequest) throws SellerException, StoreException, ProductException {
        Seller seller = sellerService.findByEmailAddress(sellerRemoveProductRequest.getEmailAddress());
        if (seller== null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);
        Store sellerStore = seller.getStore();

        if (sellerStore== null) throw new StoreException(GenerateApiResponse.SELLER_STORE_NOT_YET_CREATED);

        Product productInStore = productService.findProductByName(sellerRemoveProductRequest.getProductName());
        if (productInStore==null) throw new ProductException(GenerateApiResponse.PRODUCT_IS_CURRENTLY_UNAVAILABLE);

        List<Product> listOfProductsInStore = sellerStore.getListOfProducts();
        listOfProductsInStore.remove(productInStore);
        sellerStore.setListOfProducts(new ArrayList<>(listOfProductsInStore));
        Store updatedStore = storeService.save(sellerStore);
        seller.setStore(updatedStore);
        sellerService.save(seller);

        return GenerateApiResponse.removed(GenerateApiResponse.PRODUCT_SUCCESSFULLY_REMOVED_STORE + sellerRemoveProductRequest.getProductName());



    }
}
