package com.seekercapital.averagecalcapi.controller;

import com.seekercapital.averagecalcapi.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PriceController {

    @Autowired
    private PriceService service;

    @GetMapping("/average-price/{x}")
    public Mono<Double> averagePrice(@PathVariable("x") Integer x){
        if (x == null) return Mono.error( new IllegalArgumentException("please input a number"));
        return service.averageOfLastX(x);
    }
}
