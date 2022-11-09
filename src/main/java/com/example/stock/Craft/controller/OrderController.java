package com.example.stock.Craft.controller;

import com.example.stock.Craft.dto.OrderDto;
import com.example.stock.Craft.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<OrderDto>> createOrder(@RequestHeader("Authorization") String auth,
                                                      @RequestBody List<OrderDto> orderDtoList) {

        return ResponseEntity.ok(orderService.createOrder(auth, orderDtoList));

    }
}
