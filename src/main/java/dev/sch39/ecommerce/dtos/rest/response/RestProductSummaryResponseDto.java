package dev.sch39.ecommerce.dtos.rest.response;

import lombok.Data;

@Data
public class RestProductSummaryResponseDto {
  private Long id;
  private String name;
  private String slug;
  private Long categoryId;
}
