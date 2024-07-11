package com.semicolon.ecommerce;

import com.semicolon.ecommerce.data.enums.ProductCategory;
import com.semicolon.ecommerce.data.repositories.SellerRepository;
import com.semicolon.ecommerce.dtos.request.*;
import com.semicolon.ecommerce.exceptions.ProductException;
import com.semicolon.ecommerce.exceptions.SellerException;
import com.semicolon.ecommerce.exceptions.StoreException;
import com.semicolon.ecommerce.exceptions.UserLoginException;
import com.semicolon.ecommerce.services.product.SellerAddProductInStoreService;
import com.semicolon.ecommerce.services.product.SellerCreateProductInStoreService;
import com.semicolon.ecommerce.services.seller.SellerLoginService;
import com.semicolon.ecommerce.services.seller.SellerRegistrationService;
import com.semicolon.ecommerce.services.store.StoreCreationService;
import com.semicolon.ecommerce.utils.GenerateApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class SellerTests {

    @Autowired
   private  SellerRegistrationService sellerRegistrationService;
    @Autowired
   private  SellerRepository sellerRepository;
    @Autowired
    private SellerLoginService sellerLoginService;

    @Autowired
    private StoreCreationService storeCreationService;
    @Autowired
    SellerCreateProductInStoreService sellerCreateProductInStoreService;

    @Autowired
    SellerAddProductInStoreService sellerAddProductInStoreService;
    @BeforeEach
    public void setSellerService(){
        sellerRepository.deleteAll();
    }
    @Test
    public void testThatSellerCanRegister() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.created(GenerateApiResponse.ACCOUNT_SUCCESSFULLY_CREATED).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());
        assertEquals(1, sellerRepository.count());
    }

    @Test
    public void testThatWhenANewSellerTriesToRegisterWithExistingUserDetailsExceptionIsThrown() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");

        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());


    }

    @Test
    public void testThatARegisteredUserCanLogin() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());
        SellerLoginRequest sellerLoginRequest = new SellerLoginRequest();
        sellerLoginRequest.setSellerEmail("Tobi@gmail.com");
        sellerLoginRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.login(GenerateApiResponse.SUCCESSFULLY_LOGGED_IN).getHttpStatus(),sellerLoginService.sellerLogin(sellerLoginRequest).getHttpStatus());
    }


    @Test
    public void testThatIfARegisteredSellerTriesToLoginWithWrongDetailsExceptionIsThrown() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());
        SellerLoginRequest sellerLoginRequest = new SellerLoginRequest();
        sellerLoginRequest.setSellerEmail("Tobi@gmail.com");
        sellerLoginRequest.setPassword("123434");
        assertThrows(SellerException.class, () ->sellerLoginService.sellerLogin(sellerLoginRequest));
    }

    @Test
    public void testThatARegisteredSelleCanLoginAndCanCreateStore() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());

        SellerLoginRequest sellerLoginRequest = new SellerLoginRequest();
        sellerLoginRequest.setSellerEmail("Tobi@gmail.com");
        sellerLoginRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.login(GenerateApiResponse.SUCCESSFULLY_LOGGED_IN).getHttpStatus(),sellerLoginService.sellerLogin(sellerLoginRequest).getHttpStatus());

        StoreCreationRequest storeCreationRequest = new StoreCreationRequest();
        storeCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        storeCreationRequest.setStoreName("HisGrace Store");
        storeCreationRequest.setPassword("1234567");

        assertEquals(GenerateApiResponse.created(GenerateApiResponse.STORE_SUCCESSFULLY_CREATED).getHttpStatus(), storeCreationService.createStore(storeCreationRequest).getHttpStatus());

    }

    @Test
    public void testThatARegisteredSelleTriesToCannotCreateStoreWithoutBeLoggedInExceptionIsThrown() throws SellerException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());

        StoreCreationRequest storeCreationRequest = new StoreCreationRequest();
        storeCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        storeCreationRequest.setStoreName("HisGrace Store");
        storeCreationRequest.setPassword("1234567");

        assertThrows(SellerException.class,()-> storeCreationService.createStore(storeCreationRequest));

    }

    @Test
    public void testThatARegisteredSelleCanLoginAndCanCreateStoreAndCreateProductInStore() throws SellerException, ProductException, StoreException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());

        SellerLoginRequest sellerLoginRequest = new SellerLoginRequest();
        sellerLoginRequest.setSellerEmail("Tobi@gmail.com");
        sellerLoginRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.login(GenerateApiResponse.SUCCESSFULLY_LOGGED_IN).getHttpStatus(),sellerLoginService.sellerLogin(sellerLoginRequest).getHttpStatus());

        StoreCreationRequest storeCreationRequest = new StoreCreationRequest();
        storeCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        storeCreationRequest.setStoreName("HisGrace Store");
        storeCreationRequest.setPassword("1234567");



        assertEquals(GenerateApiResponse.created(GenerateApiResponse.STORE_SUCCESSFULLY_CREATED).getHttpStatus(), storeCreationService.createStore(storeCreationRequest).getHttpStatus());

        ProductCreationRequest productCreationRequest = new ProductCreationRequest();
        productCreationRequest.setProductCategory("phone");
        productCreationRequest.setProductName("samsung");
        productCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        productCreationRequest.setPassword("1234567");
        productCreationRequest.setQuantity(75);
        productCreationRequest.setPrice(58000);

        assertEquals(GenerateApiResponse.created(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED).getHttpStatus(),sellerCreateProductInStoreService.createProductInStore(productCreationRequest).getHttpStatus()) ;
    }

    @Test
    public void testThatARegisteredSelleCanLoginAndCanCreateStoreAndCreateProductInStoreThenAddToAlreadyCreatedProduct() throws SellerException, ProductException, StoreException, UserLoginException {
        SellerRegistrationRequest sellerRegistrationRequest = new SellerRegistrationRequest();
        sellerRegistrationRequest.setEmailAddress("Tobi@gmail.com");
        sellerRegistrationRequest.setAddress("13 wosilatu");
        sellerRegistrationRequest.setSellerName("Tobi");
        sellerRegistrationRequest.setPassword("1234567");


        assertEquals(GenerateApiResponse.created(GenerateApiResponse.SELLER_ALREADY_EXIST).getHttpStatus(), sellerRegistrationService.register(sellerRegistrationRequest).getHttpStatus());

        SellerLoginRequest sellerLoginRequest = new SellerLoginRequest();
        sellerLoginRequest.setSellerEmail("Tobi@gmail.com");
        sellerLoginRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.login(GenerateApiResponse.SUCCESSFULLY_LOGGED_IN).getHttpStatus(),sellerLoginService.sellerLogin(sellerLoginRequest).getHttpStatus());

        StoreCreationRequest storeCreationRequest = new StoreCreationRequest();
        storeCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        storeCreationRequest.setStoreName("HisGrace Store");
        storeCreationRequest.setPassword("1234567");



        assertEquals(GenerateApiResponse.created(GenerateApiResponse.STORE_SUCCESSFULLY_CREATED).getHttpStatus(), storeCreationService.createStore(storeCreationRequest).getHttpStatus());

        ProductCreationRequest productCreationRequest = new ProductCreationRequest();
        productCreationRequest.setProductCategory("phone");
        productCreationRequest.setProductName("samsung");
        productCreationRequest.setSellerEmailAddress("Tobi@gmail.com");
        productCreationRequest.setPassword("1234567");
        productCreationRequest.setQuantity(75);
        productCreationRequest.setPrice(58000);

        assertEquals(GenerateApiResponse.created(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED).getHttpStatus(),sellerCreateProductInStoreService.createProductInStore(productCreationRequest).getHttpStatus()) ;

        SellerAddToExistingProductRequest sellerAddToExistingProductRequest = new SellerAddToExistingProductRequest();
        sellerAddToExistingProductRequest.setSellerEmailAddress("Tobi@gmail.com");
        sellerAddToExistingProductRequest.setProductCategory(ProductCategory.valueOf("PHONE"));
        sellerAddToExistingProductRequest.setProductName("samsung");
        sellerAddToExistingProductRequest.setQuantity(20);
        sellerAddToExistingProductRequest.setPassword("1234567");
        assertEquals(GenerateApiResponse.added(GenerateApiResponse.PRODUCT_SUCCESSFULLY_ADDED).getHttpStatus(),sellerAddProductInStoreService.addToExistingProductInStore(sellerAddToExistingProductRequest).getHttpStatus());
    }


}