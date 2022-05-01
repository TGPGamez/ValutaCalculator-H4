package com.tgpgamez.valutacalculator_h4.Logic;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApiCurrency implements CurrencyDAO {

    List<CurrencyListener> listeners;
    List<String> currency_bases;
    List<Currency> currencies;
    ApiHandler apiHandler;


    public ApiCurrency(RequestQueue queue) {
        this.listeners = new ArrayList<>();
        this.currencies = new ArrayList<>();
        this.currency_bases = new ArrayList<>();
        this.apiHandler = new ApiHandler(queue);
    }


    @Override
    public List<Currency> getRates(String base_name) {
        return this.currencies;
    }

    /**
     * Method is used to add listeners
     * @param listener Which listener we want to add
     */
    public void addListener(CurrencyListener listener) {
        listeners.add(listener);
    }

    /**
     * Method is used to remove listeners
     * @param listener Which listener we want removed
     */
    public void removeListener(CurrencyListener listener) {
        listeners.remove(listener);
    }

    /**
     * Method is used to update all our bases out from the requested url + extra parameters
     */
    public void updateBases() {
        apiHandler.requestJSONObject("latest/DKK", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    currency_bases.clear();
                    JSONObject array = response.getJSONObject("conversion_rates");
                    Iterator<String> keys = array.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        currency_bases.add(key);
                    }
                    notifyListenersOnBaseChange();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method is used to update our currencies
     * @param base_name Which currency we want data from example: DKK or EUR
     */
    public void updateCurrencies(String base_name) {
        apiHandler.requestJSONObject("latest/" + base_name, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Currency> data = new ArrayList<>();
                try {
                    JSONObject rate_Object = response.getJSONObject("conversion_rates");
                    Iterator<String> keys = rate_Object.keys();
                    while(keys.hasNext()) {
                        String key = keys.next();
                        Currency currency = new Currency(key, Double.parseDouble(rate_Object.get(key).toString()));
                        data.add(currency);
                    }
                    currencies = data;
                    notifyListenersOnCurrencyChange();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Notify all the listeners that there is a change in our bases
     */
    private void notifyListenersOnBaseChange() {
        for (CurrencyListener l : listeners) {
            l.onBasesChange(this.currency_bases);
        }
    }

    /**
     * Notify all the listeners that there is a change in our currencies
     */
    private void notifyListenersOnCurrencyChange() {
        for (CurrencyListener l : listeners) {
            l.onCurrenciesChange(this.currencies);
        }
    }
}
