package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;

public interface RestProductService {
  RestProductAdminResponseDto createProductForAdmin(RestProductRequestDto requestdto);

  List<RestProductAdminResponseDto> getProductsForAdmin();

  List<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest);

  RestProductAdminResponseDto getProductByIdForAdmin(Long id);

  RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductRequestDto requestDto);

  void deleteProductByIdForAdmin(Long id);
}
