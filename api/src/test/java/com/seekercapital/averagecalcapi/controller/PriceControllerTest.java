package com.seekercapital.averagecalcapi.controller;

import com.seekercapital.averagecalcapi.TestApplication;
import com.seekercapital.averagecalcapi.dao.PriceRepository;
import com.seekercapital.averagecalcapi.mode.Price;
import com.seekercapital.averagecalcapi.service.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebFluxTest(PriceController.class)
@ContextConfiguration(classes = TestApplication.class)
public class PriceControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PriceRepository repository;

    @Test
    public void badRequestWhenInputIsNotANumber() {
        client.get().uri("/average-price/a").accept(MediaType.ALL).exchange().expectStatus().isBadRequest();
    }

    @Test
    public void badRequestWhenInputIsNull(){
        client.get().uri("/average-price/").accept(MediaType.ALL).exchange().expectStatus().isNotFound();
    }

    @Test
    public void intervalErrorWhenInputLessThanZero(){
        client.get().uri("/average-price/-4").accept(MediaType.ALL).exchange().expectStatus().is5xxServerError();
    }

    @Test
    public void returnZeroWhenInputZero(){
        client.get().uri("/average-price/0").accept(MediaType.ALL).exchange().expectStatus().isOk().expectBody().equals("0.0");
    }

    @Test
    public void returnValueWhenInputGreatThanZero(){
        BDDMockito.given(repository.findAllByOrderByCreateAt(any()))
                .willReturn(Flux.just(new Price(1,12345), new Price(2,123456)));
        client.get().uri("/average-price/2").accept(MediaType.ALL).exchange().expectStatus().isOk().expectBody().equals("1.5");
    }

}