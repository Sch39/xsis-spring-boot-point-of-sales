package dev.sch39.ecommerce.dtos.rest.response;

import lombok.Data;

@Data
public class RestCategorySummaryResponseDto {
  private Long id;
  private String name;
  private String slug;
}
