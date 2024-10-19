package dev.sch39.ecommerce.database.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import dev.sch39.ecommerce.database.factory.Factory;
import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.repositories.CategoryRepository;

@Component
public class CategoryFactoryImpl implements Factory {
  @Autowired
  private CategoryRepository categoryRepository;
  Faker faker = new Faker();

  @Override
  public void run() {
    List<CategoryEntity> categoryList = new ArrayList<>();

    for (int i = 1; i <= 5; i++) {
      CategoryEntity categoryEntity = new CategoryEntity();
      categoryEntity.setName(this.faker.commerce().department());
      categoryEntity.setSlug(this.faker.internet().slug().toLowerCase());
      categoryEntity.setDescription(this.faker.lorem().paragraph(5));
      categoryEntity.setDeleted(this.faker.bool().bool());
      categoryList.add(categoryEntity);
    }

    this.categoryRepository.saveAll(categoryList);
  }

}
