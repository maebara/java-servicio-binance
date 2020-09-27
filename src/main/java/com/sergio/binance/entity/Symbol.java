package com.sergio.binance.entity;

import lombok.Data;

@Data
public class Symbol {
    private String symbol;
    private String status;
    private String baseAsset;
    private String quoteAsset;
}
