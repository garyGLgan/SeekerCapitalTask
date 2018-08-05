package com.seekercapital.averagecalcapi.service;

import com.seekercapital.averagecalcapi.mode.Price;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface PriceService {

    Mono<Double> averageOfLastX(int x);

    Mono<Price> savePrice(Price price);
}
