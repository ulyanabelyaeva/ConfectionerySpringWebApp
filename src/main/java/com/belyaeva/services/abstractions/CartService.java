package com.belyaeva.services.abstractions;

import com.belyaeva.entity.Cart;

import java.util.List;

public interface CartService {

    Cart getCartByUserId(Long id);

    Cart getCartById(Long id);

    void addNewCart(Cart cart);

    List<Cart> getOrderList(Long id);

    List<Cart> getUnreadyOrderList();

    void moveOldCartToOrdersAndCreteNewCart(Cart cart);

    void moveOrderToReady(Cart cart);
}
