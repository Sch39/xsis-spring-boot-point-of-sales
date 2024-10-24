
package dev.sch39.ecommerce.services.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.sch39.ecommerce.dtos.rest.request.RestPaginationRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminFilterRequestDto;
import dev.sch39.ecommerce.dtos.rest.request.RestProductAdminRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestProductAdminResponseDto;
import dev.sch39.ecommerce.entities.ProductEntity;
import dev.sch39.ecommerce.repositories.ProductRepository;
import dev.sch39.ecommerce.services.rest.RestProductService;

@Service
public class RestProductServiceImpl implements RestProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public RestProductAdminResponseDto createProductForAdmin(RestProductAdminRequestDto requestdto) {
    ProductEntity product = new ProductEntity();
    product.setName(requestdto.getName());
    product.setSlug(requestdto.getSlug());
    product.setCategoryId(requestdto.getCategoryId());
    product.setDeleted(requestdto.isDeleted());

    product = productRepository.save(product);
    return new RestProductAdminResponseDto(product);
  }

  @Override
  public Page<RestProductAdminResponseDto> getProductsForAdminByFilter(RestProductAdminFilterRequestDto filterRequest,
      RestPaginationRequestDto paginationRequestDto) {
    String include = filterRequest.getInclude();
    int page = paginationRequestDto.getPage();
    int size = paginationRequestDto.getSize();
    String sortBy = paginationRequestDto.getSortBy();
    String sortDirection = paginationRequestDto.getSortDirection();

    Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);

    if ("deleted".equals(include)) {
      return productRepository
          .findAllDeleted(pageable)
          .map(product -> new RestProductAdminResponseDto(product));
    } else if ("not-deleted".equals(include)) {
      return productRepository
          .findAllNotDeleted(pageable)
          .map(product -> new RestProductAdminResponseDto(product));
    }

    return productRepository
        .findAll(pageable)
        .map(product -> new RestProductAdminResponseDto(product));
  }

  @Override
  public RestProductAdminResponseDto getProductByIdForAdmin(Long id) {
    ProductEntity product = productRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid product  id: " + id));

    return new RestProductAdminResponseDto(product);
  }

  @Override
  public RestProductAdminResponseDto updateProductByIdForAdmin(Long id, RestProductAdminRequestDto requestDto) {
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
