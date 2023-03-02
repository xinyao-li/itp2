package com.aaron.controller;

import com.aaron.response.Response;
import com.aaron.service.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/intelligent")
public class IntelligentController {

    private static Logger LOGGER = LoggerFactory.getLogger(IntelligentController.class);

    @Autowired
    private ServiceProvider serviceProvider;

    @RequestMapping(value="/plotPrediction",method = RequestMethod.POST)
    public Response plotPrediction(@RequestParam("ticker")String ticker,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        try{
            serviceProvider.getIntelligentService().plotPrediction(ticker,startDate,endDate);
        }catch(Exception e){
            e.printStackTrace();
            return Response.PREDICTION_FAILED;
        }
        LOGGER.info("logout Success");
        return Response.SUCCESS;
    }

    @RequestMapping(value="/pricePrediction",method = RequestMethod.GET)
    public double pricePrediction(@RequestParam("ticker")String ticker,@RequestParam("startDate")String startDate,@RequestParam("endDate")String endDate){
        Double price = null;
        try{
            price = serviceProvider.getIntelligentService().pricePredict(ticker,startDate,endDate);
        }catch(Exception e){
            e.printStackTrace();
        }
        return price;
    }
}
