package com.dulich.dulich.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TourController {
    @Autowired
    TourRepository tourRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/tours")
    public String tour(@CookieValue(value = "username", defaultValue = "") String username, @RequestParam(name = "query") Optional<String> query, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        
        String quely = query.orElse("");
        List<Tour> tourList = tourRepository.findByKeyword(quely);
        model.addAttribute("tourList", tourList);
        model.addAttribute("query", quely);

        return "tours";
    }

    @RequestMapping("/tour/detail/{id}")
    public String detail(@CookieValue(value = "username", defaultValue = "") String username, @PathVariable(value="id") long id, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        Tour tour = tourRepository.findById(id).get();
        model.addAttribute("picture", tour.getPicture());
        model.addAttribute("name", tour.getName());
        model.addAttribute("cost", tour.getCost());
        model.addAttribute("startday", tour.getStartday());
        model.addAttribute("endday", tour.getEndday());
        model.addAttribute("numseat", tour.getNumseat());
        model.addAttribute("description", tour.getDescription());
        model.addAttribute("place", tour.getPlace());
        model.addAttribute("idtour", tour.getId());
        return "tour-detail";
    }

    @RequestMapping("/tour/book/{id}")
    public String preBook(@CookieValue(value = "username", defaultValue = "") String username, @PathVariable(value="id") long id, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        Tour tour = tourRepository.findById(id).get();
        model.addAttribute("picture", tour.getPicture());
        model.addAttribute("name", tour.getName());
        model.addAttribute("cost", tour.getCost());
        model.addAttribute("startday", tour.getStartday());
        model.addAttribute("endday", tour.getEndday());
        model.addAttribute("numseat", tour.getNumseat());
        model.addAttribute("idtour", tour.getId());
        model.addAttribute("place", tour.getPlace());
        if (tour.getNumseat() == 0) {
            model.addAttribute("condition", 0);
            model.addAttribute("numError", "Tour đã hết chỗ");
            return "book-tour";
        }
        if (tour.getStartday().before(new Date())) {
            model.addAttribute("condition", 0);
            model.addAttribute("dateError", "Tour đã quá hạn");
            return "book-tour";
        }
        model.addAttribute("condition", 1);
        return "book-tour";
    }

    @PostMapping("/tour/book/{id}")
    public String book(@CookieValue(value = "username", defaultValue = "") String username, @RequestParam(name = "payment") String payment, @PathVariable(value="id") long id, Model model) {
        if (username.equals("")) model.addAttribute("loged", null);
        else model.addAttribute("loged", 1);
        Tour tour = tourRepository.findById(id).get();
        Account account = accountRepository.findByUsername(username).get();
        Book book = new Book();
        book.setTour(tour);
        book.setAccount(account);
        book.setBookat(LocalDateTime.now());
        book.setPayment(payment);
        bookRepository.save(book);
        tour.setNumseat(tour.getNumseat() - 1);
        tourRepository.save(tour);
        return "redirect:/tour/book/{id}";
    }
}
