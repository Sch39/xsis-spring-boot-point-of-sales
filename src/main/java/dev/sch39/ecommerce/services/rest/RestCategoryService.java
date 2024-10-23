package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryUserResponseDto;

public interface RestCategoryService {
  RestCategoryAdminResponseDto createCategory(RestCategoryRequestDto requestDto);

  List<RestCategoryUserResponseDto> getAllCategoriesForUser();

  List<RestCategoryAdminResponseDto> getAllCategoriesForAdmin();

  List<RestCategoryAdminResponseDto> getAllCategoriesForAdmin(boolean isDeleted);

  List<RestCategoryAdminResponseDto> getAllCategoriesForAdminByQueryParam(RestCategoryAdminFilterRequestDto queryDto);

  RestCategoryUserResponseDto getCategoryBySlugForUser(String slug);

  RestCategoryAdminResponseDto getCategoryByIdForAdmin(Long id);

  RestCategoryAdminResponseDto updateCategoryById(Long id, RestCategoryRequestDto requestDto);

  void deleteCategoryById(Long id);
}
