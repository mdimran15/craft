package com.example.stock.Craft.service;

import com.example.stock.Craft.dto.OrderDto;
import com.example.stock.Craft.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> createOrder(String auth, List<OrderDto> orderDto);
}
