package dev.sch39.ecommerce.controllers.user.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.response.RestCategorySummaryResponseDto;
import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.services.ProductService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController("RestProductController")
@RequestMapping("/api/products")
public class RestProductController {
  @Autowired
  ProductService productService;

  // @GetMapping({ "", "/" })
  // public ResponseEntity<ApiResponse<?>> getAll() {
  // ApiResponse<List<RestProductResponseDto>> apiResponse = new ApiResponse<>();

  // try {

  // List<ProductEntity> products = productService.getAllProduct(false);
  // List<RestProductResponseDto> dtos = new ArrayList<>();

  // for (ProductEntity product : products) {
  // RestProductResponseDto productDto = new RestProductResponseDto();
  // productDto.setId(product.getId());
  // productDto.setName(product.getName());
  // productDto.setSlug(product.getSlug());
  // productDto.setCategoryId(product.getCategoryId());

  // RestCategorySummaryResponseDto categorySummaryResponseDto = new
  // RestCategorySummaryResponseDto();
  // categorySummaryResponseDto.setId(product.getCategory().getId());
  // categorySummaryResponseDto.setName(product.getCategory().getName());
  // categorySummaryResponseDto.setSlug(product.getCategory().getSlug());

  // productDto.setCategory(categorySummaryResponseDto);
  // productDto.setCreatedAt(product.getCreatedAt());
  // productDto.setUpdatedAt(product.getUpdatedAt());
  // dtos.add(productDto);
  // }

  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Success get all product");
  // apiResponse.setData(dtos);
  // return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  // } catch (Exception e) {
  // ErrorApiResponse<?> errorApiResponse = new ErrorApiResponse<>();
  // errorApiResponse.setSuccess(false);
  // errorApiResponse.setMessage(e.getMessage());
  // errorApiResponse.setErrorCode(e.hashCode());
  // return new ResponseEntity<>(errorApiResponse,
  // HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

  // @GetMapping({ "{slug}", "{slug}/" })
  // public ResponseEntity<ApiResponse<?>> getBySlug(@PathVariable("slug") String
  // slug) {
  // ApiResponse<RestProductResponseDto> apiResponse = new ApiResponse<>();

  // try {
  // ProductEntity product = productService.getProductBySlug(slug);
  // RestProductResponseDto productDto = new RestProductResponseDto();
  // productDto.setId(product.getId());
  // productDto.setName(product.getName());
  // productDto.setSlug(product.getSlug());
  // productDto.setCategoryId(product.getCategoryId());

  // RestCategorySummaryResponseDto categorySummaryResponseDto = new
  // RestCategorySummaryResponseDto();
  // categorySummaryResponseDto.setId(product.getCategory().getId());
  // categorySummaryResponseDto.setName(product.getCategory().getName());
  // categorySummaryResponseDto.setSlug(product.getCategory().getSlug());

  // productDto.setCategory(categorySummaryResponseDto);
  // productDto.setCreatedAt(product.getCreatedAt());
  // productDto.setUpdatedAt(product.getUpdatedAt());

  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Success get product");
  // apiResponse.setData(productDto);

  // return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  // } catch (Exception e) {
  // ErrorApiResponse<?> errorApiResponse = new ErrorApiResponse<>();
  // errorApiResponse.setSuccess(false);
  // errorApiResponse.setMessage(e.getMessage());
  // errorApiResponse.setErrorCode(e.hashCode());
  // return new ResponseEntity<>(errorApiResponse,
  // HttpStatus.INTERNAL_SERVER_ERROR);
  // }
  // }

}
