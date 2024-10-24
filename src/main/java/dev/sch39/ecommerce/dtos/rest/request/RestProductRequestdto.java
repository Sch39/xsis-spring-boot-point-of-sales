package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestProductRequestDto {
  private String name;
  private String slug;
  private Long categoryId;
  private boolean deleted;
}
