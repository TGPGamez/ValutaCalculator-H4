package com.tgpgamez.valutacalculator_h4.Logic;

import java.util.ArrayList;
import java.util.List;

public class CurrencyCalculator {

    /**
     * Method calculates currencies
     * @param base_name Which currency name is currently selected example: DKK or EUR
     * @param amount The amount we wish to calculate
     * @param data List of all the currencies that need to be calculated
     * @return List of converted currencies
     */
    public ArrayList<ConvertedCurrency> calculate(String base_name, double amount, List<Currency> data) {
        for (Currency currency: data) {
            if (currency.getName() == base_name) {
                amount = amount / currency.getRate();
            }
        }
        ArrayList<ConvertedCurrency> values = new ArrayList<ConvertedCurrency>();

        for (Currency currency: data) {
            values.add(new ConvertedCurrency(currency.getName(), String.format("%.2f", amount * currency.getRate())));
        }
        return values;
    }
}
