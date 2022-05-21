package com.dulich.dulich.controller;

import com.dulich.dulich.form.SignupForm;
import com.dulich.dulich.model.Account;
import com.dulich.dulich.repository.AccountRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class SignUpController {

    @Autowired
    private AccountRepository accountRepository;
    
    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String signupHandler(@ModelAttribute SignupForm signupForm, Model model) {
        String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        model.addAttribute("signupForm", signupForm);
        if (signupForm.getUsername().length()<6 || signupForm.getUsername().length()>40) {
            model.addAttribute("usernameError", "Tên tài khoản không hợp lệ");
            return "signup";
        }
        if (accountRepository.findByUsername(signupForm.getUsername()).isPresent()) {
            model.addAttribute("usernameError", "Tên tài khoản đã tồn tại");
            return "signup";
        }
        if (!signupForm.getPassword().equals(signupForm.getRePassword())) {
            model.addAttribute("passwordError", "Mật khẩu không trùng khớp");
            return "signup";
        }
        if (accountRepository.findByPhone(signupForm.getPhone()).isPresent()) {
            model.addAttribute("phoneError", "Số điện thoại đã được sử dụng");
            return "signup";
        }
        if (!signupForm.getEmail().matches(regexEmail)) {
            model.addAttribute("emailError", "Email không hợp lệ");
            return "signup";
        }
        if (accountRepository.findByEmail(signupForm.getEmail()).isPresent()) {
            model.addAttribute("emailError", "Email này đã được sử dụng");
            return "signup";
        }
        Account account = new Account();
        account.setUsername(signupForm.getUsername());
        account.setFullname(signupForm.getFullName());
        account.setPass(signupForm.getPassword());
        account.setPhone(signupForm.getPhone());
        account.setEmail(signupForm.getEmail());
        account.setRole("customer");
        accountRepository.save(account);
        
        return "redirect:login";
    }
}
