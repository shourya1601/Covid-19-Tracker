package com.example.sudokusolver;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IndiaFragment extends Fragment {

    TextView tvactive,tvrecovered,tvdeceased,tvconfirmed;
    Button btActive,btRecovered,btDeceased,btTotal;

    JSONArray jsonArray;
    int activeCases=0;
    int activeCasesNew=0;
    int recovered=0;
    int recoveredNew=0;
    int deceased=0;
    int deceasedNew=0;
    int totalCases=0;

    ArrayList<statesData> statedata=new ArrayList<>();
    ListView lvItems;
    listAdapter lvAdapter;

    BarChart barGraph;
    ArrayList<BarEntry> barActiveCases=new ArrayList<>();
    ArrayList<BarEntry> barRecoveredCases=new ArrayList<>();
    ArrayList<BarEntry> barDeceasedCases=new ArrayList<>();
    ArrayList<BarEntry> barTotalCases=new ArrayList<>();
    ArrayList<String> statesName=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_india, container, false);

        lvItems=view.findViewById(R.id.lv);

        tvactive=view.findViewById(R.id.active);
        tvrecovered=view.findViewById(R.id.recovered);
        tvdeceased=view.findViewById(R.id.deceased);
        tvconfirmed=view.findViewById(R.id.confirmed);

        btActive=view.findViewById(R.id.btAvtive);
        btRecovered=view.findViewById(R.id.btRecovered);
        btDeceased=view.findViewById(R.id.btDeceased);
        btTotal=view.findViewById(R.id.btTotalCases);

        barGraph=view.findViewById(R.id.bar);

        ProgressDialog pd=new ProgressDialog(getActivity());
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        pd.show();

        String url="https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true";

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response!=null)
                    pd.dismiss();

                try {
                    jsonArray=response.getJSONArray("regionData");
                    activeCases=response.getInt("activeCases");
                    activeCasesNew=response.getInt("activeCasesNew");
                    recovered=response.getInt("recovered");
                    recoveredNew=response.getInt("recoveredNew");
                    deceased=response.getInt("deaths");
                    deceasedNew=response.getInt("deathsNew");
                    totalCases=response.getInt("totalCases");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tvactive.setText("Active\n"+activeCases+"\n"+activeCasesNew);
                tvrecovered.setText("Recovered\n"+recovered+"\n"+recoveredNew);
                tvdeceased.setText("Deceased\n"+deceased+"\n"+deceasedNew);
                tvconfirmed.setText("Total Cases\n"+totalCases);

                for(int i=0;i<jsonArray.length();++i)
                {
                    JSONObject states = null;
                    String state="";
                    int activeCases=0;
                    int newInfected=0;
                    int recovered=0;
                    int newRecovered=0;
                    int deceased=0;
                    int newDeceased=0;
                    int totalInfected=0;

                    try {
                        states=jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        state=states.getString("region");
                        activeCases=states.getInt("activeCases");
                        newInfected=states.getInt("newInfected");
                        recovered=states.getInt("recovered");
                        newRecovered=states.getInt("newRecovered");
                        deceased=states.getInt("deceased");
                        newDeceased=states.getInt("newDeceased");
                        totalInfected=states.getInt("totalInfected");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //adding data to active cases graph
                    barActiveCases.add(new BarEntry((float)i,activeCases));
                    //adding data to recovered cases graph
                    barRecoveredCases.add(new BarEntry((float)i,recovered));
                    //adding data to deceased graph
                    barDeceasedCases.add(new BarEntry((float)i,deceased));
                    //adding data to total cases
                    barTotalCases.add(new BarEntry((float)i,totalInfected));
                    statesName.add(""+state);

                    //adding data to listview
                    statedata.add(new statesData(state,""+activeCases+"\n"+newInfected,""+recovered+"\n"+newRecovered,
                            ""+deceased+"\n"+newDeceased,""+totalInfected));

                    if(i==jsonArray.length()-1)
                    {
                        //drawing graph
                        drawBarGraph(barGraph,barActiveCases,"Statewise active cases");
                    }

                }
                lvAdapter=new listAdapter(getActivity(),R.layout.listitem,statedata);
                lvItems.setAdapter(lvAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        btActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBarGraph(barGraph,barActiveCases,"Statewise active cases");
            }
        });

        btRecovered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBarGraph(barGraph,barRecoveredCases,"Statewise recovered cases");
            }
        });

        btDeceased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBarGraph(barGraph,barDeceasedCases,"Statewise deceased cases");
            }
        });

        btTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawBarGraph(barGraph,barTotalCases,"Statewise total cases");
            }
        });

        return view;
    }

    void drawBarGraph(BarChart barChart,ArrayList<BarEntry> barEntry,String description)
    {
        BarDataSet bds=new BarDataSet(barEntry,"state");
        bds.setBarBorderColor(Color.BLACK);
        bds.setBarBorderWidth(1);
        BarData bd=new BarData(bds);
        bd.setBarWidth(0.5f);
        barChart.setData(bd);
        bds.setColors(ColorTemplate.COLORFUL_COLORS);
        barChart.getDescription().setText(description);
        barChart.setBackgroundColor(Color.rgb(255,204,255));
        barChart.setVisibleXRangeMaximum(10);
        barChart.setFitBars(true);
        barChart.setDoubleTapToZoomEnabled(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.invalidate();
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(statesName));//add labels
        barChart.getXAxis().setLabelRotationAngle(90);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int ind=(int)e.getX();
                float y=e.getY();
                getActivity().setTitle(""+statesName.get(ind)+":"+y);
                Toast.makeText(getActivity(),""+statesName.get(ind)+":"+y,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {
                //do nothing
            }
        });
    }

}