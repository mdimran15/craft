package com.example.stock.Craft.service;

import com.example.stock.Craft.dto.OrderDto;
import com.example.stock.Craft.dto.OrderResponse;
import com.example.stock.Craft.dto.OrderType;
import com.example.stock.Craft.entity.Order;
import com.example.stock.Craft.repository.OrderServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServcieImpl implements OrderService {

    private OrderServiceRepository orderServiceRepository;

    @Override
    public List<OrderResponse> createOrder(String auth, List<OrderDto> dtoList) {
        // validation
        // auth is bearertoken
        List<Order> orderList = this.toEntity(dtoList);
        //orderServiceRepository.saveAll(orderList);
        // the data is ery huge and concurrnet so we can use kafka so we not miss any data
        OrderMatching orderMatching = new OrderMatching(orderList);
        return orderMatching.getResponseOrderList();
    }

    private List<Order> toEntity(List<OrderDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) return new ArrayList<>();
        List<Order> listOrder = new ArrayList<>();
        for (OrderDto dto : dtoList) {
            Order loc = new Order();
            loc.setOrderId(dto.getOrderId());
            loc.setPrice(dto.getPrice());
            loc.setQuantity(dto.getQuantity());
            loc.setOrderType(dto.getOrderType().equalsIgnoreCase("Sell") ? OrderType.SELL : OrderType.BUY);
            loc.setOrderComplete(false);
            loc.setCreateDate(dto.getTimeStamp());
            loc.setStockName(dto.getStockName());
            listOrder.add(loc);
        }

        return listOrder;
    }

    private List<OrderDto> toDto(List<Order> orderList) {
        return new ArrayList<>();
    }
}
