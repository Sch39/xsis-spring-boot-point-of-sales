package dev.sch39.ecommerce.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.sch39.ecommerce.dtos.request.CreateCategoryRequestDto;
import dev.sch39.ecommerce.dtos.response.CategoryResponseDto;
import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.services.CategoryService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin/product-management/category")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @GetMapping({ "", "/" }) // or use configuration with implement then override configurePathMatch
  public String getCategories(Model model, @RequestParam(required = false, defaultValue = "all") List<String> filter,
      CategoryEntity categoryEntity) {
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
    return "admin/category/index";
  }

  @PostMapping("/create")
  public String createCategory(@Valid CreateCategoryRequestDto categoryDto, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      CategoryEntity category = new CategoryEntity();
      category.setName(categoryDto.getName());
      category.setSlug(categoryDto.getSlug());
      category.setDescription(categoryDto.getDescription());
      category.setDeleted(categoryDto.isDeleted());
      categoryService.save(category);
    }
    return "redirect:/admin/product-management/category";
  }

  @PostMapping("/update/{id}")
  public String updateCategory(@PathVariable("id") Long id, CategoryEntity categoryEntity, BindingResult result) {
    if (!result.hasErrors()) {
      categoryService.save(categoryEntity);
    }
    return "redirect:/admin/product-management/category";
  }

  @GetMapping("/delete/{id}")
  public String deleteCategory(@PathVariable("id") Long id) {
    categoryService.deleteById(id);
    return "redirect:/admin/product-management/category";
  }
}
