package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.models.getsetChart;
import com.example.myapplication.util.ApiClient;
import com.example.myapplication.util.ApiInterface;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewVote extends AppCompatActivity {

    private PieChart pieChart_view;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vote);


        pieChart_view=findViewById(R.id.pieChart_view);





        content();
    }


    public void content(){
        setPieChart();
        getChart();

        Log.e("TAG", "refresh: " );

        refresh(5000);
    }

    public void refresh(int time){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };

        handler.postDelayed(runnable,time);
    }
    private  void getChart(){


        Call<List<getsetChart>> call = ApiClient.getApiClient().create(ApiInterface.class).getChartDetails();

        call.enqueue(new Callback<List<getsetChart>>() {
            @Override
            public void onResponse(Call<List<getsetChart>> call, Response<List<getsetChart>> response) {
                Log.e("TAG", "onResponse: "+response );

                if(response.body()!=null){

                    List<PieEntry> pieEntries = new ArrayList<>();

                    for(getsetChart piechart : response.body()){

                        pieEntries.add(new PieEntry(piechart.getTaskId(),piechart.getTask()) );

                        pieChart_view.setVisibility(View.VISIBLE);
                      //  pieChart_view.animateXY(2000,2000);

                        ArrayList<Integer> colors = new ArrayList<>();
                        for (int color : ColorTemplate.MATERIAL_COLORS){
                            colors.add(color);
                        }
                        for (int color : ColorTemplate.VORDIPLOM_COLORS){
                            colors.add(color);
                        }


                        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
                        pieDataSet.setValueTextSize(12f);

                        pieDataSet.setColors(colors);

                        PieData pieData = new PieData(pieDataSet);
                        pieDataSet.setValueFormatter(new PercentFormatter(pieChart_view));
                        pieData.setDrawValues(true);

                        pieChart_view.setData(pieData);
                        pieChart_view.invalidate();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<getsetChart>> call, Throwable t) {

            }
        });

    }

    private void setPieChart(){
        pieChart_view.setDrawHoleEnabled(true);
        pieChart_view.setUsePercentValues(true);
        pieChart_view.setEntryLabelTextSize(12);
        pieChart_view.setEntryLabelColor(Color.BLACK);
        pieChart_view.setCenterText("Winner");
        pieChart_view.setCenterTextSize(24);
        pieChart_view.getDescription().setEnabled(false);

        Legend legend = pieChart_view.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(true);
        legend.setEnabled(true);


    }
}