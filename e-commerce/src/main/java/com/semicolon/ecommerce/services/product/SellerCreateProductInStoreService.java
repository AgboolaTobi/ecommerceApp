package com.semicolon.ecommerce.services.product;

import com.semicolon.ecommerce.data.enums.ProductCategory;
import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.data.models.Seller;
import com.semicolon.ecommerce.data.models.Store;
import com.semicolon.ecommerce.dtos.request.ProductCreationRequest;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.services.seller.SellerService;
import com.semicolon.ecommerce.services.store.StoreService;
import com.semicolon.ecommerce.utils.ApiResponse;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SellerCreateProductInStoreService {
    private final SellerService sellerService;
    private final ProductService productService;
    private final StoreService storeService;
    private final ModelMapper modelMapper;


    public ApiResponse createProductInStore(ProductCreationRequest productCreationRequest) throws SellerException, StoreException, ProductException {


        Seller seller =sellerService.findByEmailAddress(productCreationRequest.getSellerEmailAddress());
        if(seller==null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);
        if (seller.isLocked()) throw new SellerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);
        if (!seller.getPassword().equals(productCreationRequest.getPassword())) throw new SellerException(GenerateApiResponse.INVALID_USER_CREDENTIALS);


        Store store = seller.getStore();
        if (store.getStoreName()==null)throw new StoreException(GenerateApiResponse.SELLER_STORE_NOT_YET_CREATED);

        List<Product> listOfProduct = store.getListOfProducts();
        if(listOfProduct==null){
            listOfProduct = new ArrayList<>();
        }
        String password = seller.getPassword();
        if (!password.equals(productCreationRequest.getPassword())) throw new StoreException(GenerateApiResponse.INVALID_USER_CREDENTIALS);
        Product product = modelMapper.map(productCreationRequest, Product.class);
        product.setProductCategory(ProductCategory.valueOf(productCreationRequest.getProductCategory().toUpperCase()));
        product.setPrice(BigDecimal.valueOf(productCreationRequest.getPrice()));

        Product savedProduct = productService.save(product);

        listOfProduct.add(savedProduct);

        store.setListOfProducts(new ArrayList<>(listOfProduct));
        Store updatedStore = storeService.save(store);

        seller.setStore(updatedStore);
        sellerService.save(seller);

        return GenerateApiResponse.created(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED);

    }
}
