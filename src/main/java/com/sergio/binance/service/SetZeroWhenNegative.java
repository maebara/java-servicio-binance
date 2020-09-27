package com.sergio.binance.service;

import com.sergio.binance.entity.Cryptocurrency;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SetZeroWhenNegative implements CryptoStrategy {

    @Override
    public void calculate(Map<String, Cryptocurrency> lista, Map<String, Cryptocurrency> novedades) {
        novedades.forEach((s, cryptocurrency) -> {
            Cryptocurrency money = lista.get(s);
            double changeX = Double.valueOf(money.getPriceChangePercent());
            double changeY = Double.valueOf(cryptocurrency.getPriceChangePercent());
            double delta = changeY - changeX;
            if(delta < 0){
                money.setVariation(0);
            }else{
                double result = money.getVariation() + delta;
                money.setVariation(result);
                if(result > 1){
                    System.out.println("variacion=" + result + " | moneda=" + money.getSymbol());
                }
            }

            money.setPriceChangePercent(cryptocurrency.getPriceChangePercent());
            money.setPriceChange(cryptocurrency.getPriceChange());
        });
    }
}
