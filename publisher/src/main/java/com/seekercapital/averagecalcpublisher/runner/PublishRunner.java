package com.seekercapital.averagecalcpublisher.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PublishRunner {

    @Autowired
    private RabbitTemplate template;

    private SecureRandom r = new SecureRandom();

    private Logger logger = LoggerFactory.getLogger(PublishRunner.class);

    @Scheduled(fixedRate = 3000)
    public void publishPrice(){
        int price = r.nextInt(1000000);

        StringBuilder sb = new StringBuilder();
        String message = sb.append(price).append(',').append(System.currentTimeMillis()).toString();
        logger.info(">>>>>>>> publish price message"+message);
        template.convertAndSend("price_queue", message);
    }
}
