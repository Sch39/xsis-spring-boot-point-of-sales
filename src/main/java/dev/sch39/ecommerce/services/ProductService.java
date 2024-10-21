package dev.sch39.ecommerce.services;

import java.util.List;

import dev.sch39.ecommerce.entities.ProductEntity;

public interface ProductService {
  List<ProductEntity> getAllProduct(boolean isDeleted);

  List<ProductEntity> getAllProduct();

  ProductEntity save(ProductEntity product);

  ProductEntity findById(Long id);

  void deleteById(Long id);
}
