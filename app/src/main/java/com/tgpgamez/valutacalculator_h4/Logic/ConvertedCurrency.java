package com.tgpgamez.valutacalculator_h4.Logic;

public class ConvertedCurrency {
    private String name;

    public String getName() {
        return name;
    }

    private String calculatedAmount;

    public String getCalculatedAmount() {
        return calculatedAmount;
    }

    public ConvertedCurrency(String name, String calculatedAmount) {
        this.name = name;
        this.calculatedAmount = calculatedAmount;
    }
}
