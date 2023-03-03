package com.aaron.service;

import com.aaron.mapper.StockMapper;
import com.aaron.model.Stock;
import com.aaron.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntelligentServiceImp implements StockMapper {

    @Autowired
    private StockRepository stockRepository;
    @Override
    public List<Stock> listCollection() {
        return stockRepository.findAll();
    }
}
