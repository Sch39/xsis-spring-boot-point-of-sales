package dev.sch39.ecommerce.services;

import java.util.List;

import dev.sch39.ecommerce.entities.ProductEntity;

public interface ProductService {
  List<ProductEntity> getAllProduct(boolean isDeleted);

  List<ProductEntity> getAllProduct();

  ProductEntity save(ProductEntity product);

  ProductEntity findById(Long id);

  ProductEntity getProductBySlug(String slug);

  void deleteById(Long id);
}
