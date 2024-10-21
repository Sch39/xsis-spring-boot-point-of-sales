package dev.sch39.ecommerce.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.services.CategoryService;
import dev.sch39.ecommerce.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/product-management/product")
public class ProductController {
  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @GetMapping({ "", "/" })
  public String getProducts(Model model, @RequestParam(required = false, defaultValue = "all") List<String> filter,
      ProductEntity productEntity) {
    List<ProductEntity> products = new ArrayList<>();

    if (filter.contains("deleted")
        && filter.contains("not-deleted")) {
      products.addAll(productService.getAllProduct());
    } else if (filter.contains("deleted")) {
      products.addAll(productService.getAllProduct(true));
    } else if (filter.contains("not-deleted")) {
      products.addAll(productService.getAllProduct(false));
    } else {
      products.addAll(productService.getAllProduct());
    }

    model.addAttribute("products", products);
    model.addAttribute("categories", categoryService.getAllCategory());
    return "admin/product/index";
  }

  @PostMapping("/save")
  public String saveProduct(@ModelAttribute ProductEntity productEntity, BindingResult result) {
    if (!result.hasErrors()) {
      productService.save(productEntity);
    }
    return "redirect:/admin/product-management/product";
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable("id") Long id) {
    productService.deleteById(id);
    return "redirect:/admin/product-management/product";
  }

}
