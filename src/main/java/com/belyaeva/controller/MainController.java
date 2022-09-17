package com.belyaeva.controller;

import com.belyaeva.entity.Product;
import com.belyaeva.entity.ProductType;
import com.belyaeva.entity.User;
import com.belyaeva.services.ProductService;
import com.belyaeva.services.ProductTypeService;
import com.belyaeva.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/catalog")
    public String getProducts(Model model){
        List<ProductType> productTypeList = productTypeService.getProductTypeList();
        List<Product> productList = productService.getAllProducts();
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
        return "catalog";
    }

    @GetMapping("/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/catalog/{id}";
    }

}
