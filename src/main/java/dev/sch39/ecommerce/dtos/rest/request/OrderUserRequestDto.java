package dev.sch39.ecommerce.dtos.rest.request;

import java.util.List;

import lombok.Data;

@Data
public class OrderUserRequestDto {
  private List<OrderItemUserDto> orders;
}
