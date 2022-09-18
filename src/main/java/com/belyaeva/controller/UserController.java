package com.belyaeva.controller;

import com.belyaeva.entity.Cart;
import com.belyaeva.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.belyaeva.services.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/user")
    public String userPage(Model model){
        model.addAttribute("tempUser", userService.getTempUser());
        List<Cart> orders = cartService.getOrderList(userService.getTempUser().getId());
        model.addAttribute("orders", orders);
        return "user";
    }

}
