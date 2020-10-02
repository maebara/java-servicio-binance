package com.sergio.binance.entity;

import lombok.Data;

@Data
public class Cryptocurrency {
    private String symbol;
    private String priceChange;
    private String priceChangePercent;
    private String lastPrice;
    private double variation;
}
