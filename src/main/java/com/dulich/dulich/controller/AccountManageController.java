package com.dulich.dulich.controller;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Account;
import com.dulich.dulich.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AccountManageController {
    
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/admin/account")
    public String index(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");

        List<Account> accountList = accountRepository.findByKeyword(quely);
        model.addAttribute("accountList", accountList);
        model.addAttribute("query", quely);

        return "admin-account";
    }

    @PostMapping("/admin/account/{id}/update")
    public String update(@PathVariable(value="id") long id, @ModelAttribute Account account, Model model) {
        Account acc = accountRepository.getById(id);

        acc.setFullname(account.getFullname());
        acc.setPhone(account.getPhone());
        acc.setEmail(account.getEmail());
        acc.setUsername(account.getUsername());
        acc.setPass(account.getPass());

        accountRepository.save(acc);

        return "redirect:/admin/account";
    }
    
}
