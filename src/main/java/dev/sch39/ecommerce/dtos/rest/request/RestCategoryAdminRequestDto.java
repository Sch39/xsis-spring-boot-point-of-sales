package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestCategoryAdminRequestDto {
  private String name;
  private String description;
  private String slug;
  private boolean deleted;

}
