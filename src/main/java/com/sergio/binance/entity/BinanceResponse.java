package com.sergio.binance.entity;

import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class BinanceResponse {
    private Collection<Cryptocurrency> lista;
    private Collection<Cryptocurrency> novedades;
}
