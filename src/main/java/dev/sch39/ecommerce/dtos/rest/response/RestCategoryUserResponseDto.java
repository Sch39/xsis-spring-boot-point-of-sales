package dev.sch39.ecommerce.dtos.rest.response;

import java.time.LocalDateTime;

import dev.sch39.ecommerce.entities.CategoryEntity;
import lombok.Data;

@Data
public class RestCategoryUserResponseDto {
  private Long id;
  private String name;
  private String description;
  private String slug;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public RestCategoryUserResponseDto(CategoryEntity categoryEntity) {
    this.id = categoryEntity.getId();
    this.name = categoryEntity.getName();
    this.description = categoryEntity.getDescription();
    this.slug = categoryEntity.getSlug();
    this.createdAt = categoryEntity.getCreatedAt();
    this.updatedAt = categoryEntity.getUpdatedAt();
  }
}
