package com.seekercapital.averagecalcapi.receiver;

import com.netflix.discovery.converters.Auto;
import com.seekercapital.averagecalcapi.dao.PriceRepository;
import com.seekercapital.averagecalcapi.mode.Price;
import com.seekercapital.averagecalcapi.service.PriceService;
import com.seekercapital.averagecalcapi.service.PriceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.text.ParseException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
public class MessageReceiverTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public MessageReceiver messageReceiver(){
            return new MessageReceiver();
        }
    }

    @Autowired
    private MessageReceiver receiver;

    @SpyBean
    PriceServiceImpl service;

    @MockBean
    PriceRepository repository;

    @Test(expected = NumberFormatException.class)
    public void throwExceptionWhenMessageFormatWrong(){

        receiver.receive("111,bbb");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwException2WhenMessageFormatWrong(){

        receiver.receive("111");
    }

    @Test
    public void savePriceWhenReceiveAMessage(){
        doReturn(Mono.just(new Price(1, 12345)))
                .when(service).savePrice(Mockito.any(Price.class));
        receiver.receive("1,12345");
        Mockito.verify(service).savePrice(Mockito.eq(new Price(1,12345)));
    }
}