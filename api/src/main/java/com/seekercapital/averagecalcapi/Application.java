package com.seekercapital.averagecalcapi;

import com.seekercapital.averagecalcapi.dao.PriceRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@EnableReactiveMongoRepositories
public class Application {

    public static void main(String[] args) throws InterruptedException {
        new SpringApplicationBuilder(Application.class).run(args);
    }
}