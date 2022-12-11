package com.belyaeva.model.services.impl;

import com.belyaeva.model.entity.ProductTypeEntity;
import com.belyaeva.model.repository.ProductTypeRepository;
import com.belyaeva.model.services.abstractions.ProductTypeService;
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
