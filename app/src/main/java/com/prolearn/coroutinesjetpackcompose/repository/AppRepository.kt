package com.prolearn.coroutinesjetpackcompose.repository;

import com.prolearn.coroutinesjetpackcompose.model.CricketResponse

import com.prolearn.coroutinesjetpackcompose.model.SportsModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

class AppRepository{
    interface SportsApi{
        @GET("football/{pid}")
        suspend fun getFootballById(@Path("pid") pid : String): SportsModel;

        @GET("cricket")
        suspend fun getCricket(): CricketResponse;

//        @GET("cricketdelay")
//        suspend fun getCricket(): CricketResponse;

    }
    private lateinit var api : SportsApi;

    init{
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

        val retrofit = Retrofit.Builder()
            .baseUrl("https://apiprojectdemo.wdc-np.tas.vmware.com/sports/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


        api = retrofit.create(SportsApi::class.java);
    }

    suspend fun getFootball(pid : String) : SportsModel {
        return api.getFootballById(pid);
    }

    suspend fun getCricket() : CricketResponse {
        return api.getCricket();
    }
}