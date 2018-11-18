package com.example.mosaab.weather.Interfaces;



import com.example.mosaab.weather.Model.Forecast_Json;
import com.example.mosaab.weather.Model.Weather_Json;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface OpenWeatherCity {

    @GET("weather")
    Call<Weather_Json> getWeatherByCity(@Query("q") String City, @Query("appid") String Key);


    @GET("forecast")
    Call<Forecast_Json> getForcastByCity(@Query("q") String City, @Query("appid") String Key);


}
