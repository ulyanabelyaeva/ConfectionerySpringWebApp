package com.belyaeva.services.abstractions;

import com.belyaeva.entity.ProductTypeEntity;

import java.util.List;

public interface ProductTypeService {

    List<ProductTypeEntity> getProductTypeList();

    ProductTypeEntity getProductTypeByName(String name);

}
