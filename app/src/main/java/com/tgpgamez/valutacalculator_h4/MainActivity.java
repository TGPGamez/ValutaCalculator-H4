package com.tgpgamez.valutacalculator_h4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;
import com.tgpgamez.valutacalculator_h4.Logic.ApiCurrency;
import com.tgpgamez.valutacalculator_h4.Logic.ConvertedCurrency;
import com.tgpgamez.valutacalculator_h4.Logic.Currency;
import com.tgpgamez.valutacalculator_h4.Logic.CurrencyAdapter;
import com.tgpgamez.valutacalculator_h4.Logic.CurrencyCalculator;
import com.tgpgamez.valutacalculator_h4.Logic.CurrencyListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApiCurrency apiCurrency;
    Spinner spinner;
    TextView currencyAmount;

    /**
     * A CurrencyListener that will stand for updating UI when we call update methods in our ApiCurrency
     */
    CurrencyListener currencyListener = new CurrencyListener() {

        @Override
        public void onBasesChange(List<String> bases) {
            /** Makes an default ArrayAdapter with the layout of a "simple_spinner_item" with all our bases */
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, bases);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            /** Sets the adapter */
            spinner.setAdapter(adapter);
        }

        @Override
        public void onCurrenciesChange(List<Currency> currencies) {
            /** Instances a new calculator */
            CurrencyCalculator calculator = new CurrencyCalculator();
            /** Finds our ListView */
            ListView listView = findViewById(R.id.currencyList);

            /** Checks that there is something in our TextView */
            if (currencyAmount.getText().length() > 0) {
                /** Calculates our Currency List into a ConvertedCurrency List with the Calculator */
                ArrayList<ConvertedCurrency> convertedCurrency = calculator.calculate(spinner.getSelectedItem().toString(),
                        Double.parseDouble(currencyAmount.getText().toString()), currencies);
                /** Creates an Adapter using our CurrencyAdapter with the convertedCurrency list*/
                CurrencyAdapter adapter = new CurrencyAdapter(MainActivity.this, convertedCurrency);

                /** Sets the adapter */
                listView.setAdapter(adapter);
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_currencies);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String newSelectedCurrencyName = (String) adapterView.getSelectedItem();
                apiCurrency.updateCurrencies(newSelectedCurrencyName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        currencyAmount = findViewById(R.id.input_currency);
        currencyAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apiCurrency.updateCurrencies(spinner.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        apiCurrency = new ApiCurrency(Volley.newRequestQueue(this));
        apiCurrency.addListener(this.currencyListener);
        apiCurrency.updateBases();


    }

}