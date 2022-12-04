package com.belyaeva.services.impl;

import com.belyaeva.entity.ProductEntity;
import com.belyaeva.entity.ProductTypeEntity;
import com.belyaeva.entity.UserEntity;
import com.belyaeva.services.abstractions.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class SpringUiModelProductFacade implements ProductFacade<Model, Model> {

    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    public Model getProductsAndUser(Model model) {
        List<ProductTypeEntity> productTypeList = productTypeServiceImpl.getProductTypeList();
        List<ProductEntity> productList = productServiceImpl.getAllProducts();
        UserEntity user = userServiceImpl.getTempUser();
        model.addAttribute("tempUser", user);
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);
        return model;
    }

    public Model getProductsByTypeAndUser(Long id, Model model){
        getProductsAndUser(model);
        List<ProductEntity> productList = productServiceImpl.getProductByProductTypeId(id);
        model.addAttribute("products", productList);
        return model;
    }
}
