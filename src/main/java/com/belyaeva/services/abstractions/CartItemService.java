package com.belyaeva.services.abstractions;

import com.belyaeva.entity.CartItem;

public interface CartItemService {

    CartItem getItemById(Long id);

    void addNewItem(CartItem cartItem);

    void deleteItemById(Long id);

}
