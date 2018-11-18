package com.example.mosaab.weather;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mosaab.weather.Interfaces.Weather_dao;
import com.example.mosaab.weather.Model.Forecast_Database_entity;
import com.example.mosaab.weather.Model.Weahter_Datebase_entity;

@Database(entities = {Weahter_Datebase_entity.class,Forecast_Database_entity.class},version = 4)
public abstract class weather_Database extends RoomDatabase {
    public abstract Weather_dao weather_dao();
}
