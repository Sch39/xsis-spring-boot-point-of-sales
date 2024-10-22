package dev.sch39.ecommerce.dtos.rest.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RestVariantResponseDto {
  private Long id;
  private String name;
  private String slug;
  private String description;
  private Double price;
  private Double stock;
  private RestProductSummaryResponseDto product;
  private Long productId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
