package dev.sch39.ecommerce.services;

import java.util.List;

import dev.sch39.ecommerce.entities.CategoryEntity;

public interface CategoryService {
  List<CategoryEntity> getAllCategory(boolean isDeleted);

  List<CategoryEntity> getAllCategory();

  List<CategoryEntity> searchByText(String search);

  CategoryEntity save(CategoryEntity category);

  CategoryEntity findById(Long id);

  CategoryEntity getCategoryBySlug(String slug);

  void deleteCategoryBySlug(String slug);

  void deleteById(Long id);
}
