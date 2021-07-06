package com.example.sudokusolver;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountriesFragment extends Fragment {

    ArrayList<countryData> countrydata=new ArrayList<>();
    ListView lvItems;
    listAdapter1 lvAdapter1;
    ViewFlipper vf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_countries, container, false);

        lvItems=view.findViewById(R.id.lv);

        vf=view.findViewById(R.id.vf);
        vf.setInAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.push_left_in));
        vf.setOutAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.push_left_out));
        vf.setFlipInterval(7000);
        vf.startFlipping();

        ProgressDialog pd=new ProgressDialog(getActivity());
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        pd.show();

        String url="https://coronavirus-19-api.herokuapp.com/countries";

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                if(response!=null)
                    pd.dismiss();

                String res="";
                for(int i=0;i<response.length();++i)
                {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String country=jsonObject.getString("country");
                        long cases=jsonObject.getLong("cases");
                        long deaths=jsonObject.getLong("deaths");
                        long recovered=jsonObject.getLong("recovered");
                        long active=jsonObject.getLong("active");
                        long tests=jsonObject.getLong("totalTests");
                        countrydata.add(new countryData(country+" ",""+active,""+recovered,
                                ""+deaths,""+cases,"(Tests: "+tests+")"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                lvAdapter1=new listAdapter1(getActivity(),R.layout.listitem1,countrydata);
                lvItems.setAdapter(lvAdapter1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);

        return view;
    }

}