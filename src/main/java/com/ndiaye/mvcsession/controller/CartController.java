package com.ndiaye.mvcsession.controller;

import com.ndiaye.mvcsession.dto.Cart;
import com.ndiaye.mvcsession.dto.CartItem;
import com.ndiaye.mvcsession.dto.ProductDto;
import com.ndiaye.mvcsession.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("shoppingCart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@ModelAttribute("cartItem") CartItem cartItem, @ModelAttribute("shoppingCart") Cart cart,
                                   @PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);
        cartItem.setProductId(id);
        cartItem.setLabel(productDto.getLabel());
        cartItem.setPrice(productDto.getPrice());
        cart.add(cartItem);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String showCart(@ModelAttribute("shoppingCart") Cart cart, Model model) {
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

    @ModelAttribute("shoppingCart")
    public Cart shoppingCart() {
        return new Cart();
    }
}
