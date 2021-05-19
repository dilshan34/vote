package com.example.myapplication.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText txtpassword;
    ImageButton btnlogin;
    String username,password,uname,nic;
    private ProgressDialog loadingbar;
    private Toolbar mtoolbar;
    TextView txtcreateAccount;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtpassword = (EditText) findViewById(R.id.txtpassword);
        btnlogin = (ImageButton) findViewById(R.id.btnlogin);
        txtcreateAccount = (TextView) findViewById(R.id.txtcreateAccount);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(txtpassword.getText().toString())){
                    Toast.makeText(login.this,"Enter the NIC number",Toast.LENGTH_SHORT).show();

                }

                else{
                    userLogin();
                }





            }
        });

        txtcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this,signup.class);
                startActivity(intent);

            }
        });
    }

    public void userLogin(){



        password = txtpassword.getText().toString();

        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.login_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("response", "onResponse: "+response );
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String code=jsonObject.getString("code");





                            Log.e("Response", "onResponse: "+code );

                            if(code.equals("false"))
                            {
                                Log.e("response", "if " );
                                Toast.makeText(login.this,"Login Error",Toast.LENGTH_SHORT).show();


                            }
                            else {
                                Log.e("response", "else " );
                                nic=jsonObject.getString("nic");


                                Toast.makeText(login.this,"Login Success",Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(login.this, createVote.class);
                                intent.putExtra("nic",nic);
                                startActivity(intent);


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams()  {
                Map<String,String> params = new HashMap<String,String>();

                params.put("password",password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

}