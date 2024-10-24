package dev.sch39.ecommerce.services.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminRequestDto;
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
  public RestCategoryAdminResponseDto createCategoryForAdmin(RestCategoryAdminRequestDto requestDto) {
    CategoryEntity category = new CategoryEntity();
    category.setName(requestDto.getName());
    category.setDescription(requestDto.getDescription());
    category.setSlug(requestDto.getSlug());
    category.setDeleted(requestDto.isDeleted());

    category = categoryRepository.save(category);

    return new RestCategoryAdminResponseDto(category);
  }

  @Override
  public List<RestCategoryUserResponseDto> getCategoriesForUser() {
    List<CategoryEntity> categories = categoryRepository.findAllNotDeleted();

    return categories
        .stream()
        .map(RestCategoryUserResponseDto::new)
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
  public RestCategoryAdminResponseDto updateCategoryByIdForAdmin(Long id, RestCategoryAdminRequestDto requestDto) {
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
  public void deleteCategoryByIdForAdmin(Long id) {
    CategoryEntity category = categoryRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid category id: " + id));
    categoryRepository.delete(category);
  }

  @Override
  public List<RestCategoryAdminResponseDto> getCategoriesForAdminByFilter(
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
