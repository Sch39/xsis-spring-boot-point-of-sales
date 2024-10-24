package dev.sch39.ecommerce.controllers.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantAdminResponseDto;
import dev.sch39.ecommerce.services.rest.RestVariantService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin("*")
@RestController("AdminRestVariantController")
@RequestMapping("api/admin/variants")
public class RestVariantController {
  @Autowired
  RestVariantService restVariantService;

  @PostMapping({ "", "/" })
  public ResponseEntity<ApiResponse> postMethodName(@RequestBody RestVariantAdminRequestDto requestDto) {
    try {
      RestVariantAdminResponseDto responseDto = restVariantService.createVariantForAdmin(requestDto);

      SuccessApiResponse<RestVariantAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Created variant successfully");
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
  public ResponseEntity<ApiResponse> getVariants(RestVariantAdminFilterRequestDto filterRequestDto,
      RestPaginationRequestDto paginationRequestDto) {
    try {
      Page<RestVariantAdminResponseDto> responseDtos = restVariantService.getVariantsForAdminByFilter(filterRequestDto,
          paginationRequestDto);

      SuccessApiResponse<Page<RestVariantAdminResponseDto>> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Succes get all product");
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
  public ResponseEntity<ApiResponse> getVariantById(@PathVariable("id") Long id) {
    try {
      RestVariantAdminResponseDto responseDto = restVariantService.getVariantByIdForAdmin(id);

      SuccessApiResponse<RestVariantAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get product");
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
  public ResponseEntity<ApiResponse> updateVariantById(@PathVariable("id") Long id,
      @RequestBody RestVariantAdminRequestDto requestDto) {
    try {
      RestVariantAdminResponseDto responseDto = restVariantService.updateVariantByIdForAdmin(id, requestDto);

      SuccessApiResponse<RestVariantAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Updated product successfully");
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
  public ResponseEntity<ApiResponse> deleteVariantById(@PathVariable("id") Long id) {
    try {
      restVariantService.deleteVariantByIdForAdmin(id);

      ApiResponse apiResponse = new ApiResponse();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Product deleted successfully");

      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());

      return new ResponseEntity<>(errorApiResponse,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
