package com.ndiaye.mvcsession.controller;

import com.ndiaye.mvcsession.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public String getProductById(Model model, @PathVariable Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "products/product";
    }
}
