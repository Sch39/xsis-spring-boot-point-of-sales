package dev.sch39.ecommerce.services.rest;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.sch39.ecommerce.dtos.rest.request.RestOrderUserRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestOrderUserResponseDto;

public interface RestOrderService {
  RestOrderUserResponseDto createOrder(RestOrderUserRequestDto requestDto) throws JsonProcessingException;

  String generateOrderReference();
}
