package com.example.mosaab.weather.ViewHolder;


import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mosaab.weather.Model.Forecast_Database_entity;
import com.example.mosaab.weather.Model.Forecast_Json;
import com.example.mosaab.weather.Adapter.Forecast_Recycler_Adapter;
import com.example.mosaab.weather.Interfaces.OpenWeatherCity;
import com.example.mosaab.weather.Model.Weahter_Datebase_entity;
import com.example.mosaab.weather.WebServices.RetroFit_Client;
import com.example.mosaab.weather.Model.Weather_Json;
import com.example.mosaab.weather.common.Common;
import com.example.mosaab.weather.Databases.weather_Database;
import com.example.mosaab.weatherJson.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class City_Fragment_Holder extends Fragment {

    private OpenWeatherCity openWeatherCity;
    private TextView higher_degree, lower_degree, degree,Weather_Desc;
    private View City_View;
    private String City_name;
    private ImageView imageView_weather;
    private ProgressBar progressBar;
    private ConstraintLayout base_layout,not_connected_layout;

    private RecyclerView forecast_recycler;
    private Forecast_Recycler_Adapter forecast_adapter;
    private Forecast_Json forecast_jsons_list;

    private  Weather_Json weather_json;

    private  weather_Database database;
    private List<Weahter_Datebase_entity> weahter_datebase_entity;
    private List<Forecast_Database_entity> forecast_database_entitiy;

    public City_Fragment_Holder() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public City_Fragment_Holder(String City) {
        City_name = City;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        City_View = inflater.inflate(R.layout.fragment_city__fragment__holder, container, false);

        isOnline();
        InitUI();
        InitDatabase();

        if (isOnline())
        {
            progressBar.setVisibility(View.VISIBLE);
            Common.isOnline="YES";
            InitRetorFit();
            getWeather();
            getForecast();

        }
        else if(isOnline() == false)
        {
            progressBar.setVisibility(View.GONE);
            forecast_recycler.setVisibility(View.VISIBLE);
            Common.isOnline="NO";
            CheckDate();
        }


        return City_View;
    }


    private void InitUI () {
            //Layout
            base_layout = City_View.findViewById(R.id.base_Layout);
            not_connected_layout = City_View.findViewById(R.id.not_connected_layout);


            higher_degree = City_View.findViewById(R.id.higher_degree_TV);
            lower_degree = City_View.findViewById(R.id.lower_degree_TV);
            degree = City_View.findViewById(R.id.degree_Tv);
            Weather_Desc = City_View.findViewById(R.id.weather_desc_Tv);
            imageView_weather = City_View.findViewById(R.id.image_weather);
            progressBar = City_View.findViewById(R.id.progress_circular);


            //Recycler View
            forecast_recycler = City_View.findViewById(R.id.forecast_recycler_view);
            forecast_recycler.setHasFixedSize(true);
            forecast_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        }


     //to check the status of the device is connected to the internet or NOT
    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }



    private void InitRetorFit() {
        openWeatherCity = RetroFit_Client.getInstance().create(OpenWeatherCity.class);
    }

    private void InitDatabase()
    {
        //Data base
        database = Room.databaseBuilder(getActivity(),weather_Database.class,"test")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void InitDatebaseEntities()
    {
        weahter_datebase_entity =database.weather_dao().getAllFromWeather(City_name);
        forecast_database_entitiy =database.weather_dao().getAllFromForecast(City_name);
    }

    //To check the  validation of the date in the app
    private void CheckDate()
    {
        InitDatebaseEntities();
        Calendar cal = Calendar.getInstance();
        String weather_database_Day;

        if(weahter_datebase_entity.isEmpty())
        {

            base_layout.setVisibility(View.GONE);
            not_connected_layout.setVisibility(View.VISIBLE);

        }
        else if(!weahter_datebase_entity.isEmpty()) {
            weather_database_Day = Common.convertUnixToDay(weahter_datebase_entity.get(weahter_datebase_entity.size() - 1).getDate());
            if (!String.valueOf(cal.get(Calendar.DATE)).equals(weather_database_Day)) {
                base_layout.setVisibility(View.GONE);
                not_connected_layout.setVisibility(View.VISIBLE);
            } else if (String.valueOf(cal.get(Calendar.DATE)).equals(weather_database_Day)) {
                GetWeatherFromDatabase();
                GetForecastFromDatabase();

            }
        }


    }

    //Getting the weather data from the API
    private void getWeather () {
            Call<Weather_Json> call = openWeatherCity
                    .getWeatherByCity(City_name, "b6907d289e10d714a6e88b30761fae22");
            call.enqueue(new Callback<Weather_Json>() {
                @Override
                public void onResponse(Call<Weather_Json> call, Response<Weather_Json> response) {

                    if (!response.isSuccessful()) {
                        degree.setText("Code: " + response.code());
                        return;
                    }


                    weather_json = response.body();

                    degree.setText(String.valueOf(weather_json.getMain().getTemp()));
                    higher_degree.setText(String.valueOf(weather_json.getMain().getTemp_max()));
                    lower_degree.setText(String.valueOf(weather_json.getMain().getTemp_min()));
                    Weather_Desc.setText(weather_json.getWeather().get(0).getDescription());

                    loadImage();
                    InsetWeatherToDatabase();

                    Common.setTemp(String.valueOf(weather_json.getMain().getTemp()));
                    Common.setDesc(weather_json.getWeather().get(0).getDescription());

                   // Toast.makeText(getActivity(), weather_json.getName(),Toast.LENGTH_SHORT).show();
                    //  Log.d("respooo", String.valueOf(comments.getMain().getTemp()));


                }

                @Override
                public void onFailure(Call<Weather_Json> call, Throwable t) {
                    degree.setText(t.getMessage() + "error");
                    Log.d("respo", String.valueOf(t.getMessage()));
                }


            });
        }

        //for loading the weather Image
    private void loadImage() {

        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weather_json.getWeather().get(0).getIcon())
                .append(".png").toString()).into(imageView_weather);
    }

    private void InsetWeatherToDatabase() {

        database.weather_dao().insertAllToWeather(new Weahter_Datebase_entity(City_name,weather_json.getMain().getTemp(),
                weather_json.getMain().getTemp_min(),
                weather_json.getMain().getTemp_max(),
                weather_json.getDt(),
                weather_json.getWeather().get(0).getDescription()));
    }

    private void GetWeatherFromDatabase(){



        if(weahter_datebase_entity.size()!=0) {
          //  Toast.makeText(getActivity(),weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getDescription(), Toast.LENGTH_SHORT).show();
            degree.setText(String.valueOf(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getTemp()));
            higher_degree.setText(String.valueOf(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getTemp_max()));
            lower_degree.setText(String.valueOf(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getTemp_min()));
            Weather_Desc.setText(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getDescription());

            Common.setTemp(String.valueOf(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getTemp()));
            Common.setDesc(weahter_datebase_entity.get(weahter_datebase_entity.size()-1).getDescription());
        }



    }

    //Getting the Forecast data from the API
    private void getForecast() {

            Call<Forecast_Json> call = openWeatherCity
                .getForcastByCity(City_name,"b6907d289e10d714a6e88b30761fae22");
                call.enqueue(new Callback<Forecast_Json>() {

            @Override
            public void onResponse(Call<Forecast_Json> call, Response<Forecast_Json> response) {

                if (!response.isSuccessful()) {
                    //degree.setText("Code: " + response.code());
                    Log.d("respo", String.valueOf(response.message()));
                    return;
                }

                progressBar.setVisibility(View.GONE);
                forecast_recycler.setVisibility(View.VISIBLE);
                forecast_jsons_list =new Forecast_Json();
                forecast_jsons_list = response.body();
                forecast_adapter = new Forecast_Recycler_Adapter(getActivity(), forecast_jsons_list);
                forecast_recycler.setAdapter(forecast_adapter);
                forecast_adapter.notifyDataSetChanged();

                InsertForecastToDatebase();

            }



            @Override
            public void onFailure(Call<Forecast_Json> call, Throwable t) {
                Log.d("respo", String.valueOf(t.getMessage()));
            }

        });

    }

    private void InsertForecastToDatebase() {

        for (int i = 0; i <forecast_jsons_list.getList().size() ; i++) {


            database.weather_dao().insertAllToForecast(new Forecast_Database_entity(City_name,forecast_jsons_list.getList().get(i).getMain().getTemp(),
                    forecast_jsons_list.getList().get(i).getMain().getTemp_min(),
                    forecast_jsons_list.getList().get(i).getMain().getTemp_max(),
                    forecast_jsons_list.getList().get(i).getDt(),
                    forecast_jsons_list.getList().get(i).getWeather().get(0).getDescription()));
        }
    }

    private void GetForecastFromDatabase() {



        if(!forecast_database_entitiy.isEmpty()) {
            forecast_adapter = new Forecast_Recycler_Adapter(getActivity(), forecast_database_entitiy);
            forecast_recycler.setAdapter(forecast_adapter);
            forecast_adapter.notifyDataSetChanged();
         // Toast.makeText(getActivity(), forecast_database_entitiy.get(forecast_database_entitiy.size()-1).getCity_name(), Toast.LENGTH_SHORT).show();



        }

    }


}
