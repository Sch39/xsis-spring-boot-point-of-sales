package dev.sch39.ecommerce.controllers.admin.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.services.rest.RestCategoryService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

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
  RestCategoryService restCategoryService;

  @PostMapping({ "", "/" })
  public ResponseEntity<ApiResponse> createCategory(@RequestBody RestCategoryRequestDto requestDto) {
    try {
      RestCategoryAdminResponseDto responseDto = restCategoryService.createCategoryForAdmin(requestDto);
      SuccessApiResponse<RestCategoryAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Created category successfully");
      apiResponse.setData(responseDto);
      return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());

      return new ResponseEntity<>(errorApiResponse,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping({ "", "/" })
  public ResponseEntity<ApiResponse> getAllCategories(RestCategoryAdminFilterRequestDto queryDto) {
    try {
      List<RestCategoryAdminResponseDto> responseDtos = restCategoryService
          .getCategoriesForAdminByFilter(queryDto);

      SuccessApiResponse<List<RestCategoryAdminResponseDto>> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get all category");
      apiResponse.setData(responseDtos);

      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());
      return new ResponseEntity<>(errorApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping({ "{id}", "{id}/" })
  public ResponseEntity<ApiResponse> getCategoryById(@PathVariable("id") Long id) {
    try {
      RestCategoryAdminResponseDto responseDto = restCategoryService.getCategoryByIdForAdmin(id);

      SuccessApiResponse<RestCategoryAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get category");
      apiResponse.setData(responseDto);

      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());
      return new ResponseEntity<>(errorApiResponse,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping({ "{id}", "{id}/" })
  public ResponseEntity<ApiResponse> updateCategoryById(@PathVariable("id") Long id,
      @RequestBody RestCategoryRequestDto requestDto) {

    try {
      RestCategoryAdminResponseDto responseDto = restCategoryService.updateCategoryByIdForAdmin(id, requestDto);
      SuccessApiResponse<RestCategoryAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Updated category successfully");
      apiResponse.setData(responseDto);

      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());

      return new ResponseEntity<>(errorApiResponse,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping({ "{id}", "{id}/" })
  public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Long id) {

    try {
      restCategoryService.deleteCategoryByIdForAdmin(id);
      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Category deleted successfully");

      return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());

      return new ResponseEntity<>(errorApiResponse,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
