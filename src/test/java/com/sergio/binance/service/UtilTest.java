package com.sergio.binance.service;

import org.junit.Test;

public class UtilTest {


    public void calculateDelta() {
        double a = Double.valueOf("4.12");
        double b = Double.valueOf("-2");

        double delta = b - a;
        System.out.println(delta);
    }
}
