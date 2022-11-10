package com.belyaeva.services.abstractions;

import com.belyaeva.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductByProductTypeId(Long id);

    Product getProductById(Long id);

    Product addNewProduct(Product product);

    Product changeProduct(Product product);

    void deleteProduct(Long id);
}
