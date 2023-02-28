package com.aaron.controller;

import com.aaron.model.Stock;
import com.aaron.response.LoginResponse;
import com.aaron.response.Response;
import com.aaron.service.ServiceProvider;
import com.aaron.service.StockServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/ita")
public class StockController {

    private static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private StockServiceImp stockServiceImp;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login() {return "login";}

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username")String username, @RequestParam("password")String password){
        try{
            LOGGER.info(username+","+password);
            serviceProvider.getRobinhoodService().login(username, password);
        }catch(Exception e){
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        LOGGER.info("login Success");
        String token = genToken();

        return new LoginResponse(token);
    }
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    @ResponseBody
    public Response logout(){
        try{
            serviceProvider.getRobinhoodService().logout();
        }catch(Exception e){
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        LOGGER.info("logout Success");
        return Response.SUCCESS;
    }
    @RequestMapping(value="/quote",method = RequestMethod.GET)
    @ResponseBody
    public double quote(@RequestParam(value="ticker",required = false)String ticker) {
        Double price = null;
        try {
            price = serviceProvider.getRobinhoodService().quote(ticker);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Response.N0_SUCH_TIKCER);
        }
        System.out.println(price);
        return price;
    }
    @RequestMapping(value="/buy",method = RequestMethod.POST)
    @ResponseBody
    public Response buy(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().buy(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return Response.BUY_FAILED;
        }
        LOGGER.info("Buy Success");
        return Response.SUCCESS;
    }
    @RequestMapping(value="/sell",method = RequestMethod.POST)
    @ResponseBody
    public Response sell(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().sell(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return Response.SELL_FAILED;
        }
        LOGGER.info("Sell Success");
        return Response.SUCCESS;
    }
    @RequestMapping(value="/buyPower",method = RequestMethod.GET)
    @ResponseBody
    public double getBalance() {
        Double balance = null;
        try {
            balance = serviceProvider.getRobinhoodService().getBalance();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Response.GET_BALANCE_FAILED);
        }
        System.out.println(balance);
        return balance;
    }
    @RequestMapping(value="/autoBuy",method = RequestMethod.POST)
    @ResponseBody
    public Response autoBuy(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        try{
            LOGGER.info(ticker+","+target+","+amount);
            serviceProvider.getRobinhoodService().autoBuy(ticker, target,amount);
        }catch(Exception e){
            e.printStackTrace();
            return Response.BUY_FAILED;
        }
        LOGGER.info("AutoBuy Completed");
        return Response.SUCCESS;
    }
    @RequestMapping(value="/autoSell",method = RequestMethod.POST)
    @ResponseBody
    public Response autoSell(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        try{
            LOGGER.info(ticker+","+target+","+amount);
            serviceProvider.getRobinhoodService().autoSell(ticker, target,amount);
        }catch(Exception e){
            e.printStackTrace();
            return Response.SELL_FAILED;
        }
        LOGGER.info("AutoSell Completed");
        return Response.SUCCESS;
    }

    @RequestMapping(value="/collection",method = RequestMethod.GET)
    @ResponseBody
    public List<Stock> listCollection(){
        List<Stock> collection = stockServiceImp.listCollection();
        System.out.println(collection);
        return collection;
    }
    @RequestMapping(value="/addCollection",method = RequestMethod.POST)
    @ResponseBody
    public Response addCollection(@RequestParam("ticker")String ticker){
        String company = serviceProvider.getRobinhoodService().getCompany(ticker);
        if (Objects.nonNull(company)){
            Stock stock = new Stock();
            stock.setTicker(ticker);
            stock.setCompany(company);
            stockServiceImp.addStock(stock);
            return Response.SUCCESS;
        }
        return Response.ADD_COLLECTION_FAILED;
    }

    private String genToken() {
        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }
    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }
}
