package com.belyaeva.controller;

import com.belyaeva.entity.Product;
import com.belyaeva.services.CartItemService;
import com.belyaeva.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import com.belyaeva.services.UserService;
import com.belyaeva.entity.User;
import com.belyaeva.entity.Cart;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/user/cart")
    public String getCart(Model model){

        User user = userService.getTempUser();

        Cart cart = cartService.getCartByUserId(user.getId());
        model.addAttribute("tempUser", user);
        model.addAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/user/cart")
    public String deleteItem(@RequestParam("btn") String btn, Model model){
        if (btn.equals("pay")){
            User user = userService.getTempUser();
            Cart cart = cartService.getCartByUserId(user.getId());
            cartService.moveOldCartToOrdersAndCreteNewCart(cart);
            cartService.addNewCart(new Cart(user, false));
            model.addAttribute("tempUser", user);
        } else {
            Long idItem = Long.parseLong(btn);
            Cart cart = cartService.getCartByUserId(userService.getTempUser().getId());
            cart.setCost(cart.getCost() - cartItemService.getItemById(idItem).getProduct().getPrice());
            cartItemService.deleteItemById(idItem);
        }
        return "redirect:/user/cart";
    }
}
