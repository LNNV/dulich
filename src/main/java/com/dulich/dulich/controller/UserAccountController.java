package com.dulich.dulich.controller;

import com.dulich.dulich.form.EditForm;
import com.dulich.dulich.model.Account;
import com.dulich.dulich.repository.AccountRepository;
import com.dulich.dulich.service.StoreDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StoreDataService storeDataService;

    @RequestMapping("/user")
    public String edit(Model model) {
        if (storeDataService.get() == null) return "redirect:login";
        model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
        model.addAttribute("username", storeDataService.getUsername());
        model.addAttribute("editForm", new EditForm());
        return "user-account";
    }

    @PostMapping("/user")
    public String editHandler(@ModelAttribute EditForm editForm, Model model) {
        Account account = accountRepository.findByUsername(storeDataService.getUsername()).get();
        String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        model.addAttribute("editForm", editForm);
        if (editForm.getUsername().length()<6 || editForm.getUsername().length()>40) {
            model.addAttribute("usernameError", "Tên tài khoản không hợp lệ");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
            model.addAttribute("username", storeDataService.getUsername());
            return "user-account";
        }
        if (!account.getUsername().equals(editForm.getUsername()))
            if (accountRepository.findByUsername(editForm.getUsername()).isPresent()) {
                model.addAttribute("usernameError", "Tên tài khoản đã tồn tại");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
                model.addAttribute("username", storeDataService.getUsername());
                return "user-account";
            }
        if (!editForm.getPassword().equals(editForm.getRePassword())) {
            model.addAttribute("passwordError", "Mật khẩu không trùng khớp");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
            model.addAttribute("username", storeDataService.getUsername());
            return "user-account";
        }
        if (!account.getPhone().equals(editForm.getPhone()))
            if (accountRepository.findByPhone(editForm.getPhone()).isPresent()) {
                model.addAttribute("phoneError", "Số điện thoại đã được sử dụng");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
                model.addAttribute("username", storeDataService.getUsername());
                return "user-account";
            }
        if (!editForm.getEmail().matches(regexEmail)) {
            model.addAttribute("emailError", "Email không hợp lệ");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
            model.addAttribute("username", storeDataService.getUsername());
            return "user-account";
        }
        if (!account.getEmail().equals(editForm.getEmail()))
            if (accountRepository.findByEmail(editForm.getEmail()).isPresent()) {
                model.addAttribute("emailError", "Email này đã được sử dụng");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
                model.addAttribute("username", storeDataService.getUsername());
                return "user-account";
            }
        account.setUsername(editForm.getUsername());
        account.setFullname(editForm.getFullName());
        account.setPass(editForm.getPassword());
        account.setPhone(editForm.getPhone());
        account.setEmail(editForm.getEmail());
        accountRepository.save(account);
        storeDataService.setUserTempData(account);
        model.addAttribute("userimg", Character.toString(Character.toUpperCase(storeDataService.getUsername().charAt(0))));
        model.addAttribute("username", storeDataService.getUsername());
        return "user-account";
    }
}
