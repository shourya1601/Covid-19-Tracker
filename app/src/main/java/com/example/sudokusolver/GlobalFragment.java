package com.example.sudokusolver;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

public class GlobalFragment extends Fragment {

    TextView tvcases,tvrecovered,tvdeceased;
    long cases=0,deceased=0,recovered=0;
    ViewFlipper vf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_global, container, false);

        tvcases=view.findViewById(R.id.cases);
        tvdeceased=view.findViewById(R.id.deceased);
        tvrecovered=view.findViewById(R.id.recovered);

        vf=view.findViewById(R.id.vf);
        vf.setInAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.push_left_in));
        vf.setOutAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.push_left_out));
        vf.setFlipInterval(7000);
        vf.startFlipping();

        ProgressDialog pd=new ProgressDialog(getActivity());
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        pd.show();

        String url="https://coronavirus-19-api.herokuapp.com/all";

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response!=null)
                    pd.dismiss();

                try {
                    cases=response.getLong("cases");
                    deceased=response.getLong("deaths");
                    recovered=response.getLong("recovered");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tvcases.setText(""+cases);
                tvrecovered.setText(""+recovered);
                tvdeceased.setText(""+deceased);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

        return view;
    }
}