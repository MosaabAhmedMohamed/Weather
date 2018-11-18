package com.example.mosaab.weather.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.mosaab.weather.Model.Forecast_Database_entity;
import com.example.mosaab.weather.Model.Weahter_Datebase_entity;

import java.util.List;

@Dao
public interface Weather_dao {

    @Insert
    void insertAllToWeather(Weahter_Datebase_entity weahter_datebase_entitiy);

    @Query("SELECT * FROM weahter_datebase_entity WHERE city LIKE :city_name")
     List<Weahter_Datebase_entity> getAllFromWeather(String city_name);

    @Insert
    void insertAllToForecast(Forecast_Database_entity forecast_database_entity);

    @Query("SELECT * FROM forecast_database_entity WHERE city LIKE :city_name_forecast")
    List<Forecast_Database_entity> getAllFromForecast(String city_name_forecast);

}
