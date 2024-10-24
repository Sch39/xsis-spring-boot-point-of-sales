package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantAdminResponseDto;

public interface RestVariantService {
  RestVariantAdminResponseDto createVariantForAdmin(RestVariantAdminRequestDto requestDto);

  List<RestVariantAdminResponseDto> getVariantsForAdminByFilter(RestVariantAdminFilterRequestDto filterRequestDto);

  RestVariantAdminResponseDto getVariantByIdForAdmin(Long id);

  RestVariantAdminResponseDto updateVariantByIdForAdmin(Long id, RestVariantAdminRequestDto requestDto);

  void deleteVariantByIdForAdmin(Long id);
}
