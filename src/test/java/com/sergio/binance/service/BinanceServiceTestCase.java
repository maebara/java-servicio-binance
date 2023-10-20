package com.sergio.binance.service;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.entity.BinanceResponse;
import com.sergio.binance.util.TimeType;
import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class BinanceServiceTestCase {
    @Autowired
    private BinanceService binanceService;

    public void binanceServiceCalculate() throws Exception {
        binanceService.getActualValues();
        for (int i = 0; i < 60; i++) {
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



    public void binanceServiceCalculateTimer() throws Exception {
        int timer = 10000;
        binanceService.getActualValuesTimer(TimeType.T10MIN);
        for (int i = 0; i < 60; i++) {
            Thread.sleep(timer);
            TreeSet<GetTopChangeDTO> finalList = binanceService.getActualValuesTimer(TimeType.T10MIN);
            if(finalList != null) {
                finalList.forEach(getTopChangeDTO -> {
                    System.out.println(getTopChangeDTO);
                });
                System.out.println("***************************");
            }else
            {
                System.out.println("null");
            }
        }
    }
}




