package com.seekercapital.averagecalcapi.service;

import com.seekercapital.averagecalcapi.dao.PriceRepository;
import com.seekercapital.averagecalcapi.mode.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    private Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

    @Override
    public Mono<Double> averageOfLastX(int x) {
        if (x < 0) return Mono.error(new IllegalArgumentException("Negative number"));
        if (x == 0) return  Mono.just(0.0);
        final Pageable page = PageRequest.of(0, x, new Sort(Sort.Direction.DESC, "createAt"));
        return priceRepository.findAllByOrderByCreateAt(page)
                .reduce(Pair. of(0, 0), (a, r) -> Pair.of(a.getFirst() + r.getPrice(), a.getSecond() + 1))
                .map( a -> a.getSecond() == 0? 0.0 : a.getFirst().doubleValue() / a.getSecond());
    }

    @Override
    public Mono<Price> savePrice(Price price) {
        log.info("save price to db -> "+price);
        return priceRepository.insert(price)
                .doOnError(t -> log.error("failed to save price entity", t))
                .doOnSuccess(a -> log.info("<<<<<<<< Price "+ a +"saved"));
    }
}
