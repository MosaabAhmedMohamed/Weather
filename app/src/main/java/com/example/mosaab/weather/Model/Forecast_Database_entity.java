package com.example.mosaab.weather.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Forecast_Database_entity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "temp")
    private double temp ;

    @ColumnInfo(name = "temp_min")
    private double temp_min ;

    @ColumnInfo(name = "temp_max")
    private double temp_max ;

    @ColumnInfo(name = "date")
    private int Date ;

    @ColumnInfo(name = "description")
    private String Descrirption;

    public Forecast_Database_entity() {

    }

    public Forecast_Database_entity(double temp, double temp_min, double temp_max, int date, String descrirption) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        Date = date;
        Descrirption = descrirption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getDescrirption() {
        return Descrirption;
    }

    public void setDescrirption(String descrirption) {
        Descrirption = descrirption;
    }
}
