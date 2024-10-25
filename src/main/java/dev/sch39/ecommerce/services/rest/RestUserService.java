package dev.sch39.ecommerce.services.rest;

import dev.sch39.ecommerce.dtos.rest.response.RestUserDetailsResponseDto;

public interface RestUserService {
  String getAuthenticatedUsername();

  RestUserDetailsResponseDto getAuthenticatedUserDetails();
}
