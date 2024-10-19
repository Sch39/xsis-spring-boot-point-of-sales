package dev.sch39.ecommerce.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.sch39.ecommerce.database.factory.impl.CategoryFactoryImpl;

@Component
public class DatabaseSeeder {
  @Autowired
  private CategoryFactoryImpl categoryFactoryImpl;

  public void seed() {
    this.categoryFactoryImpl.run();
  }
}
