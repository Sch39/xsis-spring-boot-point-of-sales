package dev.sch39.ecommerce.services.rest.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.sch39.ecommerce.dtos.rest.request.OrderItemUserDto;
import dev.sch39.ecommerce.dtos.rest.request.RestOrderUserRequestDto;
import dev.sch39.ecommerce.dtos.rest.response.RestOrderUserResponseDto;
import dev.sch39.ecommerce.entities.OrderDetailEntity;
import dev.sch39.ecommerce.entities.OrderHeaderEntity;
import dev.sch39.ecommerce.entities.OrderHeaderEntity.OrderHeaderStatus;
import dev.sch39.ecommerce.repositories.OrderDetailRepository;
import dev.sch39.ecommerce.repositories.OrderHeaderRepository;
import dev.sch39.ecommerce.repositories.VariantRepository;
import dev.sch39.ecommerce.services.rest.RestOrderService;
import dev.sch39.ecommerce.services.rest.RestUserService;

@Service
public class RestOrderServiceImpl implements RestOrderService {
  @Autowired
  private VariantRepository variantRepository;

  @Autowired
  private OrderHeaderRepository orderHeaderRepository;

  @Autowired
  private RestUserService restUserService;

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Override
  @Transactional
  public RestOrderUserResponseDto createOrder(RestOrderUserRequestDto requestDto) throws JsonProcessingException {
    Double payMoney = requestDto.getPayMoney();
    List<OrderItemUserDto> orders = requestDto.getOrders();

    List<Long> variantIds = orders.stream().map(order -> order.getVariantId())
        .collect(Collectors.toList());

    List<Double> variantQuantities = orders.stream().map(order -> order.getQuantity()).collect(Collectors.toList());

    List<Double> variantPrices = variantRepository.findPricesByVariantIds(variantIds);
    if (variantPrices.size() != orders.size()) {
      throw new IllegalArgumentException("Request contain not found variant_id");
    }
    if (variantQuantities.size() != orders.size()) {
      throw new IllegalArgumentException("Request contain not blank quantity");
    }

    Double totalPrice = 0.0;
    for (int i = 0; i < orders.size(); i++) {
      totalPrice += (variantPrices.get(i) * variantQuantities.get(i));
    }

    if (totalPrice > payMoney) {
      throw new IllegalArgumentException("Not enough money");
    }
    ObjectMapper mapper = new ObjectMapper();
    String ordersJson = mapper.writeValueAsString(orders);

    List<Long> availableVariantIds = variantRepository.findAvailableVariantIds(ordersJson);

    if (variantIds.size() != availableVariantIds.size()) {
      throw new IllegalArgumentException("Request contains quantity with exceeds stock");
    }

    String headerReference = this.generateOrderReference();
    OrderHeaderEntity orderHeader = new OrderHeaderEntity();
    orderHeader.setReference(headerReference);
    orderHeader.setAmount(totalPrice);
    orderHeader.setOrderStatus(OrderHeaderStatus.PENDING);
    orderHeader.setUserId(restUserService.getAuthenticatedUserDetails().getId());

    orderHeader = orderHeaderRepository.save(orderHeader);
    // if (orders.get(0).getVariantId().equals(1L)) {
    // throw new IllegalArgumentException("error custom");
    // }
    List<OrderDetailEntity> orderDetails = new ArrayList<>();
    int i = 0;

    for (OrderItemUserDto itemUserDto : orders) {
      OrderDetailEntity orderDetail = new OrderDetailEntity();
      orderDetail.setHeaderId(orderHeader.getId());
      orderDetail.setVariantId(itemUserDto.getVariantId());
      orderDetail.setQuantity(itemUserDto.getQuantity());
      orderDetail.setPrice(variantPrices.get(i));
      orderDetail.setDeleted(false);

      orderDetails.add(orderDetail);
      i++;
    }

    orderDetailRepository.saveAll(orderDetails);

    orderHeader.setOrderStatus(OrderHeaderStatus.SUCCESS);
    orderHeaderRepository.save(orderHeader);

    RestOrderUserResponseDto responseDto = new RestOrderUserResponseDto(orderHeader);

    return responseDto;
  }

  @Override
  public String generateOrderReference() {
    LocalDate curreDate = LocalDate.now();
    String year = curreDate.format(DateTimeFormatter.ofPattern("yy"));
    String month = curreDate.format(DateTimeFormatter.ofPattern("MM"));

    int increment = orderHeaderRepository.countOrdersInMonth(curreDate.getYear(), curreDate.getMonthValue());

    String incrementStr = String.format("%04d", increment + 1);

    return "SBY-" + year + "-" + month + "-" + incrementStr;
  }

}
