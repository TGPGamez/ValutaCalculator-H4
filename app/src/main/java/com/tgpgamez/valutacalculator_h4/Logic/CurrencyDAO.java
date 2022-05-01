package com.tgpgamez.valutacalculator_h4.Logic;

import java.util.List;

public interface CurrencyDAO {
    public List<Currency> getRates(String base_name);

}
