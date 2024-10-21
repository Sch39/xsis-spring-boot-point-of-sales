package dev.sch39.ecommerce.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
  @GetMapping({ "/shop", "/shop/" })
  public String showShopPage() {
    return "user/shop/index";
  }
}
