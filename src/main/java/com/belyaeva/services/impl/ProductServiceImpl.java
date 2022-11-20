package com.belyaeva.services.impl;

import com.belyaeva.entity.Product;
import com.belyaeva.repository.ProductRepository;
import com.belyaeva.services.abstractions.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;

    public List<Product> getAllProducts(){
        return productRepository.findAll().stream()
            .filter(Product::isStatus)
            .collect(Collectors.toList());
    }
    public List<Product> getProductByProductTypeId(Long id){
        List<Product> products;
        if (id == 1){
            products = getAllProducts();
        } else {
            products = productRepository.findAllByProductTypeId(id).stream()
                .filter(Product::isStatus)
                .collect(Collectors.toList());
        }
        return products;
    }

    // FIX: Refactor with ProductModel for presentation layer
    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product createImage(Product product){
        String name = product.getIcon().getOriginalFilename();
        // FIX: Move path to application properties/yaml
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + name;
        try (File newImage = new File(filePath)) {
            product.getIcon().transferTo(newImage);
            product.setImage(product.getIcon().getOriginalFilename());
        } catch (IOException e) {
            // FIX: Use logger
            System.out.println("не удалось создать файл");
        }
        return product;
    }

    public Product addNewProduct(Product product){
        createImage(product);
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    public Product changeProduct(Product product){

        // FIX: Create Service layer model for Product and add method for checking image
        if (product.getIcon() != null){ //old or new image
            // FIX: Possible nullptr
            product.setImage(productRepository.findById(product.getId()).orElse(null).getImage());
        } else {
            createImage(product);
        }
        product.setProductType(productTypeServiceImpl.getProductTypeByName(product.getNameProductType()));
        product.setStatus(true);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id).orElse(null);
        product.setStatus(false);
        productRepository.save(product);
    }
}
