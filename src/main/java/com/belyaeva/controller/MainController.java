package com.belyaeva.controller;

import com.belyaeva.model.entity.*;
import com.belyaeva.model.services.abstractions.ProductFacade;
import com.belyaeva.model.services.impl.CartItemServiceImpl;
import com.belyaeva.model.services.impl.CartServiceImpl;
import com.belyaeva.model.services.impl.ProductServiceImpl;
import com.belyaeva.model.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private ProductFacade<Model, Model> productFacade;

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/catalog")
    public String getProducts(Model model){
        productFacade.getProductsAndUser(model);
        return isAdmin() ? "redirect:/admin/catalog" : "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductByProductTypeId(@PathVariable("id") Long id, Model model){
        productFacade.getProductsByTypeAndUser(id, model);
        return isAdmin() ? "redirect:/admin/catalog" : "catalog";
    }

    private boolean isAdmin() {
        UserEntity user = userServiceImpl.getTempUser();
        if (user == null) {
            return false;
        }
        RoleEntity role = user.getRoles().stream()
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
    public String addToCartFromCatalogByProductType(@RequestParam("btn") String btn, Model model, HttpSession session){

        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");
            return "redirect:/catalog/{id}";
        }
        session.setAttribute("error", "");
        addNewItemToCart(btn);
        return "redirect:/catalog";
    }

    @PostMapping("/catalog")
    public String addToCart(@RequestParam("btn") String btn, Model model, HttpSession session){

        if (userServiceImpl.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");
            return "redirect:/catalog";
        }
        session.setAttribute("error", "");
        addNewItemToCart(btn);
        return "redirect:/catalog";
    }

    private void addNewItemToCart(String id){
        Long idProduct = Long.parseLong(id);
        ProductEntity product = productServiceImpl.getProductById(idProduct);
        CartEntity cart = cartServiceImpl.getCartByUserId(userServiceImpl.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItemEntity cartItem = CartItemEntity.builder()
                .product(product)
                .cart(cart)
                .build();
        cartItemServiceImpl.addNewItem(cartItem);
    }

    @GetMapping("/catalog/cart")
    public String toCart(){
        return "redirect:/user/cart";
    }
}
