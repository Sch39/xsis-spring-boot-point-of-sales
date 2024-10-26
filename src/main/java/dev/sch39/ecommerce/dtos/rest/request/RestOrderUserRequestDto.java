package dev.sch39.ecommerce.dtos.rest.request;

import java.util.List;

import lombok.Data;

@Data
public class RestOrderUserRequestDto {
  private Double payMoney = 0D;
  private List<OrderItemUserDto> orders;
}
