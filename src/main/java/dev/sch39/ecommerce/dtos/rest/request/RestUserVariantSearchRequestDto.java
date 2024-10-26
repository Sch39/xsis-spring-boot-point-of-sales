package dev.sch39.ecommerce.dtos.rest.request;

import lombok.Data;

@Data
public class RestUserVariantSearchRequestDto {
  private String variantName;
  private String productName;
  private String categoryName;
}
