package com.arron.service;

import com.aaron.grpc.robinhood.RobinhoodApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {
    @Value("${grpc.robinhood.ip}")
    private String robinhoodServerIp;

    @Value("${grpc.robinhood.port}")
    private int robinhoodServerPort;
    public RobinhoodApi getRobinhoodService(){
        RobinhoodApi robinhoodApi = new RobinhoodApi();
        robinhoodApi.setup(robinhoodServerIp,robinhoodServerPort);
        return robinhoodApi;
    }
}
