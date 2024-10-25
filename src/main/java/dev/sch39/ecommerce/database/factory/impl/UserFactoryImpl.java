package dev.sch39.ecommerce.database.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.sch39.ecommerce.database.factory.Factory;
import dev.sch39.ecommerce.entities.UserEntity;
import dev.sch39.ecommerce.repositories.UserRepository;

@Component
public class UserFactoryImpl implements Factory {
  @Autowired
  private UserRepository userRepository;

  @Override
  public void run() {
    UserEntity user1 = new UserEntity();
    user1.setUsername("goat");
    user1.setFirstname("Antonie");
    user1.setLastname("Santoz");

    userRepository.save(user1);

    UserEntity user2 = new UserEntity();
    user2.setUsername("gey");
    user2.setFirstname("Lionel");
    user2.setLastname("Gey Pessi");
    userRepository.save(user2);
  }

}
