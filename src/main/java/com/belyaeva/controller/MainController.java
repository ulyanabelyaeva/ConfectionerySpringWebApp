package com.belyaeva.controller;

import com.belyaeva.entity.*;
import com.belyaeva.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductFacade<Model, Model> productFacade;

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/catalog")
    public String getProducts(Model model){
        Model model = productFacade.getProducts(model);
        return isAdmin() ? "redirect:/admin/catalog" : "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductByProductTypeId(@PathVariable("id") Long id, Model model){

        User user = userServiceImpl.getTempUser();
        List<Product> products = productServiceImpl.getProductByProductTypeId(id);
        model.addAttribute("products", products);
        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("tempUser", user);

        // FIX: Use isAdmin method
        if (user != null){
            Role role = user.getRoles().stream().filter(r -> r.getName().equals("USER")).findFirst().orElse(null);
            if (role != null)
                return "catalog";
            else
                return "redirect:/admin/catalog/{id}";
        } else
            return "catalog";
    }


    private boolean isAdmin() {
        // TODO: Get user ...
        
        if (user == null) {
            return false;
        }

        Role role = user.getRoles().stream()
            .filter(r -> r.getName().equals("USER"))
            .findFirst()
            .orElse(null);

        return role == null;
    }



    @GetMapping("/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/catalog/{id}";
    }

    @PostMapping("/catalog/{id}")
    public String addToCart(@RequestParam("btn") String btn, @PathVariable("id") Long id, Model model, HttpSession session){

        Long idProduct = Long.parseLong(btn);
        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");
            return "redirect:/catalog/{id}";
        }

        //add to cart
        Product product = productServiceImpl.getProductById(idProduct);
        Cart cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItem cartItem = new CartItem(product, cart);
        cartItemServiceImpl.addNewItem(cartItem);

        return "redirect:/catalog";
    }

    @PostMapping("/catalog")
    public String addToCart2(@RequestParam("btn") String btn, Model model, HttpSession session){

        Long idProduct = Long.parseLong(btn);
        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");

            List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
            List<Product> productList = productServiceImpl.getAllProducts();
            model.addAttribute("productTypes", productTypeList);
            model.addAttribute("products", productList);

            return "/catalog";
        }

        Product product = productServiceImpl.getProductById(idProduct);
        Cart cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItem cartItem = new CartItem(product, cart);
        cartItemServiceImpl.addNewItem(cartItem);


        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        List<Product> productList = productServiceImpl.getAllProducts();
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);

        return "/catalog";
    }

    @GetMapping("/catalog/cart")
    public String toCart(){
        return "redirect:/user/cart";
    }
}
