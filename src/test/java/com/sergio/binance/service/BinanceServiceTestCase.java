package com.sergio.binance.service;

import com.sergio.binance.entity.BinanceResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BinanceServiceTestCase {
    @Autowired
    private BinanceService binanceService;

    @Test
    public void binanceServiceCalculate() throws Exception{
        binanceService.getActualValues();
        for(int i = 0 ; i < 60; i++ ) {
            Thread.sleep(10000);
            binanceService.getActualValues();
        }

/*
        binanceResponse.getLista().stream().forEach(y -> {
            System.out.println(y);
        });

        binanceResponse.getNovedades().stream().forEach(x -> {
            System.out.println(x);
        });

        Thread.sleep(10000);
        BinanceResponse binanceResponse2 = binanceService.getActualValues();

        binanceResponse2.getLista().stream().forEach(y -> {
            System.out.println(y);
        });

        binanceResponse2.getNovedades().stream().forEach(x -> {
            System.out.println(x);
        });
        */

    }
}
