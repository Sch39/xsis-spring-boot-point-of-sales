package dev.sch39.ecommerce.services.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestCategoryAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestCategoryAdminResponseDto;
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
  public Page<RestCategoryAdminResponseDto> getCategoriesForAdminByFilter(
      RestCategoryAdminFilterRequestDto queryDto, RestPaginationRequestDto paginationRequestDto) {
    String include = queryDto.getInclude();
    int page = paginationRequestDto.getPage();
    int size = paginationRequestDto.getSize();

    Pageable pageable = PageRequest.of(page, size);
    if ("deleted".equals(include)) {
      return categoryRepository
          .findAllDeleted(pageable)
          .map(category -> new RestCategoryAdminResponseDto(category));
    } else if ("not-deleted".equals(include)) {
      return categoryRepository
          .findAllNotDeleted(pageable)
          .map(category -> new RestCategoryAdminResponseDto(category));
    }

    return categoryRepository
        .findAll(pageable)
        .map(category -> new RestCategoryAdminResponseDto(category));
  }

}
