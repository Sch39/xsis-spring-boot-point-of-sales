package dev.sch39.ecommerce.database.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import dev.sch39.ecommerce.database.factory.Factory;
import dev.sch39.ecommerce.entities.VariantEntity;
import dev.sch39.ecommerce.repositories.VariantRepository;

@Component
public class VariantFactoryImpl implements Factory {
  @Autowired
  VariantRepository variantRepository;
  Faker faker = new Faker();

  @Override
  public void run() {
    List<VariantEntity> variantList = new ArrayList<>();

    for (Long i = 1L; i <= 15; i++) {
      for (int j = 1; j <= 4; j++) {
        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setName(faker.pokemon().name());
        variantEntity.setDescription(faker.lorem().paragraph(10));
        variantEntity.setSlug(faker.internet().slug());
        variantEntity.setPrice(Double.valueOf(faker.commerce().price(100_000, 300_000)));
        variantEntity.setStock(Double.valueOf(faker.number().numberBetween(2, 30)));
        variantEntity.setProductId(i);
        variantEntity.setDeleted(i == j ? false : faker.bool().bool());
        variantList.add(variantEntity);
      }
    }

    variantRepository.saveAll(variantList);
  }

}
