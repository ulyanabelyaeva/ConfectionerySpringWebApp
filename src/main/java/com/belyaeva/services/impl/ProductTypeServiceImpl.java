package com.belyaeva.services.impl;

import com.belyaeva.entity.ProductType;
import com.belyaeva.repository.ProductTypeRepository;
import com.belyaeva.services.abstractions.ProductService;
import com.belyaeva.services.abstractions.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductType> getProductTypeList(){
        return productTypeRepository.findAll();
    }

    public ProductType getProductTypeByName(String name){
        return productTypeRepository.findProductTypeByName(name).get(0);
    }
}
