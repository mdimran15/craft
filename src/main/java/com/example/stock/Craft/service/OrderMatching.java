package com.example.stock.Craft.service;

import com.example.stock.Craft.dto.BuyOrderPriority;
import com.example.stock.Craft.dto.OrderResponse;
import com.example.stock.Craft.dto.OrderType;
import com.example.stock.Craft.dto.SellOrderPriority;
import com.example.stock.Craft.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class OrderMatching implements Runnable {

    private static ConcurrentHashMap<String, Queue<Order>> mapSellOrder = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Queue<Order>> mapBuyOrder = new ConcurrentHashMap<>();
    private List<Order> orderList;
    private List<OrderResponse> responseOrderList;

    public OrderMatching(List<Order> orderList) {
        this.orderList = orderList;
        this.orderExchaneData();
    }

    @Override
    public void run() {

        for (Order order : this.orderList) {
            if (OrderMatching.mapSellOrder.containsKey(order.getStockName()) && OrderMatching.mapBuyOrder.containsKey(order.getStockName())) {
                this.mapOrder(OrderMatching.mapSellOrder.get(order.getStockName()), OrderMatching.mapBuyOrder.get(order.getStockName()));
            }
        }

    }

    private void mapOrder(Queue<Order> sellOrders, Queue<Order> buyOrders) {

        if (sellOrders.isEmpty() || buyOrders.isEmpty()) return;
        Order sellOrder = sellOrders.poll();
        Order buyOrder = buyOrders.poll();
        int sellQuantity = sellOrder.getQuantity();
        int buyQuantity = buyOrder.getQuantity();
        while (!sellOrders.isEmpty() && !buyOrders.isEmpty()) {

            if (buyOrder.getPrice().compareTo(sellOrder.getPrice()) >= 0) {

                if (sellQuantity > buyQuantity) {
                    prepareResponse(sellOrder, buyOrder);
                    // update table for buyorder completed
                    sellQuantity = sellQuantity - buyQuantity;
                    buyOrder = buyOrders.poll();
                } else if (sellQuantity < buyQuantity) {
                    prepareResponse(sellOrder, buyOrder);
                    // update table for sellorder completed
                    buyQuantity = buyQuantity - sellQuantity;
                    sellOrder = sellOrders.poll();
                } else {
                    prepareResponse(sellOrder, buyOrder);
                    // update table for buyorder and sellorder completed
                    buyQuantity = sellQuantity = 0;
                    sellOrder = sellOrders.poll();
                    buyOrder = buyOrders.poll();
                }

            }
        }
    }

    private void prepareResponse(Order sellOrder, Order buyOrder) {
        OrderResponse loc = new OrderResponse();
        loc.setSellOrderId(sellOrder.getOrderId());
        loc.setQuantity(buyOrder.getQuantity());
        loc.setPrice(sellOrder.getPrice());
        loc.setBuyOrderId(buyOrder.getOrderId());
        responseOrderList = new ArrayList<>();
        responseOrderList.add(loc);

    }

    private void orderExchaneData() {
        if (null != this.orderList) {
            for (Order order : this.orderList) {
                if (order.getOrderType() == OrderType.BUY) {
                    this.updateBuyOrderMap(order);
                } else if (OrderType.SELL == order.getOrderType()) {
                    this.updateSellOrderMap(order);
                }
            }
        }
    }

    private void updateBuyOrderMap(Order order) {
        if (OrderMatching.mapBuyOrder.containsKey(order.getStockName())) {
            Queue<Order> queue = OrderMatching.mapBuyOrder.get(order.getStockName());
            queue.add(order);
        } else {
            Queue<Order> queue = new PriorityQueue<>(new BuyOrderPriority());
            queue.add(order);
            OrderMatching.mapBuyOrder.put(order.getStockName(), queue);

        }
    }

    private void updateSellOrderMap(Order order) {
        if (OrderMatching.mapSellOrder.containsKey(order.getStockName())) {
            Queue<Order> queue = OrderMatching.mapSellOrder.get(order.getStockName());
            queue.add(order);
        } else {
            Queue<Order> queue = new PriorityQueue<>(new SellOrderPriority());
            queue.add(order);
            OrderMatching.mapSellOrder.put(order.getStockName(), queue);
        }
    }

    public List<OrderResponse> getResponseOrderList() {
        return responseOrderList;
    }

    public static void cleanStockWorkBook() {
        OrderMatching.mapSellOrder.clear();
        OrderMatching.mapBuyOrder.clear();
    }
}
