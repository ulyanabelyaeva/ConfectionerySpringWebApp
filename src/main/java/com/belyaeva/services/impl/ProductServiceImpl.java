package com.belyaeva.services.impl;

import com.belyaeva.entity.Product;
import com.belyaeva.repository.ProductRepository;
import com.belyaeva.services.abstractions.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;

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

    public Product createImage(Product product){
        String name = product.getIcon().getOriginalFilename();
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + name;
        File newImage = new File(filePath);
        try {
            product.getIcon().transferTo(newImage);
            product.setImage(product.getIcon().getOriginalFilename());
        } catch (IOException e) {
            System.out.println("не удалось создать файл");
        }
        return product;
    }

    public Product addNewProduct(Product product){
        createImage(product);
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        return productRepository.save(product);
    }

    public Product changeProduct(Product product){

        if (product.getIcon() != null){ //old or new image
            product.setImage(productRepository.findById(product.getId()).orElse(null).getImage());
        } else {
            createImage(product);
        }
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
