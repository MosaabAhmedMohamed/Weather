package com.example.mosaab.weather.ViewHolder;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mosaab.weather.Model.Forecast_Json;
import com.example.mosaab.weather.Adapter.Forecast_Recycler_Adapter;
import com.example.mosaab.weather.Interfaces.OpenWeatherCity;
import com.example.mosaab.weather.WebServices.RetroFit_Client;
import com.example.mosaab.weather.Model.Weather_Json;
import com.example.mosaab.weatherJson.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class City_Fragment_Holder extends Fragment {

    private OpenWeatherCity openWeatherCity;
    private TextView higher_degree, lower_degree, degree;
    private View City_View;
    private String City_name;
    private ImageView imageView_weather;

    private RecyclerView forecast_recycler;
    private Forecast_Recycler_Adapter forecast_adapter;
    private Forecast_Json forecast_jsons_list;

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

        InitUI();

        InitRetorFit();

        getWeather();

        getForecast();

        return City_View;
    }


    private void InitUI () {

            higher_degree = City_View.findViewById(R.id.higher_degree_TV);
            lower_degree = City_View.findViewById(R.id.lower_degree_TV);
            degree = City_View.findViewById(R.id.degree_Tv);
            imageView_weather = City_View.findViewById(R.id.image_weather);

            //Recycler View
            forecast_recycler = City_View.findViewById(R.id.forecast_recycler_view);
            forecast_recycler.setHasFixedSize(true);
            forecast_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        }


    private void InitRetorFit() {
        openWeatherCity = RetroFit_Client.getInstance().create(OpenWeatherCity.class);
    }



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


                    Weather_Json weather_json = response.body();

                    degree.setText(String.valueOf(weather_json.getMain().getTemp()));
                    higher_degree.setText(String.valueOf(weather_json.getMain().getTemp_max()));
                    lower_degree.setText(String.valueOf(weather_json.getMain().getTemp_min()));


                    Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                            .append(weather_json.getWeather().get(0).getIcon())
                            .append(".png").toString()).into(imageView_weather);
                    Toast.makeText(getActivity(), weather_json.getName(),Toast.LENGTH_SHORT).show();

                    getForecast();
                    //  Log.d("respooo", String.valueOf(comments.getMain().getTemp()));


                }

                @Override
                public void onFailure(Call<Weather_Json> call, Throwable t) {
                    degree.setText(t.getMessage() + "error");
                    Log.d("respo", String.valueOf(t.getMessage()));
                }


            });
        }

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

                forecast_jsons_list =new Forecast_Json();
                forecast_jsons_list = response.body();
                forecast_adapter = new Forecast_Recycler_Adapter(getActivity(), forecast_jsons_list);
                forecast_recycler.setAdapter(forecast_adapter);
                forecast_adapter.notifyDataSetChanged();






            }

            @Override
            public void onFailure(Call<Forecast_Json> call, Throwable t) {
                Log.d("respo", String.valueOf(t.getMessage()));
            }

        });

    }


}
