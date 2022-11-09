package com.example.stock.Craft.dto;

public enum OrderType {

    SELL("sell"), BUY("buy");
    private String orderType;

    OrderType(String type) {
        this.orderType = type;
    }

    public String getOrderType() {
        return this.orderType;
    }
}
