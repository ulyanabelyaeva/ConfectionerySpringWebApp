package com.belyaeva.controller;

import com.belyaeva.entity.CartEntity;
import com.belyaeva.entity.ProductEntity;
import com.belyaeva.services.abstractions.ProductFacade;
import com.belyaeva.services.impl.CartServiceImpl;
import com.belyaeva.services.impl.ProductServiceImpl;
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
    private ProductFacade<Model, Model> productFacade;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/admin")
    public String getAdminPage(Model model){
        model.addAttribute("tempUser", userServiceImpl.getTempUser());
        List<CartEntity> orders = cartServiceImpl.getUnreadyOrderList();
        model.addAttribute("orders", orders);
        return "admin";
    }

    @PostMapping("/admin")
    public String reformOrder(@RequestParam("btn") String btn, @RequestParam("cartId") Long cartId, Model model){
        if (btn.equals("ready")){
            CartEntity cartEntity = cartServiceImpl.getCartById(cartId);
            cartServiceImpl.moveOrderToReady(cartEntity);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/catalog")
    public String getAdminCatalog(@RequestParam(defaultValue="-1") Long id, Model model){
        productFacade.getProductsAndUser(model);
        showPutOrAdd(id, model);
        return "catalog_admin";
    }
    @GetMapping("/admin/catalog/{id}")
    public String getAdminCatalogByProductTypeId(@RequestParam(defaultValue="-1") Long id, @PathVariable("id") Long idSearch,Model model){
        productFacade.getProductsByTypeAndUser(idSearch, model);
        showPutOrAdd(id,model);
        return "catalog_admin";
    }

    private void showPutOrAdd(Long id, Model model) {
        if (id != -1){
            ProductEntity product = productServiceImpl.getProductById(id);
            product.setNameProductType(product.getProductType().getName());
            model.addAttribute("put_product", product);
        }
        else{
            model.addAttribute("add_product", new ProductEntity());
            model.addAttribute("put_product", null);
        }
    }

    @GetMapping("/admin/catalog/catalog/{id}")
    public String redirectCatalog(@PathVariable("id") Long id){
        return "redirect:/admin/catalog/{id}";
    }

    @PostMapping("/admin/catalog")
    public String addNewProduct(@ModelAttribute("add_product") ProductEntity productEntity){
        productServiceImpl.addNewProduct(productEntity);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/admin/catalog/{id}")
    public String addNewProductIfOnProductTypeChoice(@ModelAttribute("add_product") ProductEntity productEntity){
        productServiceImpl.addNewProduct(productEntity);
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
    public String putProduct(@ModelAttribute("put_product") ProductEntity productEntity){
        productServiceImpl.changeProduct(productEntity);
        return "redirect:/admin/catalog";
    }

    @PutMapping("/admin/catalog/{id}")
    public String putProductIfOnProductTypeChoice(@ModelAttribute("put_product") ProductEntity productEntity){
        productServiceImpl.changeProduct(productEntity);
        return "redirect:/admin/catalog/{id}";
    }

}
