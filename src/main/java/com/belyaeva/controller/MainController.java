package com.belyaeva.controller;

import com.belyaeva.entity.Cart;
import com.belyaeva.entity.CartItem;
import com.belyaeva.entity.Product;
import com.belyaeva.entity.ProductType;
import com.belyaeva.services.*;
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
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/catalog")
    public String getProducts(Model model){
        List<ProductType> productTypeList = productTypeService.getProductTypeList();
        List<Product> productList = productService.getAllProducts();

        model.addAttribute("tempUser", userService.getTempUser());
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);

        return "catalog";
    }

    @GetMapping("/catalog/{id}")
    public String getProductByProductTypeId(@PathVariable("id") Long id, Model model){

        List<Product> products = productService.getProductByProductTypeId(id);
        model.addAttribute("products", products);
        List<ProductType> productTypeList = productTypeService.getProductTypeList();
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("tempUser", userService.getTempUser());

        return "catalog";
    }

    @GetMapping("/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/catalog/{id}";
    }

    @PostMapping("/catalog/{id}")
    public String addToCart(@RequestParam("btn") String btn, @PathVariable("id") Long id, Model model, HttpSession session){

        Long idProduct = Long.parseLong(btn);
        if (userService.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");

            List<Product> products = productService.getProductByProductTypeId(id);
            model.addAttribute("products", products);
            List<ProductType> productTypeList = productTypeService.getProductTypeList();
            model.addAttribute("productTypes", productTypeList);

            return "/catalog/{id}";
        }

        //добавление в корзину
        Product product = productService.getProductById(idProduct);
        Cart cart = cartService.getCartByUserId(userService.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItem cartItem = new CartItem(product, cart);
        cartItemService.addNewItem(cartItem);

        //отображаемые данные
        List<Product> products = productService.getProductByProductTypeId(id);
        model.addAttribute("products", products);
        List<ProductType> productTypeList = productTypeService.getProductTypeList();
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("tempUser", userService.getTempUser());

        return "/catalog";
    }

    @PostMapping("/catalog")
    public String addToCart2(@RequestParam("btn") String btn, Model model, HttpSession session){

        Long idProduct = Long.parseLong(btn);
        if (userService.getTempUser() == null){
            session.setAttribute("error", "Чтобы добавлять товары в корзину необходимо авторизоваться");

            List<ProductType> productTypeList = productTypeService.getProductTypeList();
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("productTypes", productTypeList);
            model.addAttribute("products", productList);

            return "/catalog";
        }

        Product product = productService.getProductById(idProduct);
        Cart cart = cartService.getCartByUserId(userService.getTempUser().getId());
        cart.setCost(cart.getCost() + product.getPrice());
        CartItem cartItem = new CartItem(product, cart);
        cartItemService.addNewItem(cartItem);


        List<ProductType> productTypeList = productTypeService.getProductTypeList();
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("tempUser", userService.getTempUser());
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);

        return "/catalog";
    }

    @GetMapping("/catalog/cart")
    public String toCart(){
        return "redirect:/user/cart";
    }
}
