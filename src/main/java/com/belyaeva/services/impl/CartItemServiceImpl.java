package com.belyaeva.services.impl;

import com.belyaeva.entity.CartItem;
import com.belyaeva.repository.CartItemRepository;
import com.belyaeva.services.abstractions.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem getItemById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void addNewItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public void deleteItemById(Long id){
        cartItemRepository.deleteById(id);
    }
}
