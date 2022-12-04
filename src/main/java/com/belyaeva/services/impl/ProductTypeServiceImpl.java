package com.belyaeva.services.impl;

import com.belyaeva.entity.ProductTypeEntity;
import com.belyaeva.repository.ProductTypeRepository;
import com.belyaeva.services.abstractions.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    private ProductTypeRepository productTypeRepository;

    public List<ProductTypeEntity> getProductTypeList(){
        return productTypeRepository.findAll();
    }

    public ProductTypeEntity getProductTypeByName(String name){
        return productTypeRepository.findProductTypeByName(name).get(0);
    }
}
