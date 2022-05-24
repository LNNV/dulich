package com.dulich.dulich.controller;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Feedback;
import com.dulich.dulich.repository.FeedbackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackManageController {
    @Autowired
    FeedbackRepository feedbackRepository;

    @GetMapping("/admin/feedback")
    public String index(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");
        List<Feedback> fbList = feedbackRepository.findByKeyword(quely);
        model.addAttribute("fbList", fbList);
        model.addAttribute("query", quely);
        return "admin-feedback";
    }

    @PostMapping("/admin/feedback/{id}/delete")
    public String remove(@PathVariable(value="id") long id, Model model) {
        Feedback feedback = feedbackRepository.getById(id);

        feedbackRepository.delete(feedback);

        return "redirect:/admin/feedback";
    }

}
