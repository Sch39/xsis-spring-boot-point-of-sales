package dev.sch39.ecommerce.database.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import dev.sch39.ecommerce.database.factory.Factory;
import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.repositories.ProductRepository;

@Component
public class ProductFactoryImpl implements Factory {
  @Autowired
  ProductRepository productRepository;

  Faker faker = new Faker();

  @Override
  public void run() {
    List<ProductEntity> productList = new ArrayList<>();
    for (Long i = 1L; i <= 5; i++) {
      for (int j = 1; j <= 3; j++) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(faker.commerce().productName());
        productEntity.setSlug(faker.internet().slug());
        productEntity.setDeleted(faker.bool().bool());
        productEntity.setCategoryId(i);
        productList.add(productEntity);
      }
    }

    productRepository.saveAll(productList);
  }

}
