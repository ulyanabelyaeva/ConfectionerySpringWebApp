package com.belyaeva.controller;

import com.belyaeva.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartController {

    @GetMapping("/user/cart")
    public List<Product> getCart(){
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Тирамису", "Торт", 600));
        products.add(new Product(2L, "Панчо", "Торт", 600));
        return products;
    }
}
