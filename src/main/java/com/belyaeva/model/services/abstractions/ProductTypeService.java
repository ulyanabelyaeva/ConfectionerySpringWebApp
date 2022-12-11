package com.belyaeva.model.services.abstractions;

import com.belyaeva.model.entity.ProductTypeEntity;

import java.util.List;

public interface ProductTypeService {

    List<ProductTypeEntity> getProductTypeList();

    ProductTypeEntity getProductTypeByName(String name);

}
