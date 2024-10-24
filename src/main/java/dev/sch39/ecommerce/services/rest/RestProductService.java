package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;

public interface RestProductService {
  RestProductAdminResponseDto createProductForAdmin(RestProductAdminRequestDto requestdto);

  List<RestProductAdminResponseDto> getProductsForAdmin();

  List<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest);

  RestProductAdminResponseDto getProductByIdForAdmin(Long id);

  RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductAdminRequestDto requestDto);

  void deleteProductByIdForAdmin(Long id);
}
