package dev.sch39.ecommerce.services.rest;

import org.springframework.data.domain.Page;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;

public interface RestCategoryService {
  RestCategoryAdminResponseDto createCategoryForAdmin(RestCategoryAdminRequestDto requestDto);

  Page<RestCategoryAdminResponseDto> getCategoriesForAdminByFilter(RestCategoryAdminFilterRequestDto queryDto,
      RestPaginationRequestDto paginationRequestDto);

  RestCategoryAdminResponseDto getCategoryByIdForAdmin(Long id);

  RestCategoryAdminResponseDto updateCategoryByIdForAdmin(Long id, RestCategoryAdminRequestDto requestDto);

  void deleteCategoryByIdForAdmin(Long id);
}
