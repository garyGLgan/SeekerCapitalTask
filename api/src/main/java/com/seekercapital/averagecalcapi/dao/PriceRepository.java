package com.seekercapital.averagecalcapi.dao;

import com.seekercapital.averagecalcapi.mode.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PriceRepository extends ReactiveMongoRepository<Price, String>, ReactiveCrudRepository<Price, String> {

   Flux<Price> findAllByOrderByCreateAt(Pageable pageable);
}
