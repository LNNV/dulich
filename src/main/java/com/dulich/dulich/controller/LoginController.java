package com.dulich.dulich.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.dulich.dulich.form.LoginForm;
import com.dulich.dulich.model.Account;
import com.dulich.dulich.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginHandler(@ModelAttribute LoginForm loginForm, Model model, HttpServletResponse response) {
        model.addAttribute("loginForm", loginForm);
        if (accountRepository.findByUsername(loginForm.getUsername()).isPresent()) {
            Account account = accountRepository.findByUsername(loginForm.getUsername()).get();
            if (account.getPass().equals(loginForm.getPassword())) {
                if (account.getRole().equals("customer")) {
                    Cookie cookie = new Cookie("username", account.getUsername());
                    response.addCookie(cookie);
                    cookie = new Cookie("role", "customer");
                    response.addCookie(cookie);
                    return "redirect:home";
                } else {
                    Cookie cookie = new Cookie("username", account.getUsername());
                    response.addCookie(cookie);
                    cookie = new Cookie("role", "admin");
                    response.addCookie(cookie);
                    return "redirect:admin/account";
                }
            } else {
                model.addAttribute("passwordError", "Sai m???t kh???u");
                return "login";
            }
        } else {
            model.addAttribute("usernameError", "T??i kho???n kh??ng t???n t???i");
            return "login";
        }
    }
}
