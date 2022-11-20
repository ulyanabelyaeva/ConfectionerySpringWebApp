package com.belyaeva.controller;

import com.belyaeva.entity.Cart;
import com.belyaeva.entity.User;
import com.belyaeva.services.impl.CartServiceImpl;
import com.belyaeva.services.impl.UserServiceImpl;
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

        if (!userServiceImpl.saveUser(user)){
            session.setAttribute("userError", "Пользователь с таким телефоном уже существует");
            return "redirect:/reg";
        } else {
            cartServiceImpl.addNewCart(new Cart(user));
            return "redirect:/login";
        }

    }
}

public interface Strategy<T, U> {
    Optional<U> execute(T model);
}

public class UserAdminStrategy implements Strategy<User, String> {
    public Optional<String> execute(User model) {
        if (model.someData() == getAnotherData()) {
            return Optional.of("redirect:/admin/path");
        }
        return Optional.ofNullable(null);
    }
}

public class UserCustomerStrategy<User, String> {
    public String execute(User model) {
        return "customer"
    }
}

public class Controller {

    @Autowired
    private final List<Strategy<User, String>> strategies;

    public void main(User user) {
        String result;
        for (var strategy : strategies) {
            result = strategy.execute(user); 
            if (result != null) {
                return result;
            }
        }
    }
}
