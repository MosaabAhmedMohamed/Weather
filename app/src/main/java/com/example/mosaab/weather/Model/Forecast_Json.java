package com.example.mosaab.weather.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast_Json {

    @SerializedName("cod")
    private String cod ;
    @SerializedName("message")
    private double message ;
    @SerializedName("cnt")
    private int cnt ;
    @SerializedName("list")
    private List<MyList> list ;
    @SerializedName("city")
    private City city ;



    public Forecast_Json() {
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<MyList> getList() {
        return list;
    }

    public void setList(List<MyList> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
