package dev.sch39.ecommerce.dtos.rest.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RestProductResponseDto {
  private Long id;
  private String name;
  private String slug;
  private RestCategorySummaryResponseDto category;
  private Long categoryId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
