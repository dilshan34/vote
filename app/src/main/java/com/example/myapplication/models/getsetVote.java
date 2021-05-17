package com.example.myapplication.models;

public class getsetVote {

  String id,name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public getsetVote(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
