package com.example.mosaab.weather.ViewHolder;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mosaab.weatherJson.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Contact_Us extends Fragment {

    private View Contact_View;
    private Button contact_email_Bu;

    public Contact_Us() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Contact_View =inflater.inflate(R.layout.fragment_contact__us,container,false);
        contact_email_Bu = Contact_View.findViewById(R.id.contact_email_Bu);

        contact_email_Bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"mosabahmeddev@gmail.com"});
                try {
                    startActivity(Intent.createChooser(intent, "E-mail"));
                } catch (android.content.ActivityNotFoundException ex) {
                    //do something else
                }

            }
        });
        return Contact_View;
    }

}
