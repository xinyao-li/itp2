package com.aaron.controller;

import com.aaron.model.User;
import com.aaron.response.Response;
import com.aaron.service.ServiceProvider;
import com.aaron.service.UserServiceImp;
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

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/stock")
public class StockController {

    private static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private UserServiceImp userServiceImp;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login() {return "login";}

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(@RequestParam(value = "token", required = false)String token, HttpSession session) {
        if(token == null || !token.equals(session.getAttribute("token"))) {
            return "redirect:/stock/login";
        }
        return "home";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password,HttpSession session){
        List<User> users = userServiceImp.listUser();
        if(!verify(users,username,password)){
            return "redirect:/stock/login?error=true";
        }
        try{
            LOGGER.info(username+","+password);
            serviceProvider.getRobinhoodService().login(username, password);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/stock/login?error=true";
        }
        Double price = serviceProvider.getRobinhoodService().quote("AAPL");
        String message = (price == null)?"login Failed":"login Success";
        LOGGER.info(message);
        if(message.equals("login Success")) {
            String token = genToken();
            session.setAttribute("token", token);
            return "redirect:/stock/home?token=" + token;
        }
        return "redirect:/stock/login?error=true";
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

    @RequestMapping(value="/holding",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getHolding(){
        String holds = null;
        try{
            holds = serviceProvider.getRobinhoodService().getHolding();
        }catch(Exception e){
            e.printStackTrace();
        }
        LOGGER.info(""+holds);
        return new ResponseEntity<>(holds,HttpStatus.OK);
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

    private boolean verify(List<User> users, String username, String password){
        for(User user: users){
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
