package com.aaron.mapper;

import com.aaron.model.Stock;

import java.util.List;

public interface StockMapper {
    Stock findByTicker(String ticker);
    Stock findByName(String company);
    List<Stock> listCollection();
    void addStock(Stock stock);
}
