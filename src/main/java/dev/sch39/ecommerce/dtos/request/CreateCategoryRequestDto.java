package dev.sch39.ecommerce.dtos.request;

import dev.sch39.ecommerce.validation.annotation.ValidSlug;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {
  @NotBlank
  private String name;
  @NotBlank
  private String description;
  @NotBlank
  @ValidSlug
  private String slug;
  @NotBlank
  private boolean deleted;
}
