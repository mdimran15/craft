package com.example.stock.Craft.service;

import com.example.stock.Craft.dto.OrderDto;
import com.example.stock.Craft.entity.Order;
import com.example.stock.Craft.repository.OrderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServcieImpl implements OrderService {

    private OrderServiceRepository orderServiceRepository;

    @Override
    public List<OrderDto> createOrder(String auth, List<OrderDto> dtoList) {
        // validation
        // auth is bearertoken
        List<Order> orderList = this.toEntity(dtoList);
        orderServiceRepository.saveAll(orderList);
        // the data is ery huge and concurrnet so we can use kafka so we not miss any data
        OrderMatching orderMatching = new OrderMatching(orderList);
        return this.toDto(orderMatching.getResponseOrderList());
    }

    private List<Order> toEntity(List<OrderDto> dtoList) {
        return null;
    }

    private List<OrderDto> toDto(List<Order> orderList) {
        return new ArrayList<>();
    }
}
