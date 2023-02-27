package com.arron.controller;

import com.arron.response.LoginResponse;
import com.arron.response.Response;
import com.arron.service.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/ita")
public class StockController {

    private static Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private ServiceProvider serviceProvider;
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
    @RequestMapping(value="/quote",method = RequestMethod.GET)
    @ResponseBody
    public double quote(@RequestParam(value="ticker",required = false)String ticker) {
        Double price = null;
        try {
            price = serviceProvider.getRobinhoodService().quote(ticker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(price);
        return price;
    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    @ResponseBody
    public double apiTest(@RequestParam(value="amount",required = false)double amount) {
        Double result = null;
        try {
            result = serviceProvider.getRobinhoodService().apiTest(amount);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
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
