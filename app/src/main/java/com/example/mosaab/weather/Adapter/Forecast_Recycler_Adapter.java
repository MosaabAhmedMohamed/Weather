package com.example.mosaab.weather.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mosaab.weather.Model.Forecast_Database_entity;
import com.example.mosaab.weather.common.Common;
import com.example.mosaab.weather.Model.Forecast_Json;
import com.example.mosaab.weatherJson.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Forecast_Recycler_Adapter extends RecyclerView.Adapter<Forecast_Recycler_Adapter.ViewHolder>{

    private Context mContext;
    private Forecast_Json forecast_json_list;
    private List<Forecast_Database_entity> forecast_database_entity;



    public Forecast_Recycler_Adapter(Context context, Forecast_Json itemsArraylist)
    {
        mContext=context;
        forecast_json_list =itemsArraylist;
    }

    public Forecast_Recycler_Adapter(Context context, List<Forecast_Database_entity> forecast_database_entity)
    {
        mContext=context;
        this.forecast_database_entity=forecast_database_entity;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.single_forecast,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //if there is internet connection data will be getting from the server else the data will be updated from the database

        if (Common.isOnline.equals("YES")) {
            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                    .append(forecast_json_list.getList().get(i).getWeather().get(0).getIcon())
                    .append(".png").toString()).into(viewHolder.forecast_imageView);

            viewHolder.Desc_Tv.setText(forecast_json_list.list.get(i).getWeather().get(0).getDescription());
            viewHolder.Degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp()));
            viewHolder.Date_Tv.setText("Date : " + Common.convertUnixToDate(forecast_json_list.list.get(i).getDt()));
            viewHolder.Min_degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp_min()));
            viewHolder.Max_degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp_max()));
        }

        else if(Common.isOnline.equals("NO"))
        {
            viewHolder.Desc_Tv.setText(forecast_database_entity.get(i).getDescrirption());
            viewHolder.Degree_Tv.setText(String.valueOf(forecast_database_entity.get(i).getTemp()));
            viewHolder.Date_Tv.setText("Date : " + Common.convertUnixToDate(forecast_database_entity.get(i).getDate()));
            viewHolder.Min_degree_Tv.setText(String.valueOf(forecast_database_entity.get(i).getTemp_min()));
            viewHolder.Max_degree_Tv.setText(String.valueOf(forecast_database_entity.get(i).getTemp_max()));

        }

    }

    @Override
    public int getItemCount() {
       int itemCount =0;

        if (Common.isOnline.equals("YES")) {
           itemCount = forecast_json_list.getList().size();
        }
        else if(Common.isOnline.equals("NO"))
        {
            itemCount = forecast_database_entity.size();
        }


        return itemCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

       private TextView Date_Tv,Desc_Tv,Degree_Tv,Max_degree_Tv,Min_degree_Tv;
       private ImageView forecast_imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            forecast_imageView = itemView.findViewById(R.id.forecast_imageView);
            Date_Tv = itemView.findViewById(R.id.forecast_date_TV);
            Desc_Tv = itemView.findViewById(R.id.forecast_desc_Tv);
            Degree_Tv= itemView.findViewById(R.id.forecast_degree_TV);
            Max_degree_Tv= itemView.findViewById(R.id.min_temp_Tv);
            Min_degree_Tv = itemView.findViewById(R.id.max_temp_Tv);



        }
    }
}
