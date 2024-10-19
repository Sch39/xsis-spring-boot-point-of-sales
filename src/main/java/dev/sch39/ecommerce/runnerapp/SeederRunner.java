package dev.sch39.ecommerce.runnerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import dev.sch39.ecommerce.database.DatabaseSeeder;

@Component
public class SeederRunner implements ApplicationRunner {
  @Autowired
  private DatabaseSeeder databaseSeeder;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (args.containsOption("seed")) {
      databaseSeeder.seed();
    }
  }

}
