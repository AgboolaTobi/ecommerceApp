package com.semicolon.ecommerce.services.product;

import com.semicolon.ecommerce.data.models.Product;

public interface ProductService {

    Product save(Product product);

    Product findProductByName(String productName);

}
