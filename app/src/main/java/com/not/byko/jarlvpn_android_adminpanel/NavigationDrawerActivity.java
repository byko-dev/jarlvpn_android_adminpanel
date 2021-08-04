package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.fragments.AffiliateFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.ConfigsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.DetailsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.InvoicesFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.NewsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.PromoCodesFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.ServersFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.UsersFragment;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private static TextView username_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DetailsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_details);
        }

        Intent thisIntent = getIntent();
        //get element from header layout
        View nav_header = navigationView.getHeaderView(0);
        username_admin = nav_header.findViewById(R.id.username_label);

        username_admin.setText(thisIntent.getStringExtra("username"));

    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_details:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DetailsFragment()).commit();
                break;
            case R.id.nav_users:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new UsersFragment()).commit();
                break;
            case R.id.nav_servers:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ServersFragment()).commit();
                break;
            case R.id.nav_configs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ConfigsFragment()).commit();
                break;
            case R.id.nav_codes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PromoCodesFragment()).commit();
                break;
            case R.id.nav_affiliate:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AffiliateFragment()).commit();
                break;
            case R.id.nav_news:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NewsFragment()).commit();
                break;
            case R.id.nav_invoices:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InvoicesFragment()).commit();
                break;
            case R.id.nav_logout:
                //tu bedzie logout xd
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}