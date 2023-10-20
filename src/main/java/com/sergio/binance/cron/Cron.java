package com.sergio.binance.cron;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.service.BinanceService;
import com.sergio.binance.util.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.TreeSet;

@Configuration
@EnableScheduling
public class Cron {
    @Autowired
    private BinanceService binanceService;
    @Autowired
    private SimpMessagingTemplate template;


    @Scheduled(cron = "0 */5 * * * *")
    public void send5Min() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T5MIN);
        if (dto == null) return;
        template.convertAndSend("/topic/user/5Min", dto);
    }

    @Scheduled(cron = "0 */10 * * * *")
    public void send10Min() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T10MIN);
        if (dto == null) return;
        template.convertAndSend("/topic/user/10Min", dto);
    }


    @Scheduled(cron = "0 */30 * * * *")
    public void send30Min() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T30MIN);
        if (dto == null) return;
        template.convertAndSend("/topic/user/30Min", dto);
    }

    @Scheduled(cron = "0 0 */1 * * *")
    public void send1Hr() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T1HR);
        if (dto == null) return;
        template.convertAndSend("/topic/user/1Hr", dto);
    }

    @Scheduled(cron = "0 0 */3 * * *")
    public void send3Hr() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T3HR);
        if (dto == null) return;
        template.convertAndSend("/topic/user/3Hr", dto);
    }

    @Scheduled(cron = "0 0 */5 * * *")
    public void send5Hr() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T5HR);
        if (dto == null) return;
        template.convertAndSend("/topic/user/5Hr", dto);
    }

    @Scheduled(cron = "0 0 */12 * * *")
    public void send12Hr() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T12HR);
        if (dto == null) return;
        template.convertAndSend("/topic/user/12Hr", dto);
    }

    @Scheduled(cron = "0 0 0 */1 * *")
    public void send24Hr() {
        TreeSet<GetTopChangeDTO> dto = binanceService.getActualValuesTimer(TimeType.T24HR);
        if (dto == null) return;
        template.convertAndSend("/topic/user/24Hr", dto);
    }







}
