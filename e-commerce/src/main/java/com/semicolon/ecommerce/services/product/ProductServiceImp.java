package com.semicolon.ecommerce.services.product;


import com.semicolon.ecommerce.data.enums.ProductCategory;
import com.semicolon.ecommerce.data.models.Product;
import com.semicolon.ecommerce.data.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{

    private final ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

}
