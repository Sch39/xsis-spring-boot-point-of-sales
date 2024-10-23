package dev.sch39.ecommerce.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessApiResponse<T> extends ApiResponse {
  private T data;
}
