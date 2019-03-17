package com.openclassrooms.lambazon.store.controller;

import com.openclassrooms.lambazon.store.domain.model.Cart;
import com.openclassrooms.lambazon.store.domain.model.Order;
import com.openclassrooms.lambazon.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class OrderController {

    private Boolean enableCheckoutsSwitch = true;
    private OrderService orderService;

    @Autowired
    public OrderController( OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/order/cart")
    public String getCart(Model model, HttpSession session)
    {
        // TODO: Make the attribute name a constant
        // Add a cart to the session.
        // Due to Lambazon's architectural governance we have agreed to use the
        // HttpSession rather than a session scoped bean
        session.setAttribute("cart", orderService.getCart());
        model.addAttribute("cart", (Cart) session.getAttribute("cart"));
        return "cart";
    }

    @PostMapping("/order/addToCart")
    public String addToCart(@RequestParam("productId") Long productId, HttpSession session)
    {
        Cart cart = (Cart) session.getAttribute("cart");
        boolean success = orderService.addToCart(productId, cart);

        if (success) {
            return "redirect:/order/cart";
        } else {
            return "redirect:/products";
        }
    }

    @PostMapping("order/removeFromCart")
    public String removeFromCart(@RequestParam Long productId, HttpSession session)
    {
        orderService.removeFromCart(productId);

        return "redirect:/order/cart";
    }

    @GetMapping("/order")
    public String getOrderForm(Order order, HttpSession session)
    {
        return "order";
    }

    @PostMapping("/order")
    public String createOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, HttpSession session)
    {
        if (orderService.isCartEmpty()){
            result.reject("cart.empty");
        }

        // TODO: We no longer need the enabled checkout feature flag. Remove it.
        if (!result.hasErrors() && enableCheckoutsSwitch) {
            // FIXME: remember to get CartLines and add them to the Order
            orderService.createOrder(order);
            return "orderCompleted";
        } else {
            return "order";
        }
    }
}
