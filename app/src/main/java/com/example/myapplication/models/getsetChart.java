package com.example.myapplication.models;

import com.google.gson.annotations.SerializedName;

public class getsetChart {

    @SerializedName("id")
    int taskId;

    @SerializedName("task")
    String name;

    public int getTaskId() {
        return taskId;
    }

    public String getTask() {
        return name;
    }
}
