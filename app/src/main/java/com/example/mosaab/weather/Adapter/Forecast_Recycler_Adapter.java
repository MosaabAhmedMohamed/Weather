package com.example.mosaab.weather.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mosaab.weather.Common.Common;
import com.example.mosaab.weather.Model.Forecast_Json;
import com.example.mosaab.weatherJson.R;
import com.squareup.picasso.Picasso;

public class Forecast_Recycler_Adapter extends RecyclerView.Adapter<Forecast_Recycler_Adapter.ViewHolder>{

    private Context mContext;
    private Forecast_Json forecast_json_list;



    public Forecast_Recycler_Adapter(Context context, Forecast_Json itemsArraylist)
    {
        mContext=context;
        forecast_json_list =itemsArraylist;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.single_forecast,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(forecast_json_list.getList().get(i).getWeather().get(0).getIcon())
                .append(".png").toString()).into(viewHolder.forecast_imageView);

        viewHolder.Desc_Tv.setText(forecast_json_list.list.get(i).getWeather().get(0).getDescription());
        viewHolder.Degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp()));
        viewHolder.Date_Tv.setText("Date : "+Common.convertUnixToDate(forecast_json_list.list.get(i).getDt()));
        viewHolder.Min_degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp_min()));
        viewHolder.Max_degree_Tv.setText(String.valueOf(forecast_json_list.getList().get(i).getMain().getTemp_max()));
    }

    @Override
    public int getItemCount() {
        return forecast_json_list.list.size();
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
