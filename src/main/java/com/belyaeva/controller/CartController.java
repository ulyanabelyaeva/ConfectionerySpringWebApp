package com.belyaeva.controller;

import com.belyaeva.entity.CartEntity;
import com.belyaeva.services.impl.CartItemServiceImpl;
import com.belyaeva.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.belyaeva.services.impl.UserServiceImpl;
import com.belyaeva.entity.UserEntity;

@Controller
public class CartController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;

    @GetMapping("/user/cart")
    public String getCart(Model model){

        UserEntity user = userServiceImpl.getTempUser();

        CartEntity cartEntity = cartServiceImpl.getCartByUserId(user.getId());
        model.addAttribute("tempUser", user);
        model.addAttribute("cart", cartEntity);

        return "cart";
    }

    @PostMapping("/user/cart")
    public String deleteItem(@RequestParam("btn") String btn, Model model){
        if (btn.equals("pay")){
            UserEntity user = userServiceImpl.getTempUser();
            CartEntity cartEntity = cartServiceImpl.getCartByUserId(user.getId());
            cartServiceImpl.moveOldCartToOrdersAndCreteNewCart(cartEntity);
            cartServiceImpl.addNewCart(CartEntity.builder()
                    .user(user)
                    .status(false)
                    .ready(false)
                    .cost(0)
                    .build());
            model.addAttribute("tempUser", user);
        } else {
            Long idItem = Long.parseLong(btn);
            CartEntity cartEntity = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
            cartEntity.setCost(cartEntity.getCost() - cartItemServiceImpl.getItemById(idItem).getProduct().getPrice());
            cartItemServiceImpl.deleteItemById(idItem);
        }
        return "redirect:/user/cart";
    }
}
