package com.belyaeva.controller;

import com.belyaeva.entity.Cart;
import com.belyaeva.entity.Product;
import com.belyaeva.entity.ProductType;
import com.belyaeva.entity.User;
import com.belyaeva.services.impl.CartServiceImpl;
import com.belyaeva.services.impl.ProductServiceImpl;
import com.belyaeva.services.impl.ProductTypeServiceImpl;
import com.belyaeva.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private ProductTypeServiceImpl productTypeServiceImpl;


    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        List<Cart> orders = cartServiceImpl.getUnreadyOrderList();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @PostMapping("/admin")
    public String reformOrder(@RequestParam("btn") String btn, @RequestParam("cartId") Long cartId, Model model){
        if (btn.equals("ready")){
            Cart cart = cartServiceImpl.getCartById(cartId);
            cartServiceImpl.moveOrderToReady(cart);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/catalog")
    public String getAdminCatalog(@RequestParam(defaultValue="-1") Long id, Model model){
        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        List<Product> productList = productServiceImpl.getAllProducts();

        User user = userServiceImpl.getTempUser();
        model.addAttribute("tempUser", user);
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("products", productList);


        model.addAttribute("add_product", new Product());
        if (id != -1){ //show put part or not
            Product p = productServiceImpl.getProductById(id);
            p.setNameProductType(p.getProductType().getName());
            model.addAttribute("put_product", p);
        }
        else
            model.addAttribute("put_product", null);

        return "catalog_admin";
    }

    @GetMapping("/admin/catalog/{id}")
    public String getAdminCatalogByProductTypeId(@RequestParam(defaultValue="-1") Long id, @PathVariable("id") Long idSearch,Model model){
        List<Product> products = productServiceImpl.getProductByProductTypeId(idSearch);
        model.addAttribute("products", products);
        List<ProductType> productTypeList = productTypeServiceImpl.getProductTypeList();
        model.addAttribute("productTypes", productTypeList);
        model.addAttribute("tempUser", userServiceImpl.getTempUser());


        model.addAttribute("add_product", new Product());
        if (id != -1)
            model.addAttribute("put_product", productServiceImpl.getProductById(id));
        else
            model.addAttribute("put_product", null);

        return "catalog_admin";
    }

    @GetMapping("/admin/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/admin/catalog/{id}";
    }

    @PostMapping("/admin/catalog")
    public String addNewProduct(@ModelAttribute("add_product") Product product){
        productServiceImpl.addNewProduct(product);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/admin/catalog/{id}")
    public String addNewProductIfOnProductTypeChoice(@ModelAttribute("add_product") Product product){
        productServiceImpl.addNewProduct(product);
        return "redirect:/admin/catalog";
    }

    @DeleteMapping("/admin/catalog")
    public String deleteItem(@RequestParam("btn_del") Long id){
        productServiceImpl.deleteProduct(id);
        return "redirect:/admin/catalog";
    }

    @DeleteMapping("/admin/catalog/{id}")
    public String deleteItemIfOnProductTypeChoice(@RequestParam("btn_del") Long id){
        productServiceImpl.deleteProduct(id);
        return "redirect:/admin/catalog/{id}";
    }

    @PutMapping("/admin/catalog")
    public String putProduct(@ModelAttribute("put_product") Product product){
        productServiceImpl.changeProduct(product);
        return "redirect:/admin/catalog";
    }

    @PutMapping("/admin/catalog/{id}")
    public String putProductIfOnProductTypeChoice(@ModelAttribute("put_product") Product product){
        productServiceImpl.changeProduct(product);
        return "redirect:/admin/catalog/{id}";
    }

}
