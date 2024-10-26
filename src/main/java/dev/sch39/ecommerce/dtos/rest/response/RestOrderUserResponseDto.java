package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.OrderHeaderEntity;
import lombok.Data;

@Data
public class RestOrderUserResponseDto {
  private String reference;

  public RestOrderUserResponseDto(OrderHeaderEntity headerEntity) {
    this.reference = headerEntity.getReference();
  }
}
