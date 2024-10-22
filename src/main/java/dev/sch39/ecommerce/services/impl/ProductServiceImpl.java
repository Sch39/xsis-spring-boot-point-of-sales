package dev.sch39.ecommerce.services.impl;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.repositories.ProductRepository;
import dev.sch39.ecommerce.services.ProductService;
import jakarta.persistence.EntityManager;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private EntityManager entityManager;

  @Override
  public List<ProductEntity> getAllProduct(boolean isDeleted) {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deleteProductFilter");
    filter.setParameter("isDeleted", isDeleted);
    List<ProductEntity> products = productRepository.findAll();
    session.disableFilter("deleteProductFilter");

    return products;
  }

  @Override
  public List<ProductEntity> getAllProduct() {
    return productRepository.findAll();
  }

  @Override
  public ProductEntity save(ProductEntity product) {
    return productRepository.save(product);
  }

  @Override
  public ProductEntity findById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product Id:" + id + " not found"));
  }

  @Override
  public void deleteById(Long id) {
    ProductEntity productEntity = productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Product Id:" + id + " not found"));
    productRepository.delete(productEntity);
  }

  @Override
  public ProductEntity getProductBySlug(String slug) {
    return productRepository.findBySlug(slug)
        .orElseThrow(() -> new IllegalArgumentException("Product Slug:" + slug + " not found"));
  }

}
