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

public class listAdapter extends ArrayAdapter<statesData> {

    Activity context;
    int resource;
    ArrayList<statesData> statesdata;

    public listAdapter(@NonNull Activity context, int resource, ArrayList<statesData> statesdata) {
        super(context, resource, statesdata);
        this.context = context;
        this.resource = resource;
        this.statesdata = statesdata;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.listitem,null,true);

        TextView tvState=view.findViewById(R.id.state);
        TextView tvActive=view.findViewById(R.id.active);
        TextView tvRecovered=view.findViewById(R.id.recovered);
        TextView tvDeceased=view.findViewById(R.id.deceased);
        TextView tvTotal=view.findViewById(R.id.total);

        statesData states=statesdata.get(position);
        tvState.setText(states.getState());
        tvActive.setText(states.getActive());
        tvDeceased.setText(states.getDeceased());
        tvRecovered.setText(states.getRecovered());
        tvTotal.setText(states.getTotal());
        return view;
    }
}
