package com.example.stock.Craft.service;

import com.example.stock.Craft.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> createOrder(String auth, List<OrderDto> orderDto);
}
