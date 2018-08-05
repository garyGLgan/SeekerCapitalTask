package com.seekercapital.averagecalcpublisher;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableScheduling
@EnableRabbit
public class PublisherApplication {

    @Bean
    Queue queue() {
        return new Queue("price_queue", false);
    }

    public static void main(String[] args){
        SpringApplication.run(PublisherApplication.class, args);
    }
}