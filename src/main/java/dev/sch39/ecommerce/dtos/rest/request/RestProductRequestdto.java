package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestProductRequestdto {
  private String name;
  private String slug;
  private Long categoryId;
  private boolean deleted;
}
