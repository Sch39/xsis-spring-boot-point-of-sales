package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.UserEntity;
import lombok.Data;

@Data
public class RestUserDetailsResponseDto {
  private Long id;
  private String username;
  private String firstname;
  private String lastname;

  public RestUserDetailsResponseDto(UserEntity userEntity) {
    this.id = userEntity.getId();
    this.username = userEntity.getUsername();
    this.firstname = userEntity.getFirstname();
    this.lastname = userEntity.getLastname();
  }
}
