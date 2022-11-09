package com.example.stock.Craft.dto;

import com.example.stock.Craft.entity.Order;

import java.util.Comparator;

public class BuyOrderPriority implements Comparator<Order> {

    @Override
    public int compare(Order o1, Order o2) {
        if (null != o1 && o2 != null) {
            if (o1.getPrice().compareTo(o2.getPrice()) == 0) {
                return o1.getCreateDate() <= o2.getCreateDate() ? 1 : -1;
            }
            return o1.getPrice().compareTo(o2.getPrice());

        }
        return 0;
    }
}
