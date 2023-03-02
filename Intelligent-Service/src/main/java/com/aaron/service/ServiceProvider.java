package com.aaron.service;

import com.aaron.grpc.intelligent.IntelligentApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {
    @Value("${grpc.intelligent.ip}")
    private String intelligentServerIp;

    @Value("${grpc.intelligent.port}")
    private int intelligentServerPort;

    public IntelligentApi getIntelligentService(){
        IntelligentApi intelligentApi = new IntelligentApi();
        intelligentApi.setup(intelligentServerIp,intelligentServerPort);
        return intelligentApi;
    }
}
