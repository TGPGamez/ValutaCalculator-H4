package com.tgpgamez.valutacalculator_h4.Logic;

public class Currency {
    private String name;

    public String getName() {
        return name;
    }

    private double rate;

    public double getRate() {
        return rate;
    }

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }
}
