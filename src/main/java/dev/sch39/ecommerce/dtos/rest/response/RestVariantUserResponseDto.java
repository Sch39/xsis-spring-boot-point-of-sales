package dev.sch39.ecommerce.dtos.rest.response;

import dev.sch39.ecommerce.entities.VariantEntity;
import lombok.Data;

@Data
public class RestVariantUserResponseDto {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private Double price;
  private Double stock;
  private Long productId;

  private RestProductSummaryResponseDto product;
  private RestCategorySummaryResponseDto category;

  public RestVariantUserResponseDto(VariantEntity variantEntity) {
    this.id = variantEntity.getId();
    this.name = variantEntity.getName();
    this.slug = variantEntity.getSlug();
    this.description = variantEntity.getDescription();
    this.price = variantEntity.getPrice();
    this.stock = variantEntity.getStock();
    this.productId = variantEntity.getProductId();

    this.product = new RestProductSummaryResponseDto(variantEntity.getProduct());
    this.category = new RestCategorySummaryResponseDto(variantEntity.getProduct().getCategory());
  }
}
