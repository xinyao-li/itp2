package com.aaron.model;

import javax.persistence.*;

@Entity
@Table(name = "collection_table")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "stock_seq")
    @SequenceGenerator(name = "stock_seq", sequenceName = "stock_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String ticker;

    @Column(nullable = false)
    private String company;

    public void setId(Integer id) { this.id = id;}
    public Integer getId() {
        return id;
    }
    public void setTicker(String ticker){
        this.ticker = ticker;
    }
    public String getTicker(){
        return ticker;
    }
    public void setCompany(String company){
        this.company = company;
    }
    public String getCompany(){
        return company;
    }

}
