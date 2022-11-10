package com.belyaeva.services.abstractions;

import com.belyaeva.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    List<ProductType> getProductTypeList();

    ProductType getProductTypeByName(String name);

}
