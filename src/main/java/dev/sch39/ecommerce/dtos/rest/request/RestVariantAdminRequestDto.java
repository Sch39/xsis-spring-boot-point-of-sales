package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestVariantAdminRequestDto {
  private String name;
  private String slug;
  private String description;
  private Double price;
  private Double stock;
  private Long productId;
  private boolean deleted;
}
