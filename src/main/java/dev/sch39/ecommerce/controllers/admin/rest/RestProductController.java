package dev.sch39.ecommerce.controllers.admin.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.response.RestCategorySummaryResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductResponseDto;
import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.services.ProductService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;

@RestController
@RequestMapping("/api/admin/products")
public class RestProductController {
  @Autowired
  ProductService productService;

  @GetMapping({ "", "/" })
  public ResponseEntity<ApiResponse<?>> getAll() {
    ApiResponse<List<RestProductResponseDto>> apiResponse = new ApiResponse<>();

    try {
      List<ProductEntity> products = productService.getAllProduct(false);
      List<RestProductResponseDto> dtos = new ArrayList<>();

      for (ProductEntity product : products) {
        RestProductResponseDto dto = new RestProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setSlug(product.getSlug());
        dto.setCategoryId(product.getCategoryId());

        RestCategorySummaryResponseDto categoryResponseDto = new RestCategorySummaryResponseDto();
        categoryResponseDto.setId(product.getCategory().getId());
        categoryResponseDto.setName(product.getCategory().getName());
        categoryResponseDto.setSlug(product.getCategory().getSlug());
        dto.setCategory(categoryResponseDto);

        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        dtos.add(dto);
      }
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get all category");
      apiResponse.setData(dtos);
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<?> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());
      errorApiResponse.setErrorCode(e.hashCode());
      return new ResponseEntity<>(errorApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
