package dev.sch39.ecommerce.services.rest.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    List<Object[]> pricesData = variantRepository.findPricesByVariantIds(variantIds);
    if (pricesData.size() != orders.size()) {
      throw new IllegalArgumentException("Request contain not found variant_id");
    }
    if (variantQuantities.size() != orders.size()) {
      throw new IllegalArgumentException("Request contain blank quantity");
    }

    Map<Long, Double> priceMap = new HashMap<>();
    for (Object[] row : pricesData) {
      priceMap.put(((Number) row[0]).longValue(), (Double) row[1]);
    }

    Double totalPrice = 0.0;
    for (int i = 0; i < orders.size(); i++) {
      Long variantId = variantIds.get(i);
      Double quantity = variantQuantities.get(i);
      Double price = priceMap.get(variantId);

      totalPrice += (price * quantity);
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

    // variantRepository.updateStocks(availableVariantIds, variantQuantities);

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

    for (OrderItemUserDto itemUserDto : orders) {
      OrderDetailEntity orderDetail = new OrderDetailEntity();

      Long variantId = itemUserDto.getVariantId();
      Double quantity = itemUserDto.getQuantity();
      Double price = priceMap.get(variantId);

      orderDetail.setHeaderId(orderHeader.getId());
      orderDetail.setVariantId(variantId);
      orderDetail.setQuantity(quantity);
      orderDetail.setPrice(price);
      orderDetail.setDeleted(false);

      orderDetails.add(orderDetail);
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
