package com.belyaeva.controller;

import com.belyaeva.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    @GetMapping("/user/cart")
    public String getCart(Model model){
        /*List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Тирамису", "Торт", 600));
        products.add(new Product(2L, "Панчо", "Торт", 600));
        model.addAttribute("cart", products);*/
        return "cart";
    }
}
