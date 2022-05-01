package com.tgpgamez.valutacalculator_h4.Logic;

import java.util.ArrayList;
import java.util.List;

public interface CurrencyListener {
    /**
     * Method to call when there is a change in the bases
     * @param bases List of new bases
     */
    public void onBasesChange(List<String> bases);

    /**
     * Method to call when there is a change in the currencies
     * @param currencies List of new currencies
     */
    public void onCurrenciesChange(List<Currency> currencies);
}