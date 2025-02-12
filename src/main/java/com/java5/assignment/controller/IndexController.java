package com.java5.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("cart")
public class IndexController {

    @GetMapping("/home")
    public String index() {
        return "home";
    }

    @GetMapping("/cart")
    public String cart(@ModelAttribute("cart") Map<String, Integer> cart, Model model) {

        Integer total = cart.values().stream().mapToInt(i -> i).sum();

        model.addAttribute("total", total);

        return "cart";
    }

    @GetMapping("/products/{id}")
    public String products(@PathVariable("id") String id, Model model) {

        model.addAttribute("productId", id);

        return "product-detail";
    }

    @GetMapping("/category/{id}")
    public String category(@PathVariable("id") String id) {
        return "category-view";
    }

    @ModelAttribute("cart")
    public Map<String, Integer> cart() {
        return new HashMap<String, Integer>();
    }

}
