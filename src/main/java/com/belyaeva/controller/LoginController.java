package com.belyaeva.controller;

import com.belyaeva.entity.Cart;
import com.belyaeva.entity.User;
import com.belyaeva.services.CartService;
import com.belyaeva.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/login")
    public String log(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@ModelAttribute("user") User user, HttpSession session) {

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            session.setAttribute("userError", "Пароли не совпадают");
            return "redirect:/reg";
        }

        if (!userService.saveUser(user)){
            session.setAttribute("userError", "Пользователь с таким телефоном уже существует");
            return "redirect:/reg";
        } else {
            cartService.addNewCart(new Cart(user, false));
            return "redirect:/user";
        }

    }
}
