package com.example.stock.Craft.entity;


import com.example.stock.Craft.dto.OrderType;
//import com.sun.istack.internal.NotNull;

import java.math.BigDecimal;
import java.util.Objects;


public class Order {

    //@Column
    //@NotNull
    private String orderId;
    //@NotNull
    private Long createDate;
    //NotNull
    private String stockName;
    private OrderType orderType;
    //@NotNull
    private int quantity;
    //@NotNull
    private BigDecimal price;

    private boolean orderComplete;

    public Order() {
        // immutable class
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.orderId);
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj) return false;
        Order local = (Order)obj;
        if(local.orderId.equals(this.orderId)) return true;
        return false;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isOrderComplete() {
        return orderComplete;
    }

    public void setOrderComplete(boolean orderComplete) {
        this.orderComplete = orderComplete;
    }
}
