package com.aaron.model;

public class Stock {

    private String company;

    private String price;

    private String quantity;

    private String average_buy_price;

    private String equity;

    private String type;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAverage_buy_price() {
        return average_buy_price;
    }

    public void setAverage_buy_price(String average_buy_price) {
        this.average_buy_price = average_buy_price;
    }

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
