package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestPaginationRequestDto {
  private int page = 0;
  private int size = 10;
}
