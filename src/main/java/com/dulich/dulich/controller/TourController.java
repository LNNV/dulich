package com.dulich.dulich.controller;

import java.util.List;
import java.util.Optional;

import com.dulich.dulich.model.Tour;
import com.dulich.dulich.repository.TourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {
    @Autowired
    TourRepository tourRepository;

    @GetMapping("/tour-list")
    public String tour(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");

        List<Tour> tourList = tourRepository.findByKeyword(quely);
        model.addAttribute("tourList", tourList);
        model.addAttribute("query", quely);

        return "user-tour-list";
    }
}
