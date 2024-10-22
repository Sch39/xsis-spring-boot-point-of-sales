package dev.sch39.ecommerce.services.impl;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.entities.VariantEntity;
import dev.sch39.ecommerce.repositories.VariantRepository;
import dev.sch39.ecommerce.services.VariantService;
import jakarta.persistence.EntityManager;

@Service
public class VariantServiceImpl implements VariantService {
  @Autowired
  private VariantRepository variantRepository;
  @Autowired
  private EntityManager entityManager;

  @Override
  public List<VariantEntity> getAllVariant(boolean isDeleted) {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deleteVariantFilter");
    filter.setParameter("isDeleted", isDeleted);
    List<VariantEntity> variants = variantRepository.findAll();
    session.disableFilter("deleteVariantFilter");

    return variants;
  }

  @Override
  public List<VariantEntity> getAllVariant() {
    return variantRepository.findAll();
  }

  @Override
  public VariantEntity save(VariantEntity variant) {
    return variantRepository.save(variant);
  }

  @Override
  public VariantEntity findById(Long id) {
    return variantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Variant Id: " + id + " not found"));
  }

  @Override
  public void deleteById(Long id) {
    VariantEntity variantEntity = variantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Variant Id: " + id + " not found"));
    variantRepository.delete(variantEntity);
  }

  @Override
  public VariantEntity getVariantBySlug(String slug) {
    return variantRepository.findBySlug(slug)
        .orElseThrow(() -> new IllegalArgumentException("Variant Slug: " + slug + " not found"));
  }
}
