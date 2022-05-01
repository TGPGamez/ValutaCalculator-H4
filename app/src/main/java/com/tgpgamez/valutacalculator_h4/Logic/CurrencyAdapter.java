package com.tgpgamez.valutacalculator_h4.Logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tgpgamez.valutacalculator_h4.R;

import java.util.ArrayList;

public class CurrencyAdapter extends ArrayAdapter<ConvertedCurrency> {
    public CurrencyAdapter(Context context, ArrayList<ConvertedCurrency> currencies) {
        super(context, 0, currencies);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Get data item for this position
        ConvertedCurrency convertedCurrency = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_list, parent, false);
        }

        //Lookup view for data population
        TextView currencyName = (TextView) convertView.findViewById(R.id.currency_name);
        TextView currencyAmount = (TextView) convertView.findViewById(R.id.currency_calculated);

        //Populate the data into the template view using the data object
        currencyName.setText(convertedCurrency.getName());
        currencyAmount.setText(convertedCurrency.getCalculatedAmount());

        //return the completed view to render on screen
        return convertView;
    }
}
