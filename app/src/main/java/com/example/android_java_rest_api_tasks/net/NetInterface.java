package com.example.android_java_rest_api_tasks.net;

import com.example.android_java_rest_api_tasks.models.TaskModel;
import com.example.android_java_rest_api_tasks.models.TasksModel;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NetInterface {

    @GET("v1/task")
    Observable<TasksModel> getTasks();

    @GET("v1/task/view/{id}")
    Observable<TaskModel> getTask(@Path("id") String taskId);

    @DELETE("v1/task/delete/{id}")
    Observable<TaskModel> deleteTask(@Path("id") String taskId);

    @POST("v1/task/create")
    Observable<TaskModel> createTask(@Body TaskModel taskModel);

    @PUT("v1/task/update/{id}")
    Observable<TaskModel> updateTask(@Path("id") String taskId, @Body TaskModel taskModel);

}


