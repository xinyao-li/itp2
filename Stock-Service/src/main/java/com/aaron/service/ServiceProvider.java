package com.aaron.service;

import com.aaron.grpc.robinhood.RobinhoodApi;
import com.aaron.model.Stock;
import com.aaron.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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
