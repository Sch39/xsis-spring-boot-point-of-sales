package dev.sch39.ecommerce.controllers.user.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestUserVariantSearchRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantUserResponseDto;
import dev.sch39.ecommerce.services.rest.RestVariantService;
import dev.sch39.ecommerce.utils.ApiResponse;
import dev.sch39.ecommerce.utils.ErrorApiResponse;
import dev.sch39.ecommerce.utils.SuccessApiResponse;

import org.springframework.web.bind.annotation.GetMapping;

@RestController("RestVariantController")
@RequestMapping("/api/variants")
public class RestVariantController {
  @Autowired
  RestVariantService restVariantService;

  @GetMapping({ "/search", "/search/" })
  public ResponseEntity<ApiResponse> search(RestUserVariantSearchRequestDto requestDto,
      RestPaginationRequestDto paginationRequestDto) {
    try {
      Page<RestVariantUserResponseDto> searchResults = restVariantService.searchVariants(requestDto,
          paginationRequestDto);
      SuccessApiResponse<Page<RestVariantUserResponseDto>> apiResponse = new SuccessApiResponse<>();
      apiResponse.setSuccess(true);
      apiResponse.setMessage("Succes search variant");
      apiResponse.setData(searchResults);
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    } catch (Exception e) {
      ErrorApiResponse<Void> errorApiResponse = new ErrorApiResponse<>();
      errorApiResponse.setSuccess(false);
      errorApiResponse.setMessage(e.getMessage());
      return new ResponseEntity<>(errorApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
