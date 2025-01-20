package com.java5.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("cart")
public class CartController {

    @PostMapping("/cart/change-quantity/{product_id}")
    public String changeQuantity(
            @PathVariable("product_id") String productId,
            Model model,
            @RequestHeader(value = "Referer", required = false) String referer,
            @RequestParam String value,
            @ModelAttribute("cart") Map<String, Integer> cart
    ) {
        if (cart.containsKey(productId)) {
            cart.put(productId, Integer.parseInt(value));
            if (Integer.parseInt(value) < 1) {
                cart.remove(productId);
            }
        }

        return referer != null ? "redirect:" + referer : "redirect:/";
    }

    @GetMapping("/cart/remove/{product_id}")
    public String removeProduct(
            @PathVariable("product_id") String productId,
            @RequestHeader(value = "Referer", required = false) String referer,
            @ModelAttribute("cart") Map<String, Integer> cart
    ){

        cart.remove(productId);

        return referer != null ? "redirect:" + referer : "redirect:/";
    }
    @PostMapping("/cart/add/{product_id}")
    public String addCart(
            @PathVariable String product_id,
            @RequestParam String quantity,
            Model model,
            @RequestHeader(value = "Referer", required = false) String referer,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("cart") Map<String, Integer> productList
    ) {

        String redirect = referer != null ? "redirect:" + referer : "redirect:/";

        if (Integer.parseInt(quantity) < 1) {
            return redirect;
        }

        if (productList.containsKey(product_id)) {
            productList.put(product_id, productList.get(product_id) + Integer.parseInt(quantity));
            return redirect;
        }

        productList.put(product_id, Integer.parseInt(quantity));
        return redirect;
    }


    @ModelAttribute("cart")
    public Map<String, Integer> cart() {
        return new HashMap<String, Integer>();
    }

}
