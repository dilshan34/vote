package com.example.myapplication.util;

import com.example.myapplication.models.getsetChart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("getSubTask.php")
    Call<List<getsetChart>> getChartDetails();
}
