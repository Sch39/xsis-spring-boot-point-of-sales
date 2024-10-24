package dev.sch39.ecommerce.services.rest;

import org.springframework.data.domain.Page;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;

public interface RestProductService {
  RestProductAdminResponseDto createProductForAdmin(RestProductAdminRequestDto requestdto);

  Page<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest,
      RestPaginationRequestDto paginationRequestDto);

  RestProductAdminResponseDto getProductByIdForAdmin(Long id);

  RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductAdminRequestDto requestDto);

  void deleteProductByIdForAdmin(Long id);
}
