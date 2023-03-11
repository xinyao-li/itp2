package com.aaron;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    @Value("${local.address}")
    private String localAddress;
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("stock", r -> r.path("/stock/**")
                        .uri("http://"+localAddress+":8082/stock/login"))
                .route("intelligent", r -> r.path("/intelligent/**")
                        .uri("http://"+localAddress+":8084/intelligent"))
                .build();
    }

}