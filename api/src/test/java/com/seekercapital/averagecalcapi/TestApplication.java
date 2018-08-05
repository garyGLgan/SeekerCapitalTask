package com.seekercapital.averagecalcapi;

import com.seekercapital.averagecalcapi.service.PriceService;
import com.seekercapital.averagecalcapi.service.PriceServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    @Bean
    public PriceService priceService(){
        return new PriceServiceImpl();
    }
}
