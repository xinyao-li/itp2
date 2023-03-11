package com.aaron.controller;

import com.aaron.model.AutoingStock;
import com.aaron.model.Hold;
import com.aaron.model.Stock;
import com.aaron.model.User;
import com.aaron.service.ServiceProvider;
import com.aaron.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private ServiceProvider serviceProvider;

    @Autowired
    private UserServiceImp userServiceImp;

    private String token;


    private List<AutoingStock> autoBuying = new ArrayList<>();

    private List<AutoingStock> autoSelling = new ArrayList<>();

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login() {return "login";}

    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home() {
        if(token == null) {
            return "redirect:/stock/login";
        }
        return "home";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @RequestParam("password")String password){
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
            token = genToken();
            return "redirect:/stock/home";
        }
        return "redirect:/stock/login?error=true";
    }
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(){
        try{
            serviceProvider.getRobinhoodService().logout();
        }catch(Exception e){
            e.printStackTrace();
        }
        LOGGER.info("logout Success");
        token = null;
        return "redirect:/stock/login";
    }
    @RequestMapping(value="/quote",method = RequestMethod.GET)
    public String quote(@RequestParam(value="ticker",required = false)String ticker, Model model) {
        if(token == null) {
            return "redirect:/stock/login";
        }
        Double price = null;
        try {
            price = serviceProvider.getRobinhoodService().quote(ticker);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/stock/quote?error=true";
        }
        LOGGER.info(""+price);
        if(price == null) {
            return "redirect:/stock/quote?error=true";
        }
        String price_str = ""+price;
        Stock stock = new Stock();
        stock.setTicker(ticker);
        stock.setCompany(serviceProvider.getRobinhoodService().getCompany(ticker));
        stock.setPrice(price_str);
        model.addAttribute("stock",stock);
        return "quote";
    }

    @RequestMapping(value="/buy",method = RequestMethod.GET)
    public String buy() {
        if(token == null) {
            return "redirect:/stock/login";
        }
        return "buy";
    }

    @RequestMapping(value="/buy",method = RequestMethod.POST)
    public String buy(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().buy(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/stock/buy?error=true";
        }
        LOGGER.info("Buy Success");
        return "redirect:/stock/home";
    }

    @RequestMapping(value="/sell",method = RequestMethod.GET)
    public String sell() {
        if(token == null) {
            return "redirect:/stock/login";
        }
        return "sell";
    }

    @RequestMapping(value="/sell",method = RequestMethod.POST)
    public String sell(@RequestParam("ticker")String ticker, @RequestParam("amount")int amount){
        try{
            LOGGER.info(ticker+","+amount);
            serviceProvider.getRobinhoodService().sell(ticker, amount);
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/stock/sell?error=true";
        }
        LOGGER.info("Sell Success");
        return "redirect:/stock/home";
    }

    @RequestMapping(value="/autobuy",method = RequestMethod.GET)
    public String autoBuy(){
        if(token == null) {
            return "redirect:/stock/login";
        }
        if(autoBuying.size() == 0)
            return "autobuy";
        return "redirect:/stock/stopbuy";
    }
    @RequestMapping(value="/autobuy",method = RequestMethod.POST)
    public String autoBuy(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        Thread thread = new Thread(() -> {
            try {
                serviceProvider.getRobinhoodService().autoBuy(ticker, target, amount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        autoBuying.add(new AutoingStock());
        autoBuying.get(autoBuying.size()-1).setTicker(ticker);
        autoBuying.get(autoBuying.size()-1).setTarget(target);
        autoBuying.get(autoBuying.size()-1).setAmount(amount);
        LOGGER.info("AutoBuy triggered");
        return "autotriggered";
    }

    @RequestMapping(value="/stopbuy",method = RequestMethod.GET)
    public String stopBuy(Model model){
        if(token == null) {
            return "redirect:/stock/login";
        }
        model.addAttribute("autoBuying",autoBuying);
        return "stopbuy";
    }
    @RequestMapping(value="/stopbuy",method = RequestMethod.POST)
    public String stopBuy(@RequestParam("ticker")String ticker) {
        try {
            serviceProvider.getRobinhoodService().stopBuy(ticker);
            for (int i = 0; i < autoBuying.size(); i++) {
                if (autoBuying.get(i).getTicker().equals(ticker)){
                    autoBuying.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/stock/stopbuy";
    }

    @RequestMapping(value="/autosell",method = RequestMethod.GET)
    public String autoSell(){
        if(token == null) {
            return "redirect:/stock/login";
        }
        if(autoSelling.size() == 0)
            return "autosell";
        return "redirect:/stock/stopsell";
    }
    @RequestMapping(value="/autosell",method = RequestMethod.POST)
    @ResponseBody
    public String autoSell(@RequestParam("ticker")String ticker, @RequestParam("target")double target,@RequestParam("amount")double amount){
        String holds = null;
        try{
            holds = serviceProvider.getRobinhoodService().getHolding();
        }catch(Exception e){
            e.printStackTrace();
        }
        List<Hold> holdList = convertInfo(holds);
        if(containsStock(holdList,ticker)) {
            Thread thread = new Thread(() -> {
                try {
                    serviceProvider.getRobinhoodService().autoSell(ticker, target, amount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            autoSelling.add(new AutoingStock());
            autoSelling.get(autoSelling.size() - 1).setTicker(ticker);
            autoSelling.get(autoSelling.size() - 1).setTarget(target);
            autoSelling.get(autoSelling.size() - 1).setAmount(amount);
            LOGGER.info("AutoSell triggered");
            return "autotriggered";
        }
        return "redirect:/stock/autosell?error=true";
    }

    @RequestMapping(value="/stopsell",method = RequestMethod.GET)
    public String stopSell(Model model){
        if(token == null) {
            return "redirect:/stock/login";
        }
        model.addAttribute("autoSelling",autoSelling);
        return "stopsell";
    }
    @RequestMapping(value="/stopsell",method = RequestMethod.POST)
    public String stopSell(@RequestParam("ticker")String ticker) {
        try {
            serviceProvider.getRobinhoodService().stopSell(ticker);
            for (int i = 0; i < autoSelling.size(); i++) {
                if (autoSelling.get(i).getTicker().equals(ticker)){
                    autoSelling.remove(i);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/stock/stopsell";
    }

    @RequestMapping(value="/holding",method = RequestMethod.GET)
    public String getHolding(Model model){
        if(token == null) {
            return "redirect:/stock/login";
        }
        String holds = null;
        try{
            holds = serviceProvider.getRobinhoodService().getHolding();
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/stock/holding?error=true";
        }
        List<Hold> holdingList = convertInfo(holds);
        Double balance = null;
        try {
            balance = serviceProvider.getRobinhoodService().getBalance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("holdingList",holdingList);
        model.addAttribute("balance",balance);
        return "holding";
    }

    private List<Hold> convertInfo(String holds){
        List<String> list = new ArrayList<>();
        List<Hold> holdingList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < holds.length();i++){
            if(Character.isDigit(holds.charAt(i))||Character.isLetter(holds.charAt(i))||holds.charAt(i) == '.'||holds.charAt(i)=='_'){
                sb.append(holds.charAt(i));
            }
            else{
                if(sb.length() > 0)
                    list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        for(int i = 0; i < list.size();i++){
            if(Character.isUpperCase(list.get(i).charAt(0))){
                if(holdingList.size() > 0&&holdingList.get(holdingList.size()-1).getPrice() == null) {
                    holdingList.remove(holdingList.size()-1);
                }
                holdingList.add(new Hold());
                holdingList.get(holdingList.size()-1).setCompany(list.get(i));
            }
            else if(list.get(i).equals("price")){
                holdingList.get(holdingList.size()-1).setPrice(list.get(i+1));
            }
            else if(list.get(i).equals("quantity")){
                holdingList.get(holdingList.size()-1).setQuantity(list.get(i+1));
            }
            else if(list.get(i).equals("average_buy_price")){
                holdingList.get(holdingList.size()-1).setAverage_buy_price(list.get(i+1));
            }
            else if(list.get(i).equals("equity")){
                holdingList.get(holdingList.size()-1).setEquity(list.get(i+1));
            }
            else if(list.get(i).equals("type")){
                holdingList.get(holdingList.size()-1).setType(list.get(i+1));
            }
        }
        if(holdingList.size() > 0&&holdingList.get(holdingList.size()-1).getPrice() == null) {
            holdingList.remove(holdingList.size()-1);
        }
        return holdingList;
    }

    private boolean containsStock(List<Hold> holdList,String ticker){
        for(Hold hold: holdList){
            if(hold.getCompany().equals(ticker))
                return true;
        }
        return false;
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
