package com.sergio.binance.service;

import com.sergio.binance.entity.Cryptocurrency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Qualifier("Slope")
public class SlopeCalculation implements CryptoStrategy {

    @Override
    public void calculate(Map<String, Cryptocurrency> lista, Map<String, Cryptocurrency> novedades) {


    }

}
