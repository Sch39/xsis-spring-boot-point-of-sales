package dev.sch39.ecommerce.dtos.rest.response;

import java.time.LocalDateTime;

import dev.sch39.ecommerce.entities.VariantEntity;
import lombok.Data;

@Data
public class RestVariantAdminResponseDto {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private Double price;
  private Double stock;
  private Long productId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean deleted;

  public RestVariantAdminResponseDto(VariantEntity variantEntity) {
    this.id = variantEntity.getId();
    this.name = variantEntity.getName();
    this.slug = variantEntity.getSlug();
    this.description = variantEntity.getDescription();
    this.price = variantEntity.getPrice();
    this.stock = variantEntity.getStock();
    this.productId = variantEntity.getProductId();
    this.createdAt = variantEntity.getCreatedAt();
    this.updatedAt = variantEntity.getUpdatedAt();
    this.deleted = variantEntity.isDeleted();
  }
}
