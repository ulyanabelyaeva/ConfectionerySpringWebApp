package com.belyaeva.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.belyaeva.repository.CartRepository;
import com.belyaeva.entity.Cart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    public static final SimpleDateFormat TEXT_FORMATTER = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUserId(Long id){
        return cartRepository.findAllByUserId(id).stream().filter(c -> !c.isStatus()).findFirst().orElse(null);
    }

    public List<Cart> getOrderList(Long id){
        return cartRepository.findAllByUserId(id).stream().filter(Cart::isStatus).collect(Collectors.toList());
    }

    public void addNewCart(Cart cart){
        cartRepository.save(cart);
    }

    public void moveOldCartToOrdersAndCreteNewCart(Cart cart){
        cart.setDate(TEXT_FORMATTER.format(new Date()));
        cart.setStatus(true);
        cartRepository.save(cart);
    }

}
