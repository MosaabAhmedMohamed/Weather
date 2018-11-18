package com.example.mosaab.weather.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Weahter_Datebase_entity {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    @ColumnInfo(name = "city")
    private String City_name;

    @ColumnInfo(name = "temp")
    private double temp ;

    @ColumnInfo(name = "temp_min")
    private double temp_min ;

    @ColumnInfo(name = "temp_max")
    private double temp_max ;


    public Weahter_Datebase_entity()
    {

    }
    public Weahter_Datebase_entity(String city_name, double temp, double temp_min, double temp_max) {
        City_name = city_name;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }

    public String getCity_name() {
        return City_name;
    }

    public void setCity_name(String city_name) {
        City_name = city_name;
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

}
