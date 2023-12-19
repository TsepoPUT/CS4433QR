package com.example.cs4433qr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    String ServerUrl ="http://192.168.43.254/insert/insertion.php";
    Button RegButton;
    TextInputEditText Asset_ID, Asset_Name, Asset_Holder, Asset_HolderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegButton = findViewById(R.id.GenButton);
        Asset_Holder = findViewById(R.id.NAMEinput);
        Asset_HolderID = findViewById(R.id.AssetHolderID);
        Asset_ID = findViewById(R.id.AssetID);
        Asset_Name = findViewById(R.id.ANAMEinput);


        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Aname = Asset_Name.getText().toString();
                String Aid =Asset_ID.getText().toString();
                String Hname = Asset_Holder.getText().toString();
                String Hid= Asset_HolderID.getText().toString();


                //set up request
                StringRequest request = new StringRequest(Request.Method.POST, ServerUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // handle the response
                        Intent intent = new Intent(Register.this,GenerateActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Handle error
                        Toast.makeText(Register.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>param = new HashMap<>();
                        param.put("Aname",Aname);
                        param.put("Aid",Aid);
                        param.put("Hname",Hname);
                        param.put("Hid",Hid);

                        return param;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
                requestQueue.add(request);
            }
        });

    }
}