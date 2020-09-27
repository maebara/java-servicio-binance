package com.sergio.binance.service;

import com.sergio.binance.entity.ActualUSDTSymbol;
import com.sergio.binance.entity.ExchangeInfo;
import com.sergio.binance.entity.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InitApplication {
    @Autowired
    private RestHttpCallerService restHttpCallerService;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        ResponseEntity<ExchangeInfo> response = restHttpCallerService
                .toEndpoint("https://api.binance.com/api/v3/exchangeInfo")
                .method(HttpMethod.GET)
                .call(ExchangeInfo.class);

        List<Symbol> list = response.getBody().getSymbols()
                .stream()
                .filter(symbol -> symbol.getQuoteAsset().equalsIgnoreCase("USDT"))
                .collect(Collectors.toList());

        ActualUSDTSymbol.symbolList = list;
    }

}
