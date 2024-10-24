package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductRequestdto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;

public interface RestProductService {
  RestProductAdminResponseDto createProductForAdmin(RestProductRequestdto requestdto);

  List<RestProductAdminResponseDto> getProductsForAdmin();

  List<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest);

  RestProductAdminResponseDto getProductByIdForAdmin(Long id);

  RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductRequestdto requestDto);

  void deleteProductByIdForAdmin(Long id);
}
