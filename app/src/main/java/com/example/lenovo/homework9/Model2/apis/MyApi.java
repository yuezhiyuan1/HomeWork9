package com.example.lenovo.homework9.Model2.apis;


import com.example.lenovo.homework9.Model2.bean.IndexBean2;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MyApi {

    @GET("list/0/json")
    Flowable<IndexBean2> getIndexData();



}
