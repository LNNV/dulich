package com.dulich.dulich.controller;

import com.dulich.dulich.repository.NewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dulich.dulich.model.News;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class NewsController {
    @Autowired
    private NewsRepository repository;

    @GetMapping("/admin/news")
    public String index(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");

        List<News> newsList = repository.findByKeyword(quely);
        model.addAttribute("newsList", newsList);
        model.addAttribute("query", quely);

        return "admin-news";
    }

    @PostMapping("/admin/news")
    public String create(@ModelAttribute News news, Model model) {
        news.setPostedAt(LocalDateTime.now());
        repository.save(news);
        model.addAttribute("news", news);

        return "redirect:/admin/news";
    }

    @PostMapping("/admin/news/{id}/update")
    public String update(@PathVariable(value="id") long id, @ModelAttribute News news, Model model) {
        News newz = repository.getById(id);

        newz.setContent(news.getContent());
        newz.setPicture(news.getPicture());
        newz.setTopic(news.getTopic());
        newz.setSource(news.getSource());
        newz.setIntro(news.getIntro());

        repository.save(newz);
        return "redirect:/admin/news";
    }

    @PostMapping("/admin/news/{id}/delete")
    public String remove(@PathVariable(value="id") long id, Model model) {
        News newz = repository.getById(id);

        repository.delete(newz);

        return "redirect:/admin/news";
    }

    
}
