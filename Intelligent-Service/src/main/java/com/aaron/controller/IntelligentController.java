package com.aaron.controller;

import com.aaron.model.Stock;
import com.aaron.response.Response;
import com.aaron.service.IntelligentServiceImp;
import com.aaron.service.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/intelligent")
public class IntelligentController {

    private static Logger LOGGER = LoggerFactory.getLogger(IntelligentController.class);

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private IntelligentServiceImp intelligentServiceImp;

    @RequestMapping(value="/plotPrediction",method = RequestMethod.POST)
    public ResponseEntity<Response> plotPrediction(@RequestParam("ticker")String ticker,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        try{
            serviceProvider.getIntelligentService().plotPrediction(ticker,startDate,endDate);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.PREDICTION_FAILED, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }

    @RequestMapping(value="/pricePrediction",method = RequestMethod.GET)
    public ResponseEntity<Double> pricePrediction(@RequestParam("ticker")String ticker,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        Double price = null;
        try{
            price = serviceProvider.getIntelligentService().pricePredict(ticker,startDate,endDate);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        LOGGER.info("price should be: "+price);
        return ResponseEntity.ok(price);
    }

    @RequestMapping(value="/dailyPrediction",method = RequestMethod.POST)
    public ResponseEntity<Response> dailyPrediction(){
        List<Stock> stockList = intelligentServiceImp.listCollection();
        Random random = new Random();
        Stock stock = stockList.get(random.nextInt(stockList.size()));
        String ticker = stock.getTicker();
        LOGGER.info("stock is chosen is: "+ticker);
        String nextMonth = "";
        String prevYear = "";
        try {
            LocalDate date = LocalDate.now();
            nextMonth = date.plusDays(30).toString();
            prevYear = date.minusYears(1).toString();
            serviceProvider.getIntelligentService().plotPrediction(ticker,prevYear,nextMonth);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.PREDICTION_FAILED, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }
}
