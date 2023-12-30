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
public class ProductCreationService {
    private final SellerService sellerService;
    private final ProductService productService;
    private final StoreService storeService;
    private final ModelMapper modelMapper;


    public ApiResponse createProduct(ProductCreationRequest productCreationRequest) throws SellerException, ProductException, StoreException {


        Seller seller =sellerService.findByEmailAddress(productCreationRequest.getSellerEmailAddress());
        if(seller==null) throw new SellerException(GenerateApiResponse.SELLER_ACCOUNT_NOT_YET_CREATED);


        Store store = seller.getStore();
        if (store.getStoreName()==null)throw new StoreException(GenerateApiResponse.SELLER_STORE_NOT_YET_CREATED);

        List<Product> listOfProduct = store.getListOfProducts();
        if(listOfProduct==null){
            listOfProduct = new ArrayList<>();
        }

        Product existingProduct = productService.findProductByName(productCreationRequest.getProductName());
        if(existingProduct!=null) throw new ProductException(GenerateApiResponse.PRODUCT_ALREADY_ADDED);


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
