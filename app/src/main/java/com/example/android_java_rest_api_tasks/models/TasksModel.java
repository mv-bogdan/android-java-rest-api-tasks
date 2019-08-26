package com.example.android_java_rest_api_tasks.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TasksModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private List<TaskModel> data = null;

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("size")
    @Expose
    private Integer size;

    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TaskModel> getData() {
        return data;
    }

    public void setData(List<TaskModel> data) {
        this.data = data;
    }

}
