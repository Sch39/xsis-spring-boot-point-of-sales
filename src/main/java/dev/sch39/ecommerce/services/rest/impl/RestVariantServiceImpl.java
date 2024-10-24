package dev.sch39.ecommerce.services.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public List<RestVariantAdminResponseDto> getVariantsForAdminByFilter(
      RestVariantAdminFilterRequestDto filterRequestDto) {
    String include = filterRequestDto.getInclude();

    if ("deleted".equals(include)) {
      return variantRepository
          .findAllDeleted()
          .stream()
          .map(variant -> new RestVariantAdminResponseDto(variant))
          .collect(Collectors.toList());
    } else if ("not-deleted".equals(include)) {
      return variantRepository
          .findAllNotDeleted()
          .stream()
          .map(variant -> new RestVariantAdminResponseDto(variant))
          .collect(Collectors.toList());
    }
    return variantRepository
        .findAll()
        .stream()
        .map(variant -> new RestVariantAdminResponseDto(variant))
        .collect(Collectors.toList());
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
