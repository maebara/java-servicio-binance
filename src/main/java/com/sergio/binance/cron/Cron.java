package com.sergio.binance.cron;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.service.BinanceService;
import com.sergio.binance.util.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.TreeSet;

@Configuration
@EnableScheduling
public class Cron {
    @Autowired
    private BinanceService binanceService;

    @Scheduled(cron = "*/30 * * * * *")
    public void scheduleFixedRateTask() {

        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T30MIN);
        if(dto == null) return;
        System.out.println("~~~~~~~~~~~~~~~~30 SECONDS~~~~~~~~~~~~~~~~~~~~+");
        dto.forEach(getTopChangeDTO -> {
            System.out.println(getTopChangeDTO);
        });
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduleFixedRateTask2() {

        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T10MIN);
        if(dto == null) return;
        System.out.println("~~~~~~~~~~~~~~~~~~~ 10 SECONDS ~~~~~~~~~~~~~~~~~+");
        dto.forEach(getTopChangeDTO -> {
            System.out.println(getTopChangeDTO);
        });
        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~+");
    }


}
