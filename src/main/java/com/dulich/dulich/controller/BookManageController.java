package com.dulich.dulich.controller;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Book;
import com.dulich.dulich.repository.AccountRepository;
import com.dulich.dulich.repository.BookRepository;
import com.dulich.dulich.repository.TourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookManageController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/admin/book")
    public String index(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");
        List<Book> bookList = bookRepository.findByKeyword(quely);
        model.addAttribute("bookList", bookList);
        model.addAttribute("query", quely);
        return "admin-booked";
    }

    @PostMapping("/admin/book/{id}/delete")
    public String remove(@PathVariable(value="id") long id, Model model) {
        Book book = bookRepository.getById(id);

        bookRepository.delete(book);

        return "redirect:/admin/book";
    }
}
