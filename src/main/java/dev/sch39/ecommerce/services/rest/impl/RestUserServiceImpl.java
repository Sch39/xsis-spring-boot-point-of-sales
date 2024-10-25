package dev.sch39.ecommerce.services.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.response.RestUserDetailsResponseDto;
import dev.sch39.ecommerce.entities.UserEntity;
import dev.sch39.ecommerce.repositories.UserRepository;
import dev.sch39.ecommerce.services.rest.RestUserService;

@Service
public class RestUserServiceImpl implements RestUserService {
  @Autowired
  UserRepository userRepository;

  @Override
  public String getAuthenticatedUsername() {
    Long id = 1L;
    UserEntity user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product  id: " + id));
    return user.getUsername();
  }

  @Override
  public RestUserDetailsResponseDto getAuthenticatedUserDetails() {
    Long id = 1L;
    UserEntity user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product  id: " + id));
    return new RestUserDetailsResponseDto(user);
  }

}
