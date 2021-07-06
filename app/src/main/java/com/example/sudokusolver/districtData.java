package com.example.sudokusolver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class districtData extends AppCompatActivity {

    TextView tv;
    ArrayList<districtWiseData> districtwisedata=new ArrayList<>();
    ListView lvItems;
    listAdapter2 lvAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_data);

        lvItems=findViewById(R.id.lv);

        tv=findViewById(R.id.tv);
        String stateName=getIntent().getExtras().getString("state");
        tv.setText(stateName);

        ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        pd.show();

        String url="https://api.covid19india.org/v2/state_district_wise.json";

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,url, null, new Response.Listener<JSONArray>(){
            @Override
            public void onResponse(JSONArray response) {

                if(response!=null)
                    pd.dismiss();

                for(int i=1;i<response.length();++i) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String state = jsonObject.getString("state");
                        if (state.equals(stateName)) {
                            JSONArray districts = jsonObject.getJSONArray("districtData");
                            for (int j = 0; j < districts.length(); ++j) {
                                JSONObject district = districts.getJSONObject(j);
                                String districtName = district.getString("district");
                                long active = district.getLong("active");
                                long recovered = district.getLong("recovered");
                                long deceased = district.getLong("deceased");
                                long confirmed = district.getLong("confirmed");
                                String notes = district.getString("notes");
                                districtwisedata.add(new districtWiseData(districtName,""+active,""+recovered,
                                        ""+deceased,""+confirmed,""+notes));
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                lvAdapter2=new listAdapter2(districtData.this,R.layout.listitem1,districtwisedata);
                lvItems.setAdapter(lvAdapter2);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}