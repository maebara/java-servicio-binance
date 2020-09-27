package com.sergio.binance.service;

import com.sergio.binance.entity.BinanceResponse;
import com.sergio.binance.entity.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BinanceService {
    @Autowired
    private RestHttpCallerService restHttpCallerService;
    private Map<String, Cryptocurrency> lista;
    @Qualifier("Slope")
    @Autowired
    private CryptoStrategy strategy;

    public BinanceResponse getActualValues(Boolean reset){
        String endpoint = "https://api.binance.com/api/v3/ticker/24hr";

        ResponseEntity<List<Cryptocurrency>> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(new ParameterizedTypeReference<List<Cryptocurrency>>() {
                });

        Map<String, Cryptocurrency> lista = response.getBody().stream()
                .filter(c -> c.getSymbol().endsWith("USDT"))
                .collect(Collectors.toMap(c -> c.getSymbol(), c -> c));

        if(this.lista == null){
           this.lista = lista;
        }else{
            strategy.calculate(this.lista, lista);
        }
        BinanceResponse binanceResponse = new BinanceResponse();
        binanceResponse.setLista(this.lista.values());
        binanceResponse.setNovedades(lista.values());
        return binanceResponse;
    }

}
