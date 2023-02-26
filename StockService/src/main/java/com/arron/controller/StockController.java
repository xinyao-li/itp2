package com.arron.controller;

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
        return Response.SUCCESS;
    }
    @RequestMapping(value="/quote",method = RequestMethod.GET)
    @ResponseBody
    public Response quote(@RequestParam("ticker")String ticker) {
        try {
            serviceProvider.getRobinhoodService().quote(ticker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.SUCCESS;
    }
    @RequestMapping(value="/test",method = RequestMethod.POST)
    @ResponseBody
    public Response apiTest(@RequestParam(value="amount",required = false)double amount) {
        try {
            serviceProvider.getRobinhoodService().apiTest(amount);
        }catch(Exception e){
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        return Response.SUCCESS;
    }

}
