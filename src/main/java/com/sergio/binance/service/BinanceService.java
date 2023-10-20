package com.sergio.binance.service;

import com.sergio.binance.dto.GetTopChangeDTO;
import com.sergio.binance.dto.GetTopChangeWithTimeDTO;
import com.sergio.binance.entity.BinanceResponse;
import com.sergio.binance.entity.Cryptocurrency;
import com.sergio.binance.util.TimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BinanceService {
    @Autowired
    private RestHttpCallerService restHttpCallerService;
    private Map<String, Cryptocurrency> lista5m;
    private Map<String, Cryptocurrency> lista10m;
    private Map<String, Cryptocurrency> lista30m;
    private Map<String, Cryptocurrency> lista1hr;
    private Map<String, Cryptocurrency> lista3hr;
    private Map<String, Cryptocurrency> lista5hr;
    private Map<String, Cryptocurrency> lista12hr;
    private Map<String, Cryptocurrency> lista24hr;
    private GetTopChangeWithTimeDTO dto5m;
    private GetTopChangeWithTimeDTO dto10m;
    private GetTopChangeWithTimeDTO dto30m;
    private GetTopChangeWithTimeDTO dto1hr;
    private GetTopChangeWithTimeDTO dto3hr;
    private GetTopChangeWithTimeDTO dto5hr;
    private GetTopChangeWithTimeDTO dto12hr;
    private GetTopChangeWithTimeDTO dto24hr;

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

        if (this.lista10m == null) {
            this.lista10m = lista;

        } else {
            strategy.calculate(this.lista10m, lista);
        }
        BinanceResponse binanceResponse = new BinanceResponse();
        binanceResponse.setLista(this.lista10m.values());
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


        switch (time) {
            case T5MIN:
                if (this.lista5m == null) {
                    list = null;
                    this.dto5m = new GetTopChangeWithTimeDTO();
                    this.dto5m.setId(-1);
                    this.dto5m.setDto(null);
                    this.lista5m = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista5m, lista);
                    this.dto5m.setDto(list);
                }
                break;
            case T10MIN:
                if (this.lista10m == null) {
                    list = null;
                    this.dto10m = new GetTopChangeWithTimeDTO();
                    this.dto10m.setId(0);
                    this.dto10m.setDto(null);
                    this.lista10m = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista10m, lista);
                    this.dto10m.setDto(list);
                }
                break;
            case T30MIN:
                if (this.lista30m == null) {
                    list = null;
                    this.dto30m = new GetTopChangeWithTimeDTO();
                    this.dto30m.setId(1);
                    this.dto30m.setDto(null);
                    this.lista30m = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista30m, lista);
                    this.dto30m.setDto(list);
                }
                break;
            case T1HR:
                if (this.lista1hr == null) {
                    this.dto1hr = new GetTopChangeWithTimeDTO();
                    this.dto1hr.setId(2);
                    this.dto1hr.setDto(null);
                    list = null;
                    this.lista1hr = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista1hr, lista);
                    this.dto1hr.setDto(list);
                }
                break;
            case T3HR:
                if (this.lista3hr == null) {
                    list = null;
                    this.dto3hr = new GetTopChangeWithTimeDTO();
                    this.dto3hr.setId(3);
                    this.dto3hr.setDto(null);
                    this.lista3hr = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista3hr, lista);
                    this.dto3hr.setDto(list);
                }
                break;
            case T5HR:
                if (this.lista5hr == null) {
                    list = null;
                    this.dto5hr = new GetTopChangeWithTimeDTO();
                    this.dto5hr.setId(4);
                    this.dto5hr.setDto(null);
                    this.lista5hr = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista5hr, lista);
                    this.dto5hr.setDto(list);
                }
                break;
            case T12HR:
                if (this.lista12hr == null) {
                    list = null;
                    this.dto12hr = new GetTopChangeWithTimeDTO();
                    this.dto12hr.setId(5);
                    this.dto12hr.setDto(null);
                    this.lista12hr = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista12hr, lista);
                    this.dto12hr.setDto(list);
                }
                break;
            case T24HR:
                if (this.lista24hr == null) {
                    list = null;
                    this.dto24hr = new GetTopChangeWithTimeDTO();
                    this.dto24hr.setId(6);
                    this.dto24hr.setDto(null);
                    this.lista24hr = deepCopy(lista);
                } else {
                    list = slopeCalculation.calculate(this.lista24hr, lista);
                    this.dto24hr.setDto(list);
                }
                break;
            default:
                break;
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

    public List<GetTopChangeWithTimeDTO> getAll() {
        List<GetTopChangeWithTimeDTO> list = new ArrayList<>();
        list.add(dto5m);
        list.add(dto10m);
        list.add(dto30m);
        list.add(dto1hr);
        list.add(dto3hr);
        list.add(dto5hr);
        list.add(dto12hr);
        list.add(dto24hr);
        return list;
    }
}
