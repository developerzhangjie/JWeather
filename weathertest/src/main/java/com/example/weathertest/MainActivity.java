package com.example.weathertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private String TAG = "TAG";
    private String encProName;
    private String encCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.bn);
        //        final String url = "http://apicloud.mob.com/v1/weather/query?province=北京&key=19c88142861e7&city=通州";
        String pro = "山东";
        String city = "青岛";
        try {
            encProName = URLEncoder.encode(pro, "UTF-8");
            encCityName = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //        final String url = "http://apicloud.mob.com/v1/weather/query?key=19c88142861e7&city=通州&province=北京";
        final String url = "http://apicloud.mob.com/v1/weather/query?key=19c88142861e7&city=" + encCityName + "&province=" + encProName;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "onResponse: " + response.toString());

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "onErrorResponse: ");
                            }
                        });
                        requestQueue.add(jsonObjectRequest);
                    }
                }).start();
            }
        });
    }
}
