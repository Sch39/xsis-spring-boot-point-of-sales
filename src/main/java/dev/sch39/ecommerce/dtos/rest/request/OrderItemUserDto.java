package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class OrderItemUserDto {
  private Long variantId;
  private Double quantity;
}
