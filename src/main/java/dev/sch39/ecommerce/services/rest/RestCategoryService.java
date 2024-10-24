package dev.sch39.ecommerce.services.rest;

import java.util.List;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryUserResponseDto;

public interface RestCategoryService {
  RestCategoryAdminResponseDto createCategoryForAdmin(RestCategoryAdminRequestDto requestDto);

  List<RestCategoryUserResponseDto> getCategoriesForUser();

  List<RestCategoryAdminResponseDto> getCategoriesForAdminByFilter(RestCategoryAdminFilterRequestDto queryDto);

  RestCategoryUserResponseDto getCategoryBySlugForUser(String slug);

  RestCategoryAdminResponseDto getCategoryByIdForAdmin(Long id);

  RestCategoryAdminResponseDto updateCategoryByIdForAdmin(Long id, RestCategoryAdminRequestDto requestDto);

  void deleteCategoryByIdForAdmin(Long id);
}
