package com.dulich.dulich.controller;

import com.dulich.dulich.form.LoginForm;
import com.dulich.dulich.model.Account;
import com.dulich.dulich.repository.AccountRepository;
import com.dulich.dulich.service.StoreDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StoreDataService storeDataService;
    
    @RequestMapping("/login")
    public String login(Model model) {
        if (storeDataService.get() != null) return "redirect:user";
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginHandler(@ModelAttribute LoginForm loginForm, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("loginForm", loginForm);
        if (accountRepository.findByUsername(loginForm.getUsername()).isPresent()) {
            Account account = accountRepository.findByUsername(loginForm.getUsername()).get();
            if (account.getPass().equals(loginForm.getPassword())) {
                if (account.getRole().equals("customer")) {
                    storeDataService.setUserTempData(account);
                    redirectAttributes.addFlashAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
                    redirectAttributes.addFlashAttribute("username", storeDataService.getUsername());
                    return "redirect:user";
                }
                return "redirect:home";
            } else {
                model.addAttribute("passwordError", "Sai mật khẩu");
                return "login";
            }
        } else {
            model.addAttribute("usernameError", "Tài khoản không tồn tại");
            return "login";
        }
    }
}
