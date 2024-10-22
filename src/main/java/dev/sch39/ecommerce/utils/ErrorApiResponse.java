package dev.sch39.ecommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorApiResponse<T> extends ApiResponse<T> {
  private int errorCode;
}
