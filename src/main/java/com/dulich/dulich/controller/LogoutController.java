package com.dulich.dulich.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
    
    @RequestMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", "");
        response.addCookie(cookie);
        cookie = new Cookie("role", "");
        response.addCookie(cookie);
        return "redirect:home";
    }
}
