package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.CategoryEntity;
import lombok.Data;

@Data
public class RestCategorySummaryResponseDto {
  private Long id;
  private String name;
  private String slug;

  public RestCategorySummaryResponseDto(CategoryEntity categoryEntity) {
    this.id = categoryEntity.getId();
    this.name = categoryEntity.getName();
    this.slug = categoryEntity.getSlug();
  }
}
