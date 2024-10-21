package dev.sch39.ecommerce.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.sch39.ecommerce.entities.VariantEntity;
import dev.sch39.ecommerce.services.CategoryService;
import dev.sch39.ecommerce.services.ProductService;
import dev.sch39.ecommerce.services.VariantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/product-management/variant")
public class VariantController {
  @Autowired
  VariantService variantService;
  @Autowired
  ProductService productService;
  @Autowired
  CategoryService categoryService;

  @GetMapping({ "", "/" })
  public String getVariants(Model model, @RequestParam(required = false, defaultValue = "all") List<String> filter,
      VariantEntity variantEntity) {
    List<VariantEntity> variants = new ArrayList<>();
    if (filter.contains("deleted")
        && filter.contains("not-deleted")) {
      variants.addAll(variantService.getAllVariant());
    } else if (filter.contains("deleted")) {
      variants.addAll(variantService.getAllVariant(true));
    } else if (filter.contains("not-deleted")) {
      variants.addAll(variantService.getAllVariant(false));
    } else {
      variants.addAll(variantService.getAllVariant());
    }

    model.addAttribute("variants", variants);
    model.addAttribute("categories", categoryService.getAllCategory());
    model.addAttribute("products", productService.getAllProduct());
    return "admin/variant/index";
  }

}
