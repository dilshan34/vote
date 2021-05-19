package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;
import com.example.myapplication.adapter.voteAdapter;
import com.example.myapplication.constant;
import com.example.myapplication.models.getsetVote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class createVote extends AppCompatActivity {

    voteAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public String candidate,candidateId;
    TextView candName;
    Button submitButton;
    String sessionId;
    ImageView viewResult;



    ArrayList<getsetVote> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        candName = (TextView) findViewById(R.id.candName);
        submitButton = (Button) findViewById(R.id.submitbtn);
        viewResult = (ImageView) findViewById(R.id.viewResult);

        getNames();

         sessionId = getIntent().getStringExtra("nic");


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkCustomer();
            }
        });

        viewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(createVote.this,viewVote.class);
                startActivity(intent);
            }
        });

    }

    public void getValue(){

        Log.e("New Value", "getValue: "+candidate );
        candName.setText(candidate);

    }

    public void checkCustomer(){


        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.checkUser_url,
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
                                Toast.makeText(createVote.this,"voted",Toast.LENGTH_SHORT).show();

                                uploadTask();

                            }
                            else {
                                Log.e("response", "else " );
                                Toast.makeText(createVote.this,"Already voted",Toast.LENGTH_SHORT).show();




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

                params.put("password",sessionId);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
    public void uploadTask()
    {
        Log.e("New Value uploadtask", "getValue: "+candidate );
        Log.e("candidate id", "getValue: "+candidateId );
        Log.e("New Value", "session: "+sessionId );



       // h1time = fromTime.getText().toString();




        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                constant.uploadTask_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("response", "onResponse: "+response );
                            JSONArray jsonArray = new JSONArray(response);


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
                params.put("nic",sessionId);
                params.put("can_id",candidateId);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    public void getNames(){

        arrayList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constant.vote_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArray = new JSONArray(response);
                            Log.e("ed", "onResponse: "+jsonArray );


                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");

                                Log.e("TAG", "task : "+name );




                                getsetVote task = new getsetVote(id,name);
                                arrayList.add(task);


                            }
                            adapter = new voteAdapter(arrayList, createVote.this);
                           recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();






                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}