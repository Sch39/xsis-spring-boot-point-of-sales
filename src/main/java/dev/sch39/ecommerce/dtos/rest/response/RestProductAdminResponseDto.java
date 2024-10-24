package dev.sch39.ecommerce.dtos.rest.response;

import java.time.LocalDateTime;

import dev.sch39.ecommerce.entities.ProductEntity;
import lombok.Data;

@Data
public class RestProductAdminResponseDto {
  private Long id;
  private String name;
  private String slug;
  private Long categoryId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean deleted;

  public RestProductAdminResponseDto(ProductEntity productEntity) {
    this.id = productEntity.getId();
    this.name = productEntity.getName();
    this.slug = productEntity.getSlug();
    this.categoryId = productEntity.getCategoryId();
    this.createdAt = productEntity.getCreatedAt();
    this.updatedAt = productEntity.getUpdatedAt();
    this.deleted = productEntity.isDeleted();
  }
}
