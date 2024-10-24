package dev.sch39.ecommerce.controllers.user.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.response.RestProductSummaryResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantAdminResponseDto;
import dev.sch39.ecommerce.entities.VariantEntity;
import dev.sch39.ecommerce.services.VariantService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;

@RestController("RestVariantController")
@RequestMapping("/api/variants")
public class RestVariantController {
  @Autowired
  VariantService variantService;

  // @GetMapping({ "", "/" })
  // public ResponseEntity<ApiResponse<?>> getAll() {
  // ApiResponse<List<RestVariantResponseDto>> apiResponse = new ApiResponse<>();

  // try {
  // List<VariantEntity> variants = variantService.getAllVariant(false);
  // List<RestVariantResponseDto> dtos = new ArrayList<>();

  // for (VariantEntity variant : variants) {
  // RestVariantResponseDto variantDto = new RestVariantResponseDto();
  // variantDto.setId(variant.getId());
  // variantDto.setName(variant.getName());
  // variantDto.setSlug(variant.getSlug());
  // variantDto.setDescription(variant.getDescription());
  // variantDto.setPrice(variant.getPrice());
  // variantDto.setStock(variant.getStock());
  // variantDto.setProductId(variant.getProductId());

  // RestProductSummaryResponseDto productSummaryResponseDto = new
  // RestProductSummaryResponseDto();
  // productSummaryResponseDto.setId(variant.getProduct().getId());
  // productSummaryResponseDto.setName(variant.getProduct().getName());
  // productSummaryResponseDto.setSlug(variant.getProduct().getSlug());
  // productSummaryResponseDto.setCategoryId(variant.getProduct().getCategoryId());

  // variantDto.setProduct(productSummaryResponseDto);
  // variantDto.setCreatedAt(variant.getCreatedAt());
  // variantDto.setUpdatedAt(variant.getUpdatedAt());
  // dtos.add(variantDto);
  // }

  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Success get all variant");
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
  // ApiResponse<RestVariantResponseDto> apiResponse = new ApiResponse<>();
  // try {
  // VariantEntity variant = variantService.getVariantBySlug(slug);
  // RestVariantResponseDto variantResponseDto = new RestVariantResponseDto();

  // variantResponseDto.setId(variant.getId());
  // variantResponseDto.setName(variant.getName());
  // variantResponseDto.setSlug(variant.getSlug());
  // variantResponseDto.setDescription(variant.getDescription());
  // variantResponseDto.setStock(variant.getStock());
  // variantResponseDto.setPrice(variant.getPrice());
  // variantResponseDto.setProductId(variant.getProductId());

  // RestProductSummaryResponseDto productSummaryResponseDto = new
  // RestProductSummaryResponseDto();
  // productSummaryResponseDto.setId(variant.getProduct().getId());
  // productSummaryResponseDto.setName(variant.getProduct().getName());
  // productSummaryResponseDto.setSlug(variant.getProduct().getSlug());
  // productSummaryResponseDto.setCategoryId(variant.getProduct().getCategoryId());

  // variantResponseDto.setProduct(productSummaryResponseDto);

  // variantResponseDto.setCreatedAt(variant.getCreatedAt());
  // variantResponseDto.setUpdatedAt(variant.getUpdatedAt());
  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Success get variant");
  // apiResponse.setData(variantResponseDto);

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
