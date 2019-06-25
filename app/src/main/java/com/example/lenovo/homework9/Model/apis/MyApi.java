package com.example.lenovo.homework9.Model.apis;


import com.example.lenovo.homework9.Model.bean.IndexBean;
import com.example.lenovo.homework9.Model.bean.VersionBean;
import com.example.lenovo.homework9.Model2.bean.IndexBean2;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {





    @GET("index/index")
    Flowable<IndexBean> getIndexData();

    @GET("apkinfo")
    Flowable<VersionBean> getVersionData(@Query("package") String pg, @Query("name") String name);




}
