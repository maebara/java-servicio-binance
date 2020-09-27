package com.sergio.binance.service;

import com.sergio.binance.entity.Cryptocurrencies;
import com.sergio.binance.entity.Cryptocurrency;
import com.sergio.binance.entity.ExchangeInfo;
import com.sergio.binance.entity.Symbol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestHttpCallerServiceTest {
    @Autowired
    private RestHttpCallerService restHttpCallerService;
    @Test
    public void callGet() {
        String endpoint = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<String> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(String.class);

        //System.out.println(response.getBody());
        assertEquals(true, response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void callBinanceInfo() {
        String endpoint = "https://api.binance.com/api/v3/exchangeInfo";
        ResponseEntity<ExchangeInfo> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(ExchangeInfo.class);

        List<Symbol> list = response.getBody().getSymbols()
                .stream()
                .filter(symbol -> symbol.getQuoteAsset().equalsIgnoreCase("USDT"))
                .collect(Collectors.toList());
        System.out.println(list.size());
        assertEquals(true, response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void callBinance() {
        String endpoint = "https://api.binance.com/api/v3/ticker/24hr?symbol=LINKUSDT";
        ResponseEntity<Cryptocurrency> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(Cryptocurrency.class);

        System.out.println(response.getBody());
        assertEquals(true, response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void callBinance24hrTicker() {
        String endpoint = "https://api.binance.com/api/v3/ticker/24hr";
        ResponseEntity<List<Cryptocurrency>> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(new ParameterizedTypeReference<List<Cryptocurrency>>() {
                });

        Map<String, Cryptocurrency> lista = response.getBody().stream()
                .filter(c -> c.getSymbol().endsWith("USDT"))
                .collect(Collectors.toMap(c -> c.getSymbol(), c -> c));

        lista.forEach((s, cryptocurrency) -> {
            System.out.println(s + " " + cryptocurrency);
        });


        assertEquals(true, response.getStatusCode().is2xxSuccessful());
    }


}
