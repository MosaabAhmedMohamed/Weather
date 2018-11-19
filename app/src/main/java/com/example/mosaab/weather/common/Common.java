package com.example.mosaab.weather.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    public static final String API_KEy = "af7b1830541171b1ec00a7f31168e3d7";

    public static String Temp;
    public static String Desc;

    public static String isOnline=null;

    public static String getTemp() {
        return Temp;
    }

    public static void setTemp(String temp) {
        Temp = temp;
    }

    public static String getDesc() {
        return Desc;
    }

    public static void setDesc(String desc) {
        Desc = desc;
    }

    public static String convertUnixToDate(long dt)
    {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm EEE dd MM yyyy");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixToDay(long dt)
    {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String formatted = sdf.format(date);
        return formatted;
    }

    public static String convertUnixToHour(long dt)
    {
        Date date = new Date(dt*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formatted = sdf.format(date);
        return formatted;
    }
}
