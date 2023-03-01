package com.aaron.repository;

import com.aaron.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findByTicker(String ticker);
    Stock findByCompany(String company);
    void deleteByTicker(String ticker);
    void deleteByCompany(String company);
}
