package dev.sch39.ecommerce.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.sch39.ecommerce.database.factory.impl.CategoryFactoryImpl;
import dev.sch39.ecommerce.database.factory.impl.ProductFactoryImpl;
import dev.sch39.ecommerce.database.factory.impl.UserFactoryImpl;
import dev.sch39.ecommerce.database.factory.impl.VariantFactoryImpl;
import dev.sch39.ecommerce.database.factory.impl.WalletFactoryImpl;

@Component
public class DatabaseSeeder {
  @Autowired
  private CategoryFactoryImpl categoryFactoryImpl;
  @Autowired
  private ProductFactoryImpl productFactoryImpl;
  @Autowired
  private VariantFactoryImpl variantFactoryImpl;
  @Autowired
  private UserFactoryImpl userFactoryImpl;
  @Autowired
  private WalletFactoryImpl walletFactoryImpl;

  public void seed() {
    this.categoryFactoryImpl.run();
    this.productFactoryImpl.run();
    this.variantFactoryImpl.run();

    this.userFactoryImpl.run();
    this.walletFactoryImpl.run();
  }
}
