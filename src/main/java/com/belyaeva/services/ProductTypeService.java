package com.belyaeva.services;

import com.belyaeva.entity.Product;
import com.belyaeva.entity.ProductType;
import com.belyaeva.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> getProductTypeList(){
        return productTypeRepository.findAll();
    }
}
