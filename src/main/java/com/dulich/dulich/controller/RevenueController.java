package com.dulich.dulich.controller;

import java.util.List;

import com.dulich.dulich.model.Book;
import com.dulich.dulich.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RevenueController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/admin/revenue")
    public String revenue(Model model) {
        long total = 0;
        List<Book> bookList = bookRepository.findByKeyword("");
        for (Book book : bookList) {
            total += book.getTour().getCost();
        }
        model.addAttribute("revenue", total);
        return "admin-revenue";
    }
}
