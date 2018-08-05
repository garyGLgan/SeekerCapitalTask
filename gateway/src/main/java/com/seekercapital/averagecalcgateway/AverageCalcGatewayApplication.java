package com.seekercapital.averagecalcgateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AverageCalcGatewayApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder(AverageCalcGatewayApplication.class).run(args);
    }
}
