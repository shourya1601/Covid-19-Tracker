package com.example.sudokusolver;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class listAdapter1 extends ArrayAdapter<countryData> {

    Activity context;
    int resource;
    ArrayList<countryData> countrydata;

    public listAdapter1(@NonNull Activity context, int resource, ArrayList<countryData> countrydata) {
        super(context, resource, countrydata);
        this.context = context;
        this.resource = resource;
        this.countrydata = countrydata;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.listitem1,null,true);

        TextView tvCountry=view.findViewById(R.id.country);
        TextView tvCases=view.findViewById(R.id.cases);
        TextView tvActive=view.findViewById(R.id.active);
        TextView tvRecovered=view.findViewById(R.id.recovered);
        TextView tvDeceased=view.findViewById(R.id.deceased);
        TextView tvTests=view.findViewById(R.id.tests);

        countryData states=countrydata.get(position);
        tvCountry.setText(states.getCountry());
        tvCases.setText(states.getCases());
        tvActive.setText(states.getActive());
        tvDeceased.setText(states.getDeceased());
        tvRecovered.setText(states.getRecovered());
        tvTests.setText(states.getTests());
        return view;
    }
}
