package dev.sch39.ecommerce.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
  @GetMapping({ "/cart", "/cart/" })
  public String showCartPage(Model model) {
    return "user/cart/index";
  }
}
