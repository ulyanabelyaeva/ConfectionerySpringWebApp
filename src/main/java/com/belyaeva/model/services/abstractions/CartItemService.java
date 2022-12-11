package com.belyaeva.model.services.abstractions;

import com.belyaeva.model.entity.CartItemEntity;

public interface CartItemService {

    CartItemEntity getItemById(Long id);

    void addNewItem(CartItemEntity cartItemEntity);

    void deleteItemById(Long id);

}
