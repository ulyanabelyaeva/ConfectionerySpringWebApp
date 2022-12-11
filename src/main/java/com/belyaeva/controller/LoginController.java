package com.belyaeva.controller;

import com.belyaeva.model.entity.CartEntity;
import com.belyaeva.model.entity.UserEntity;
import com.belyaeva.model.services.impl.CartServiceImpl;
import com.belyaeva.model.services.impl.UserServiceImpl;
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
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @GetMapping("/login")
    public String log(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @GetMapping("/reg")
    public String reg(){
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(@ModelAttribute("user") UserEntity user, HttpSession session) {

        if (!user.getPassword().equals(user.getPasswordConfirm())){
            session.setAttribute("userError", "Пароли не совпадают");
            return "redirect:/reg";
        }

        if (!userServiceImpl.saveUser(user)){
            session.setAttribute("userError", "Пользователь с таким телефоном уже существует");
            return "redirect:/reg";
        } else {
            cartServiceImpl.addNewCart(CartEntity.builder()
                    .user(user)
                    .status(false)
                    .ready(false)
                    .cost(0)
                    .build());
            return "redirect:/login";
        }

    }
}