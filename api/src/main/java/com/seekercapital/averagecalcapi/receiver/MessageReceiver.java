package com.seekercapital.averagecalcapi.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.seekercapital.averagecalcapi.mode.Price;
import com.seekercapital.averagecalcapi.service.PriceService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "price_queue")
public class MessageReceiver {

    private Logger log = LoggerFactory.getLogger(MessageReceiver.class);

    @Autowired
    private PriceService service;

    @RabbitHandler
    public void receive(String msg){
        log.info("<<<<<<<< receiver a mesage: "+msg);
        String[] msgInfos = msg.split(",");
        Price price = new Price(Integer.valueOf(msgInfos[0]), Long.valueOf(msgInfos[1]));
        service.savePrice(price).subscribe();
    }
}
