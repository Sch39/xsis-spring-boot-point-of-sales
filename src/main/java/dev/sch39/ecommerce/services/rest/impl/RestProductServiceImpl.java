
package dev.sch39.ecommerce.services.rest.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductRequestdto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;
import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.repositories.ProductRepository;
import dev.sch39.ecommerce.services.rest.RestProductService;

@Service
public class RestProductServiceImpl implements RestProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public RestProductAdminResponseDto createProductForAdmin(RestProductRequestdto requestdto) {
    ProductEntity product = new ProductEntity();
    product.setName(requestdto.getName());
    product.setSlug(requestdto.getSlug());
    product.setCategoryId(requestdto.getCategoryId());
    product.setDeleted(requestdto.isDeleted());

    product = productRepository.save(product);
    return new RestProductAdminResponseDto(product);
  }

  @Override
  public List<RestProductAdminResponseDto> getProductsForAdmin() {
    List<ProductEntity> products = productRepository.findAll();

    return products
        .stream()
        .map(product -> new RestProductAdminResponseDto(product))
        .collect(Collectors.toList());
  }

  @Override
  public List<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest) {
    String include = filterRequest.getInclude();

    if ("deleted".equals(include)) {
      return productRepository
          .findAllDeleted()
          .stream()
          .map(product -> new RestProductAdminResponseDto(product))
          .collect(Collectors.toList());
    } else if ("not-deleted".equals(include)) {
      return productRepository
          .findAllNotDeleted()
          .stream()
          .map(product -> new RestProductAdminResponseDto(product))
          .collect(Collectors.toList());
    }

    return productRepository
        .findAll()
        .stream()
        .map(product -> new RestProductAdminResponseDto(product))
        .collect(Collectors.toList());
  }

  @Override
  public RestProductAdminResponseDto getProductByIdForAdmin(Long id) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product  id: " + id));

    return new RestProductAdminResponseDto(product);
  }

  @Override
  public RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductRequestdto requestDto) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

    product.setName(requestDto.getName());
    product.setSlug(requestDto.getSlug());
    product.setCategoryId(requestDto.getCategoryId());
    product.setDeleted(requestDto.isDeleted());

    product = productRepository.save(product);
    return new RestProductAdminResponseDto(product);
  }

  @Override
  public void deleteProductByIdForAdmin(Long id) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product id: " + id));

    productRepository.delete(product);
  }

}
