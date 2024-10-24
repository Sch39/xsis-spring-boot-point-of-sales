package dev.sch39.ecommerce.services.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestVariantAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestVariantAdminResponseDto;
import dev.sch39.ecommerce.entities.VariantEntity;
import dev.sch39.ecommerce.repositories.VariantRepository;
import dev.sch39.ecommerce.services.rest.RestVariantService;

@Service
public class RestVariantServiceImpl implements RestVariantService {
  @Autowired
  private VariantRepository variantRepository;

  @Override
  public RestVariantAdminResponseDto createVariantForAdmin(RestVariantAdminRequestDto requestDto) {
    VariantEntity variant = new VariantEntity();
    variant.setName(requestDto.getName());
    variant.setDescription(requestDto.getDescription());
    variant.setSlug(requestDto.getSlug());
    variant.setPrice(requestDto.getPrice());
    variant.setStock(requestDto.getStock());
    variant.setProductId(requestDto.getProductId());
    variant.setDeleted(requestDto.isDeleted());

    variant = variantRepository.save(variant);
    return new RestVariantAdminResponseDto(variant);
  }

  @Override
  public Page<RestVariantAdminResponseDto> getVariantsForAdminByFilter(
      RestVariantAdminFilterRequestDto filterRequestDto, RestPaginationRequestDto paginationRequestDto) {
    String include = filterRequestDto.getInclude();
    int page = paginationRequestDto.getPage();
    int size = paginationRequestDto.getSize();
    String sortBy = paginationRequestDto.getSortBy();
    String sortDirection = paginationRequestDto.getSortDirection();

    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);

    if ("deleted".equals(include)) {
      return variantRepository
          .findAllDeleted(pageable)
          .map(variant -> new RestVariantAdminResponseDto(variant));
    } else if ("not-deleted".equals(include)) {
      return variantRepository
          .findAllNotDeleted(pageable)
          .map(variant -> new RestVariantAdminResponseDto(variant));
    }
    return variantRepository
        .findAll(pageable)
        .map(variant -> new RestVariantAdminResponseDto(variant));
  }

  @Override
  public RestVariantAdminResponseDto getVariantByIdForAdmin(Long id) {
    VariantEntity variant = variantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid variant  id: " + id));

    return new RestVariantAdminResponseDto(variant);
  }

  @Override
  public RestVariantAdminResponseDto updateVariantByIdForAdmin(Long id, RestVariantAdminRequestDto requestDto) {
    VariantEntity variant = variantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid variant  id: " + id));

    variant.setName(requestDto.getName());
    variant.setDescription(requestDto.getDescription());
    variant.setSlug(requestDto.getSlug());
    variant.setPrice(requestDto.getPrice());
    variant.setStock(requestDto.getStock());
    variant.setProductId(requestDto.getProductId());
    variant.setDeleted(requestDto.isDeleted());

    variant = variantRepository.save(variant);
    return new RestVariantAdminResponseDto(variant);
  }

  @Override
  public void deleteVariantByIdForAdmin(Long id) {
    VariantEntity variant = variantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid variant  id: " + id));

    variantRepository.delete(variant);
  }

}
