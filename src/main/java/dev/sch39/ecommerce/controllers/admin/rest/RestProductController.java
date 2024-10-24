package dev.sch39.ecommerce.controllers.admin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;
import dev.sch39.ecommerce.services.rest.RestProductService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

@CrossOrigin("*")
@RestController("AdminRestProductController")
@RequestMapping("/api/admin/products")
public class RestProductController {
  @Autowired
  RestProductService restProductService;

  @PostMapping({ "", "/" })
  public ResponseEntity<ApiResponse> createProduct(@RequestBody RestProductAdminRequestDto requestdto) {
    try {
      RestProductAdminResponseDto responseDto = restProductService.createProductForAdmin(requestdto);

      SuccessApiResponse<RestProductAdminResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse
          .setMessage("Created product successfully");
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
  public ResponseEntity<ApiResponse> getProducts(RestProductAdminFilterRequestDto filterRequestDto,
      RestPaginationRequestDto paginationRequestDto) {
    try {
      Page<RestProductAdminResponseDto> responseDtos = restProductService.getProductsForAdminByFilter(filterRequestDto,
          paginationRequestDto);

      SuccessApiResponse<Page<RestProductAdminResponseDto>> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Success get all product");
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
  public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
    try {
      RestProductAdminResponseDto responseDto = restProductService.getProductByIdForAdmin(id);

      SuccessApiResponse<RestProductAdminResponseDto> apiResponse = new SuccessApiResponse<>();
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
  public ResponseEntity<ApiResponse> updateProductById(@PathVariable("id") Long id,
      @RequestBody RestProductAdminRequestDto requestdto) {
    try {
      RestProductAdminResponseDto responseDto = restProductService.updateProductByIdForAdmin(id, requestdto);

      SuccessApiResponse<RestProductAdminResponseDto> apiResponse = new SuccessApiResponse<>();
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
  public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("id") Long id) {
    try {
      restProductService.deleteProductByIdForAdmin(id);

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
