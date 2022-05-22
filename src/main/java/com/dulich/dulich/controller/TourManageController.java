package com.dulich.dulich.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import com.dulich.dulich.form.TourForm;
import com.dulich.dulich.model.Tour;
import com.dulich.dulich.repository.TourRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourManageController {
    
    @Autowired
    TourRepository tourRepository;

    @GetMapping("/admin/tour")
    public String tour(@RequestParam(name = "query") Optional<String> query, Model model) {
        String quely = query.orElse("");

        List<Tour> tourList = tourRepository.findByKeyword(quely);
        model.addAttribute("tourList", tourList);
        model.addAttribute("query", quely);
        model.addAttribute("tourForm", new TourForm());
        return "admin-tour";
    }

    @PostMapping("/admin/tour")
    public String create(@ModelAttribute TourForm tourForm, Model model) {
        model.addAttribute("tourForm", tourForm);
        Tour tour = new Tour();
        tour.setName(tourForm.getName());
        tour.setPicture(tourForm.getPicture());
        tour.setStartplace(tourForm.getStartplace());
        tour.setDescription(tourForm.getDescription());
        tour.setNumseat(tourForm.getNumseat());
        tour.setCost(tourForm.getCost());
        try {
            tour.setStartday(new SimpleDateFormat("yyyy-MM-dd").parse(tourForm.getStartday()));
        } catch (ParseException e) {
            
        }
        try {
            tour.setEndday(new SimpleDateFormat("yyyy-MM-dd").parse(tourForm.getEndday()));
        } catch (ParseException e) {

        }

        tourRepository.save(tour);
        return "redirect:/admin/tour";
    }

    @PostMapping("/admin/tour/{id}/update")
    public String update(@PathVariable(value="id") long id, @ModelAttribute TourForm tourForm, Model model) {
        model.addAttribute("tourForm", tourForm);

        Tour tour = tourRepository.findById(id).get();
        tour.setName(tourForm.getName());
        tour.setPicture(tourForm.getPicture());
        tour.setStartplace(tourForm.getStartplace());
        tour.setDescription(tourForm.getDescription());
        tour.setNumseat(tourForm.getNumseat());
        tour.setCost(tourForm.getCost());

        try {
            tour.setStartday(new SimpleDateFormat("yyyy-MM-dd").parse(tourForm.getStartday()));
        } catch (ParseException e) {
            
        }

        try {
            tour.setEndday(new SimpleDateFormat("yyyy-MM-dd").parse(tourForm.getEndday()));
        } catch (ParseException e) {

        }

        tourRepository.save(tour);
        return "redirect:/admin/tour";
    }

    @PostMapping("/admin/tour/{id}/delete")
    public String delete(@PathVariable(value="id") long id, @ModelAttribute Tour tour, Model model) {
        Tour temp = tourRepository.getById(id);

        tourRepository.delete(temp);

        return "redirect:/admin/tour";
    }
}
