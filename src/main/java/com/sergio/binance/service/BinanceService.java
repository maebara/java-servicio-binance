package com.sergio.binance.service;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.entity.BinanceResponse;
import com.sergio.binance.entity.Cryptocurrency;
import com.sergio.binance.util.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class BinanceService {
    @Autowired
    private RestHttpCallerService restHttpCallerService;
    private Map<String, Cryptocurrency> lista;
    private Map<String, Cryptocurrency> lista10m;
    private Map<String, Cryptocurrency> lista30m;
    private Map<String, Cryptocurrency> lista1hr;
    private Map<String, Cryptocurrency> lista3hr;
    private Map<String, Cryptocurrency> lista5hr;
    private Map<String, Cryptocurrency> lista12hr;
    private Map<String, Cryptocurrency> lista24hr;

    @Autowired
    private CryptoStrategy strategy;
    @Autowired
    private SlopeCalculation slopeCalculation;

    public BinanceResponse getActualValues() {
        String endpoint = "https://api.binance.com/api/v3/ticker/24hr";

        ResponseEntity<List<Cryptocurrency>> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(new ParameterizedTypeReference<List<Cryptocurrency>>() {
                });

        Map<String, Cryptocurrency> lista = response.getBody().stream()
                .filter(c -> c.getSymbol().endsWith("USDT"))
                .collect(Collectors.toMap(c -> c.getSymbol(), c -> c));

        if (this.lista == null) {
            this.lista = lista;

        } else {
            strategy.calculate(this.lista, lista);
        }
        BinanceResponse binanceResponse = new BinanceResponse();
        binanceResponse.setLista(this.lista.values());
        binanceResponse.setNovedades(lista.values());
        return binanceResponse;
    }

    public synchronized TreeSet<GetTopChangeDTO> getActualValuesTimer(TimeType time) {
        String endpoint = "https://api.binance.com/api/v3/ticker/24hr";

        ResponseEntity<List<Cryptocurrency>> response = restHttpCallerService.toEndpoint(endpoint)
                .method(HttpMethod.GET)
                .call(new ParameterizedTypeReference<List<Cryptocurrency>>() {
                });

        Map<String, Cryptocurrency> lista = response.getBody().stream()
                .filter(c -> c.getSymbol().endsWith("USDT"))
                .collect(Collectors.toMap(c -> c.getSymbol(), c -> c));

        TreeSet<GetTopChangeDTO> list = null;

        if (this.lista == null) {
            this.lista = lista;
            this.lista10m = deepCopy(lista);
            this.lista30m = deepCopy(lista);
        } else {
            switch (time) {
                case T10MIN:
                    list = slopeCalculation.calculate(this.lista10m, lista);
                    break;
                case T30MIN:
                    list = slopeCalculation.calculate(this.lista30m, lista);
                    break;
                case T1HR:
                    list = slopeCalculation.calculate(this.lista1hr, lista);
                    break;
                case T3HR:
                    list = slopeCalculation.calculate(this.lista3hr, lista);
                    break;
                case T5HR:
                    list = slopeCalculation.calculate(this.lista5hr, lista);
                    break;
                case T12HR:
                    list = slopeCalculation.calculate(this.lista12hr, lista);
                    break;
                case T24HR:
                    list = slopeCalculation.calculate(this.lista24hr, lista);
                    break;
                default:
                    break;
            }
        }
        return list;
    }

    private Map<String, Cryptocurrency> deepCopy(Map<String, Cryptocurrency> lista) {
        Map<String, Cryptocurrency> map = new HashMap<>();
        lista.forEach((s, cryptocurrency) -> {
            Cryptocurrency c2 = new Cryptocurrency();
            c2.setSymbol(cryptocurrency.getSymbol());
            c2.setVariation(cryptocurrency.getVariation());
            c2.setLastPrice(cryptocurrency.getLastPrice());
            c2.setPriceChange(cryptocurrency.getPriceChange());
            c2.setPriceChangePercent(cryptocurrency.getPriceChangePercent());
            map.put(s, c2);
        });
        return map;
    }

}
