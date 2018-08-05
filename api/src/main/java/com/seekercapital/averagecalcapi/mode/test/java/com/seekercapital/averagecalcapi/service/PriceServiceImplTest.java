package com.seekercapital.averagecalcapi.service;

import com.seekercapital.averagecalcapi.dao.PriceRepository;
import com.seekercapital.averagecalcapi.mode.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class PriceServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public PriceService priceService() {
            return new PriceServiceImpl();
        }
    }

    @Autowired
    private PriceService service;

    @MockBean
    private PriceRepository repository;

    @Test
    public void returnZeroWhenInputZero(){
        assertEquals(service.averageOfLastX(0).block(), new Double(0.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionWhenInputNegative(){
        service.averageOfLastX(-3).block();
    }

    @Test
    public void returnValueWhenInputNormal(){
        BDDMockito.given(repository.findAllByOrderByCreateAt(any(Pageable.class)))
                .willReturn(Flux.just(new Price(1,12345), new Price(2,123456)));
        assertEquals(service.averageOfLastX(10).block(),new Double(1.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExcptionWhenRepositoryThrowException(){
        BDDMockito.given(repository.insert(any(Price.class)))
                .willReturn(Mono.error(new IllegalArgumentException()));
        service.savePrice(new Price(1, 12345)).block();
    }

    @Test
    public void returnPriceWhenSaveIt(){
        BDDMockito.given(repository.insert(any(Price.class)))
                .willReturn(Mono.just(new Price(1,12345)));
        Price p = service.savePrice(new Price(1, 12345)).block();

        assertEquals(p.getPrice(), 1);
        assertEquals(p.getCreateAt(), 12345);

    }
}