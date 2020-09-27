package com.sergio.binance.service;

import com.sergio.binance.entity.Cryptocurrency;

import java.util.Map;

public interface CryptoStrategy {

    void calculate(Map<String, Cryptocurrency> lista, Map<String, Cryptocurrency> novedades);
}
