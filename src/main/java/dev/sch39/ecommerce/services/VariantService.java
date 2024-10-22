package dev.sch39.ecommerce.services;

import java.util.List;

import dev.sch39.ecommerce.entities.VariantEntity;

public interface VariantService {
  List<VariantEntity> getAllVariant(boolean isDeleted);

  List<VariantEntity> getAllVariant();

  VariantEntity save(VariantEntity variant);

  VariantEntity findById(Long id);

  VariantEntity getVariantBySlug(String slug);

  void deleteById(Long id);
}
