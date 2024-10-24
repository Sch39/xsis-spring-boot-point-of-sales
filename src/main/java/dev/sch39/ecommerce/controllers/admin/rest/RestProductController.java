package dev.sch39.ecommerce.controllers.admin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductRequestdto;
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
  public ResponseEntity<ApiResponse> createProduct(@RequestBody RestProductRequestdto requestdto) {
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
  public ResponseEntity<ApiResponse> getProducts(RestProductAdminFilterRequestDto filterRequestDto) {
    try {
      List<RestProductAdminResponseDto> responseDtos = restProductService.getProductsForAdminByFilter(filterRequestDto);

      SuccessApiResponse<List<RestProductAdminResponseDto>> apiResponse = new SuccessApiResponse<>();
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
}
