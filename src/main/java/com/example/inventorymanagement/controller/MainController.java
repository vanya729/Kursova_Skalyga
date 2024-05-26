package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @ModelAttribute("product")
    public Product product() {
        return new Product();
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/search/results")
    public String searchResults(@RequestParam(value = "id", required = false) Long id,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "supplier", required = false) String supplier,
                                @RequestParam(value = "deliveryDate", required = false)
                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate,
                                Model model) {
        List<Product> results = productService.searchProducts(id, name, supplier, deliveryDate);
        model.addAttribute("results", results);
        return "index";
    }
}
