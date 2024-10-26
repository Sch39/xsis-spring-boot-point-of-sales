package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.ProductEntity;
import lombok.Data;

@Data
public class RestProductSummaryResponseDto {
  private Long id;
  private String name;
  private String slug;
  private Long categoryId;

  public RestProductSummaryResponseDto(ProductEntity productEntity) {
    this.id = productEntity.getId();
    this.name = productEntity.getName();
    this.slug = productEntity.getSlug();
    this.categoryId = productEntity.getCategoryId();
  }
}
