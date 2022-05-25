package com.dulich.dulich.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.dulich.dulich.form.EditForm;
import com.dulich.dulich.model.Account;
import com.dulich.dulich.model.Book;
import com.dulich.dulich.model.Tour;
import com.dulich.dulich.repository.AccountRepository;
import com.dulich.dulich.repository.BookRepository;
import com.dulich.dulich.repository.TourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAccountController {
    
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TourRepository tourRepository;


    @RequestMapping("/user")
    public String edit(@CookieValue(value = "username", defaultValue = "") String username, @ModelAttribute EditForm editForm, Model model) {
        model.addAttribute("loged", 1);
        model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
        model.addAttribute("username", username);
        Account account = accountRepository.findByUsername(username).get();
        editForm = new EditForm();
        editForm.setUsername(username);
        editForm.setPassword(account.getPass());
        editForm.setRePassword("");
        editForm.setFullName(account.getFullname());
        editForm.setPhone(account.getPhone());
        editForm.setEmail(account.getEmail());
        model.addAttribute("editForm", editForm);
        return "user-account";
    }

    @PostMapping("/user")
    public String editHandler(@CookieValue(value = "username", defaultValue = "") String username, @ModelAttribute EditForm editForm, Model model, HttpServletResponse response) {
        Account account = accountRepository.findByUsername(username).get();
        String regexEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        model.addAttribute("editForm", editForm);
        if (editForm.getUsername().length()<6 || editForm.getUsername().length()>40) {
            model.addAttribute("usernameError", "Tên tài khoản không hợp lệ");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
            model.addAttribute("username", username);
            return "user-account";
        }
        if (!account.getUsername().equals(editForm.getUsername()))
            if (accountRepository.findByUsername(editForm.getUsername()).isPresent()) {
                model.addAttribute("usernameError", "Tên tài khoản đã tồn tại");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
                model.addAttribute("username", username);
                return "user-account";
            }
        if (!editForm.getPassword().equals(editForm.getRePassword())) {
            model.addAttribute("passwordError", "Mật khẩu không trùng khớp");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
            model.addAttribute("username", username);
            return "user-account";
        }
        if (!account.getPhone().equals(editForm.getPhone()))
            if (accountRepository.findByPhone(editForm.getPhone()).isPresent()) {
                model.addAttribute("phoneError", "Số điện thoại đã được sử dụng");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
                model.addAttribute("username", username);
                return "user-account";
            }
        if (!editForm.getEmail().matches(regexEmail)) {
            model.addAttribute("emailError", "Email không hợp lệ");
            model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
            model.addAttribute("username", username);
            return "user-account";
        }
        if (!account.getEmail().equals(editForm.getEmail()))
            if (accountRepository.findByEmail(editForm.getEmail()).isPresent()) {
                model.addAttribute("emailError", "Email này đã được sử dụng");
                model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
                model.addAttribute("username", username);
                return "user-account";
            }
        account.setUsername(editForm.getUsername());
        account.setFullname(editForm.getFullName());
        account.setPass(editForm.getPassword());
        account.setPhone(editForm.getPhone());
        account.setEmail(editForm.getEmail());
        accountRepository.save(account);
        Cookie cookie = new Cookie("username", account.getUsername());
        response.addCookie(cookie);
        model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
        model.addAttribute("username", username);
        return "user-account";
    }

    @GetMapping("user/booked")
    public String index(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        model.addAttribute("loged", 1);
        Account account = accountRepository.findByUsername(username).get();
        List<Book> bookList = bookRepository.findByAccount(account);
        model.addAttribute("bookList", bookList);
        model.addAttribute("userimg", Character.toString(Character.toUpperCase(username.charAt(0))));
        model.addAttribute("username", username);
        return "order";
    }

    @PostMapping("/user/booked/{id}/delete")
    public String remove(@PathVariable(value="id") long id, Model model) {
        Book book = bookRepository.getById(id);
        Tour tour = book.getTour();
        bookRepository.delete(book);
        tour.setNumseat(tour.getNumseat() + 1);
        tourRepository.save(tour);
        return "redirect:/user/booked";
    }
}
