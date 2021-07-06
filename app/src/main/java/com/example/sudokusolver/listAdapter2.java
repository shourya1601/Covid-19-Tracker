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

public class listAdapter2 extends ArrayAdapter<districtWiseData> {

    Activity context;
    int resource;
    ArrayList<districtWiseData> districtwisedata;

    public listAdapter2(@NonNull Activity context, int resource, ArrayList<districtWiseData> districtwisedata) {
        super(context, resource, districtwisedata);
        this.context = context;
        this.resource = resource;
        this.districtwisedata = districtwisedata;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.listitem2,null,true);

        TextView tvDistrict=view.findViewById(R.id.district);
        TextView tvActive=view.findViewById(R.id.active);
        TextView tvRecovered=view.findViewById(R.id.recovered);
        TextView tvDeceased=view.findViewById(R.id.deceased);
        TextView tvConfirmed=view.findViewById(R.id.confirmed);
        TextView tvNotes=view.findViewById(R.id.notes);

        districtWiseData district=districtwisedata.get(position);
        tvDistrict.setText(district.getDistrict());
        tvActive.setText(district.getActive());
        tvDeceased.setText(district.getDeceased());
        tvRecovered.setText(district.getRecovered());
        tvConfirmed.setText(district.getConfirmed());
        tvNotes.setText(district.getNotes());
        return view;
    }
}
