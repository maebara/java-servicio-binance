package com.sergio.binance.entity;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeInfo {
    private List<Symbol> symbols;
}
