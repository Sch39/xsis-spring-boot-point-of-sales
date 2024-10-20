package dev.sch39.ecommerce.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.sch39.ecommerce.dtos.response.CategoryResponseDto;
import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.services.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/admin/product-management/category")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @GetMapping({ "", "/" }) // or use configuration with implement then override configurePathMatch
  public String getCategories(Model model, @RequestParam(required = false, defaultValue = "all") List<String> filter) {
    List<CategoryEntity> categories = new ArrayList<>();
    if (filter.contains("deleted")
        && filter.contains("not-deleted")) {
      categories.addAll(categoryService.getAllCategory());
    } else if (filter.contains("deleted")) {
      categories.addAll(categoryService.getAllCategory(true));
    } else if (filter.contains("not-deleted")) {
      categories.addAll(categoryService.getAllCategory(false));
    } else {
      categories.addAll(categoryService.getAllCategory());
    }
    List<CategoryResponseDto> categoryResponseDtos = new ArrayList<>();
    for (CategoryEntity category : categories) {
      CategoryResponseDto dto = new CategoryResponseDto();
      dto.setId(category.getId());
      dto.setName(category.getName());
      dto.setDescription(category.getDescription());
      dto.setSlug(category.getSlug());
      dto.setDeleted(category.isDeleted());
      categoryResponseDtos.add(dto);
    }
    model.addAttribute("categories", categoryResponseDtos);
    // model.addAttribute("servletPath", request.getServletPath());
    return "admin/Category";
  }

}
