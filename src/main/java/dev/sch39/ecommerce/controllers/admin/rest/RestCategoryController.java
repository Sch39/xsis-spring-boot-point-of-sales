package dev.sch39.ecommerce.controllers.admin.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryUserResponseDto;
import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.services.rest.RestCategoryService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/categories")
public class RestCategoryController {
  @Autowired
  RestCategoryService categoryService;

  @GetMapping({ "", "/" })
  public ResponseEntity<ApiResponse> getAllCategory(RestCategoryAdminFilterRequestDto queryDto) {
    try {
      List<RestCategoryAdminResponseDto> dtos = categoryService.getAllCategoriesForAdminByQueryParam(queryDto);

      SuccessApiResponse<List<RestCategoryAdminResponseDto>> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get all category");
      apiResponse.setData(dtos);

      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());
      return new ResponseEntity<>(errorApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping({ "{slug}", "{slug}/" })
  // public ResponseEntity<ApiResponse<?>> getCategoryBySlug(@PathVariable("slug")
  // String slug) {
  // ApiResponse<RestCategoryUserResponseDto> apiResponse = new ApiResponse<>();
  // try {
  // CategoryEntity category = categoryService.getCategoryBySlug(slug);
  // RestCategoryUserResponseDto dto = new RestCategoryUserResponseDto();
  // if (category == null) {
  // throw new Exception("Data not found for slug: " + slug);
  // }
  // dto.setId(category.getId());
  // dto.setName(category.getName());
  // dto.setDescription(category.getDescription());
  // dto.setSlug(category.getSlug());
  // dto.setCreatedAt(category.getCreatedAt());
  // dto.setUpdatedAt(category.getUpdatedAt());

  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Success get category");
  // apiResponse.setData(dto);

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

  // @DeleteMapping({ "{slug}", "{slug}/" })
  // public ResponseEntity<ApiResponse<?>>
  // deleteCategoryBySlug(@PathVariable("slug") String slug) {
  // ApiResponse<?> apiResponse = new ApiResponse<>();

  // try {
  // categoryService.deleteCategoryBySlug(slug);
  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Category deleted successfully");

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

  // @PostMapping({ "", "/" })
  // public ResponseEntity<ApiResponse<?>> createCategory(@RequestBody
  // RestCategoryUserResponseDto categoryResponseDto) {
  // ApiResponse<CategoryEntity> apiResponse = new ApiResponse<>();

  // try {
  // CategoryEntity categoryEntity = new CategoryEntity();
  // categoryEntity.setName(categoryResponseDto.getName());
  // categoryEntity.setDescription(categoryResponseDto.getDescription());
  // categoryEntity.setSlug(categoryResponseDto.getSlug());
  // categoryEntity.setDeleted(false);

  // categoryService.save(categoryEntity);
  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Created category successfully");
  // apiResponse.setData(categoryEntity);
  // return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  // } catch (Exception e) {
  // ErrorApiResponse<?> errorApiResponse = new ErrorApiResponse<>();
  // errorApiResponse.setSuccess(false);
  // errorApiResponse.setMessage(e.getMessage());
  // errorApiResponse.setErrorCode(e.hashCode());

  // return new ResponseEntity<>(errorApiResponse,
  // HttpStatus.INTERNAL_SERVER_ERROR);
  // }

  // }

  // @PutMapping({ "{slug}", "{slug}/" })
  // public ResponseEntity<ApiResponse<?>>
  // updateCategoryBySlug(@PathVariable("slug") String slug,
  // @RequestBody RestCategoryUserResponseDto categoryResponseDto) {
  // ApiResponse<CategoryEntity> apiResponse = new ApiResponse<>();

  // try {
  // CategoryEntity categoryEntity = categoryService.getCategoryBySlug(slug);
  // categoryEntity.setName(categoryResponseDto.getName());
  // categoryEntity.setDescription(categoryResponseDto.getDescription());
  // categoryEntity.setSlug(categoryResponseDto.getSlug());

  // categoryService.save(categoryEntity);
  // apiResponse.setSuccess(true);
  // apiResponse.setMessage("Updated category successfully");
  // apiResponse.setData(categoryEntity);

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
