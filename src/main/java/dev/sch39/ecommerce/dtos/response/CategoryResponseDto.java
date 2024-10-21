package dev.sch39.ecommerce.dtos.response;

import lombok.Data;

@Data
public class CategoryResponseDto {
  private Long id;
  private String name;
  private String description;
  private String slug;
  private boolean deleted;
}
