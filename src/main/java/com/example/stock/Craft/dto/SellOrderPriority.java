package com.example.stock.Craft.dto;

import com.example.stock.Craft.entity.Order;

import java.util.Comparator;
import java.util.Objects;

public class SellOrderPriority implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
            if (o1.getPrice().compareTo(o2.getPrice()) == -1) return 1;
            if (o1.getPrice().compareTo(o2.getPrice()) == 1) return -1;
            if(o1.getPrice().compareTo(o2.getPrice()) == 0){
               return (o1.getCreateDate() < o2.getCreateDate()) ? 1 : -1;
            }
        }
        return 0;
    }
}
