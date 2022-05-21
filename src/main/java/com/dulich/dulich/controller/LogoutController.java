package com.dulich.dulich.controller;

import com.dulich.dulich.service.StoreDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
    
    @Autowired
    private StoreDataService storeDataService;

    @RequestMapping("/logout")
    public String logout() {
        storeDataService.logOut();
        return "redirect:login";
    }
}
