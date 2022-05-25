package com.dulich.dulich.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.dulich.dulich.model.Account;
import com.dulich.dulich.model.Feedback;
import com.dulich.dulich.model.News;
import com.dulich.dulich.repository.AccountRepository;
import com.dulich.dulich.repository.FeedbackRepository;
import com.dulich.dulich.repository.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @RequestMapping("/")
    public String goToHome() {
        return "redirect:home";
    }
    @RequestMapping("/home")
    public String home(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        return "home";
    }

    @PostMapping("home")
    public String feedback(@CookieValue(value = "username", defaultValue = "") String username, @RequestParam(name = "content") String content, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        Feedback feedback = new Feedback();
        Account account = accountRepository.findByUsername(username).get();
        feedback.setAccount(account);
        feedback.setContent(content);
        feedback.setUpAt(LocalDateTime.now());
        feedbackRepository.save(feedback);
        return "home";
    }

    @RequestMapping("/about")
    public String about(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        return "about";
    }

    @GetMapping("/news")
    public String news(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        List<News> newsList = newsRepository.findByKeyword("");
        model.addAttribute("newsList", newsList);
        return "news";
    }

    @GetMapping("/news/detail/{id}")
    public String newsDetail(@PathVariable(value="id") long id, @CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        News news = newsRepository.findById(id).get();
        model.addAttribute("news", news);
        return "detail_news";
    }
}
