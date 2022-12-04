package com.belyaeva.services.abstractions;

import com.belyaeva.entity.CartItemEntity;

public interface CartItemService {

    CartItemEntity getItemById(Long id);

    void addNewItem(CartItemEntity cartItemEntity);

    void deleteItemById(Long id);

}
