package com.example.android_java_rest_api_tasks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("dateComplete")
    @Expose
    private String dateComplete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(String dateComplete) {
        this.dateComplete = dateComplete;
    }

}
