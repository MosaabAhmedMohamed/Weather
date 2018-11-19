package com.example.mosaab.weather.ViewHolder;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.mosaab.weatherJson.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private City_Fragment_Holder cairo;
    private City_Fragment_Holder London;
    private City_Fragment_Holder New_York;
    private City_Fragment_Holder Dubai;
    private City_Fragment_Holder australia;
    private Contact_Us contact_us;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;

    private ConstraintLayout placeHolder_layout;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleMarginStart(300);
        toolbar.setTitle("Cairo");
        setSupportActionBar(toolbar);

        InitUI();

        InitFragmentHolder();

    }


    private void InitUI()
    {
        placeHolder_layout = findViewById(R.id.PlaceHolder_layout);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void InitFragmentHolder()
    {
        cairo= new City_Fragment_Holder("Cairo");
        fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.PlaceHolder_layout,cairo);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cairo) {

            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("Cairo");
            cairo= new City_Fragment_Holder("Cairo");
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout,cairo);
            fragmentTransaction.commit();
        }
        else if(id==R.id.nav_London)
        {
            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("London");
            London= new City_Fragment_Holder("London");
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout,London);
            fragmentTransaction.commit();
        }
        else if (id == R.id.nav_new_york) {
            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("New York");
            New_York= new City_Fragment_Holder("new york");
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout,New_York);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_dubai) {
            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("Dubai");
            Dubai = new City_Fragment_Holder("Dubai");
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout,Dubai);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_san_diego) {
            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("Australia");
            australia = new City_Fragment_Holder("australia");
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout, australia);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_contact) {

            toolbar.setTitleMarginStart(300);
            toolbar.setTitle("Contact Us");
            contact_us = new Contact_Us();
            fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.PlaceHolder_layout, contact_us);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
