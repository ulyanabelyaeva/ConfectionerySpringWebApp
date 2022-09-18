package com.belyaeva.services;

import com.belyaeva.entity.CartItem;
import com.belyaeva.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem getItemById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public void addNewItem(CartItem cartItem){
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllItemsByCartId(Long id){
        return cartItemRepository.findAllByCartId(id);
    }

    public void deleteItemById(Long id){
        cartItemRepository.deleteById(id);
    }
}
