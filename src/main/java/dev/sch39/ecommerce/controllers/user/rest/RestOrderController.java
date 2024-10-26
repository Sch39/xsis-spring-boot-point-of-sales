package dev.sch39.ecommerce.controllers.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestOrderUserRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestOrderUserResponseDto;
import dev.sch39.ecommerce.services.rest.RestOrderService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class RestOrderController {
  @Autowired
  private RestOrderService restOrderService;

  @PostMapping({ "", "/" })
  public ResponseEntity<ApiResponse> processOrder(@RequestBody RestOrderUserRequestDto requestDto) {
    try {
      RestOrderUserResponseDto responseDto = restOrderService.createOrder(requestDto);

      SuccessApiResponse<RestOrderUserResponseDto> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Transaction sucessfully!");
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
}
