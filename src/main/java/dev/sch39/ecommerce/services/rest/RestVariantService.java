package dev.sch39.ecommerce.services.rest;

import org.springframework.data.domain.Page;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestUserVariantSearchRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantUserResponseDto;

public interface RestVariantService {
  // admin
  RestVariantAdminResponseDto createVariantForAdmin(RestVariantAdminRequestDto requestDto);

  Page<RestVariantAdminResponseDto> getVariantsForAdminByFilter(RestVariantAdminFilterRequestDto filterRequestDto,
      RestPaginationRequestDto paginationRequestDto);

  RestVariantAdminResponseDto getVariantByIdForAdmin(Long id);

  RestVariantAdminResponseDto updateVariantByIdForAdmin(Long id, RestVariantAdminRequestDto requestDto);

  void deleteVariantByIdForAdmin(Long id);

  // user
  Page<RestVariantUserResponseDto> searchVariants(RestUserVariantSearchRequestDto requestDto,
      RestPaginationRequestDto paginationRequestDto);
}
