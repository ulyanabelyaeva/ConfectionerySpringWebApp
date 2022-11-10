package com.belyaeva.controller;

import com.belyaeva.services.impl.CartItemServiceImpl;
import com.belyaeva.services.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.belyaeva.services.impl.UserServiceImpl;
import com.belyaeva.entity.User;
import com.belyaeva.entity.Cart;

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

        User user = userServiceImpl.getTempUser();

        Cart cart = cartServiceImpl.getCartByUserId(user.getId());
        model.addAttribute("tempUser", user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/user/cart")
    public String deleteItem(@RequestParam("btn") String btn, Model model){
        if (btn.equals("pay")){
            User user = userServiceImpl.getTempUser();
            Cart cart = cartServiceImpl.getCartByUserId(user.getId());
            cartServiceImpl.moveOldCartToOrdersAndCreteNewCart(cart);
            cartServiceImpl.addNewCart(new Cart(user));
            model.addAttribute("tempUser", user);
        } else {
            Long idItem = Long.parseLong(btn);
            Cart cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
            cart.setCost(cart.getCost() - cartItemServiceImpl.getItemById(idItem).getProduct().getPrice());
            cartItemServiceImpl.deleteItemById(idItem);
        }
        return "redirect:/user/cart";
    }
}
