package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryUserResponseDto;

public interface RestCategoryService {
  RestCategoryAdminResponseDto createCategoryForAdmin(RestCategoryRequestDto requestDto);

  List<RestCategoryUserResponseDto> getCategoriesForUser();

  List<RestCategoryAdminResponseDto> getCategoriesForAdmin();

  List<RestCategoryAdminResponseDto> getCategoriesForAdminByFilter(RestCategoryAdminFilterRequestDto queryDto);

  RestCategoryUserResponseDto getCategoryBySlugForUser(String slug);

  RestCategoryAdminResponseDto getCategoryByIdForAdmin(Long id);

  RestCategoryAdminResponseDto updateCategoryByIdForAdmin(Long id, RestCategoryRequestDto requestDto);

  void deleteCategoryByIdForAdmin(Long id);
}
