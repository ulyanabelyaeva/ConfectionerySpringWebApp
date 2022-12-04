package com.belyaeva.services.abstractions;

import com.belyaeva.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProducts();

    List<ProductEntity> getProductByProductTypeId(Long id);

    ProductEntity getProductById(Long id);

    ProductEntity addNewProduct(ProductEntity productEntity);

    ProductEntity changeProduct(ProductEntity productEntity);

    void deleteProduct(Long id);
}
