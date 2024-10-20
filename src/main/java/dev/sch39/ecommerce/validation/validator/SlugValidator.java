package dev.sch39.ecommerce.validation.validator;

import dev.sch39.ecommerce.validation.annotation.ValidSlug;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SlugValidator implements ConstraintValidator<ValidSlug, String> {
  private final String SLUG_PATTERN = "^[a-z0-9]+(?:-[a-z0-9]+)*$";

  @Override
  public boolean isValid(String slug, ConstraintValidatorContext ctx) {
    if (slug == null || slug.isEmpty()) {
      return false;
    }

    return slug.matches(this.SLUG_PATTERN);
  }

}
