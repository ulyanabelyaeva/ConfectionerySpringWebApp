package com.belyaeva.services;

import com.belyaeva.entity.Product;
import com.belyaeva.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public List<Product> getProductByProductTypeId(Long id){
        List<Product> products;
        if (id == 1){
            products = productRepository.findAll();
        } else {
            products = productRepository.findAllByProductTypeId(id);
        }
        return products;
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

}
