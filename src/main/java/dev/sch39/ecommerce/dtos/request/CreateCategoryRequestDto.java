package dev.sch39.ecommerce.dtos.request;

import dev.sch39.ecommerce.validation.annotation.ValidSlug;
import lombok.Data;

@Data
public class CreateCategoryRequestDto {

  private String name;

  private String description;

  @ValidSlug
  private String slug;

  private boolean deleted;
}
