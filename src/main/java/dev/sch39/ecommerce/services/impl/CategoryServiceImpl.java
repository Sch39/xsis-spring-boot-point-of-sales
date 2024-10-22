package dev.sch39.ecommerce.services.impl;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.repositories.CategoryRepository;
import dev.sch39.ecommerce.services.CategoryService;
import jakarta.persistence.EntityManager;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private EntityManager entityManager;

  @Override
  public List<CategoryEntity> getAllCategory(boolean isDeleted) {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deleteCategoryFilter");
    filter.setParameter("isDeleted", isDeleted);
    List<CategoryEntity> categories = categoryRepository.findAll();
    session.disableFilter("deleteCategoryFilter");

    return categories;
  }

  @Override
  public List<CategoryEntity> getAllCategory() {
    return categoryRepository.findAll();
  }

  @Override
  public List<CategoryEntity> searchByText(String search) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchByText'");
  }

  @Override
  public CategoryEntity save(CategoryEntity category) {
    return categoryRepository.save(category);
  }

  @Override
  public CategoryEntity findById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
  }

  @Override
  public void deleteById(Long id) {
    CategoryEntity categoryEntity = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
    categoryRepository.delete(categoryEntity);
  }

  @Override
  public CategoryEntity getCategoryBySlug(String slug) {
    return categoryRepository.findBySlug(slug)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category slug: " + slug));
  }

  @Override
  public void deleteCategoryBySlug(String slug) {
    CategoryEntity categoryEntity = categoryRepository.findBySlug(slug)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category slug: " + slug));

    categoryRepository.delete(categoryEntity);
  }

}
