package com.aaron.controller;

import com.aaron.model.Stock;
import com.aaron.response.LoginResponse;
import com.aaron.response.Response;
import com.aaron.service.ServiceProvider;
import com.aaron.service.StockServiceImp;
import com.google.api.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/stock")
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
    public ResponseEntity<Response> login(@RequestParam("username")String username, @RequestParam("password")String password){
        try{
            LOGGER.info(username+","+password);
            serviceProvider.getRobinhoodService().login(username, password);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.USERNAME_PASSWORD_INVALID, HttpStatus.NOT_FOUND);
        }
        Double price = serviceProvider.getRobinhoodService().quote("AAPL");
        String message = (price == null)?"login Failed":"login Success";
        LOGGER.info(message);
        if(message.equals("login Success")) {
            String token = genToken();
            return new ResponseEntity<>(new LoginResponse(token),HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.LOGIN_FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @RequestMapping(value="/logout",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> logout(){
        try{
            serviceProvider.getRobinhoodService().logout();
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.USERNAME_PASSWORD_INVALID,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("logout Success");
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }
    @RequestMapping(value="/quote",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Double> quote(@RequestParam(value="ticker",required = false)String ticker) {
        Double price = null;
        try {
            price = serviceProvider.getRobinhoodService().quote(ticker);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        System.out.println(price);
        return new ResponseEntity<>(price,HttpStatus.OK);
    }
    @RequestMapping(value="/buy",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> buy(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().buy(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.BUY_FAILED,HttpStatus.NOT_ACCEPTABLE);
        }
        LOGGER.info("Buy Success");
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }
    @RequestMapping(value="/sell",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> sell(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().sell(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.SELL_FAILED,HttpStatus.NOT_ACCEPTABLE);
        }
        LOGGER.info("Sell Success");
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }
    @RequestMapping(value="/buyPower",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Double> getBalance() {
        Double balance = null;
        try {
            balance = serviceProvider.getRobinhoodService().getBalance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(balance);
        return new ResponseEntity<>(balance,HttpStatus.OK);
    }
    @RequestMapping(value="/autoBuy",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> autoBuy(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        try{
            LOGGER.info(ticker+","+target+","+amount);
            serviceProvider.getRobinhoodService().autoBuy(ticker, target,amount);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.BUY_FAILED,HttpStatus.PAYLOAD_TOO_LARGE);
        }
        LOGGER.info("AutoBuy Completed");
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }
    @RequestMapping(value="/autoSell",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> autoSell(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        try{
            LOGGER.info(ticker+","+target+","+amount);
            serviceProvider.getRobinhoodService().autoSell(ticker, target,amount);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Response.SELL_FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("AutoSell Completed");
        return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
    }

    @RequestMapping(value="/collection",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Stock>> listCollection(){
        List<Stock> collection = stockServiceImp.listCollection();
        System.out.println(collection);
        return new ResponseEntity<>(collection,HttpStatus.OK);
    }
    @RequestMapping(value="/addCollection",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> addCollection(@RequestParam("ticker")String ticker){
        String company = serviceProvider.getRobinhoodService().getCompany(ticker);
        if (Objects.nonNull(company)){
            Stock stock = new Stock();
            stock.setTicker(ticker);
            stock.setCompany(company);
            stockServiceImp.addStock(stock);
            return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.ADD_COLLECTION_FAILED,HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value="/removeCollectionStock",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Response> removeCollectionStock(@RequestParam("name")String name){
        if (Objects.nonNull(stockServiceImp.findByTicker(name))){
            stockServiceImp.deleteByTicker(name);
            return new ResponseEntity<>(Response.SUCCESS, HttpStatus.OK);
        }
        if(Objects.nonNull(stockServiceImp.findByCompany(name))){
            stockServiceImp.deleteByCompany(name);
            return new ResponseEntity<>(Response.SUCCESS,HttpStatus.OK);
        }
        return new ResponseEntity<>(Response.NO_SUCH_COMPANY,HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value="/searchCollectionStock",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Stock> searchCollectionStock(@RequestParam("name")String name){
        if (Objects.nonNull(stockServiceImp.findByTicker(name))){
            return new ResponseEntity<>(stockServiceImp.findByTicker(name),HttpStatus.OK);
        }
        if(Objects.nonNull(stockServiceImp.findByCompany(name))){
            return new ResponseEntity<>(stockServiceImp.findByCompany(name),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
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
