package dev.sch39.ecommerce.services.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryUserResponseDto;
import dev.sch39.ecommerce.entities.CategoryEntity;
import dev.sch39.ecommerce.repositories.CategoryRepository;
import dev.sch39.ecommerce.services.rest.RestCategoryService;

@Service
public class RestCategoryServiceImpl implements RestCategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public RestCategoryAdminResponseDto createCategory(RestCategoryRequestDto requestDto) {
    CategoryEntity category = new CategoryEntity();
    category.setName(requestDto.getName());
    category.setDescription(requestDto.getDescription());
    category.setSlug(requestDto.getSlug());
    category.setDeleted(requestDto.isDeleted());

    category = categoryRepository.save(category);

    return new RestCategoryAdminResponseDto(category);
  }

  @Override
  public List<RestCategoryUserResponseDto> getAllCategoriesForUser() {
    List<CategoryEntity> categories = categoryRepository.findAllNotDeleted();

    return categories
        .stream()
        .map(RestCategoryUserResponseDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public List<RestCategoryAdminResponseDto> getAllCategoriesForAdmin() {
    List<CategoryEntity> categories = categoryRepository.findAll();

    return categories
        .stream()
        .map(RestCategoryAdminResponseDto::new)
        .collect(Collectors.toList());
  }

  @Override
  public List<RestCategoryAdminResponseDto> getAllCategoriesForAdmin(boolean isDeleted) {

    if (isDeleted) {
      return categoryRepository
          .findAllDeleted()
          .stream()
          .map(category -> new RestCategoryAdminResponseDto(category))
          .collect(Collectors.toList());
    }

    return categoryRepository
        .findAllNotDeleted()
        .stream()
        .map(category -> new RestCategoryAdminResponseDto(category))
        .collect(Collectors.toList());
  }

  @Override
  public RestCategoryUserResponseDto getCategoryBySlugForUser(String slug) {
    CategoryEntity category = categoryRepository
        .findBySlug(slug).orElseThrow(() -> new IllegalArgumentException("Invalid category slug: " + slug));
    RestCategoryUserResponseDto responseDto = new RestCategoryUserResponseDto(category);
    return responseDto;
  }

  @Override
  public RestCategoryAdminResponseDto getCategoryByIdForAdmin(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
    RestCategoryAdminResponseDto responseDto = new RestCategoryAdminResponseDto(category);
    return responseDto;
  }

  @Override
  public RestCategoryAdminResponseDto updateCategoryById(Long id, RestCategoryRequestDto requestDto) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));

    category.setName(requestDto.getName());
    category.setDescription(requestDto.getDescription());
    category.setSlug(requestDto.getSlug());
    category.setDeleted(requestDto.isDeleted());
    category = categoryRepository.save(category);
    return new RestCategoryAdminResponseDto(category);
  }

  @Override
  public void deleteCategoryById(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
    categoryRepository.delete(category);
  }

  @Override
  public List<RestCategoryAdminResponseDto> getAllCategoriesForAdminByQueryParam(
      RestCategoryAdminFilterRequestDto queryDto) {
    String include = queryDto.getInclude();
    if ("deleted".equals(include)) {
      return categoryRepository
          .findAllDeleted()
          .stream()
          .map(category -> new RestCategoryAdminResponseDto(category))
          .collect(Collectors.toList());
    } else if ("not-deleted".equals(include)) {
      return categoryRepository
          .findAllNotDeleted()
          .stream()
          .map(category -> new RestCategoryAdminResponseDto(category))
          .collect(Collectors.toList());
    }

    return categoryRepository
        .findAll()
        .stream()
        .map(category -> new RestCategoryAdminResponseDto(category))
        .collect(Collectors.toList());
  }

}
