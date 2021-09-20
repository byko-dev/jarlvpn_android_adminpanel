package com.not.byko.jarlvpn_android_adminpanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.fragments.AffiliateFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.ConfigsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.DetailsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.InvoicesCryptoFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.InvoicesFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.NewsFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.PromoCodesFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.ServersFragment;
import com.not.byko.jarlvpn_android_adminpanel.fragments.UsersFragment;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawerLayout;
    private static TextView username_admin;
    private boolean darkMode;
    private Bundle bundle;
    private DetailsFragment detailsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //call setTheme before render activity
        Intent thisIntent = getIntent();
        darkMode = thisIntent.getBooleanExtra("darkMode", false);
        if(darkMode) setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        bundle = new Bundle();
        bundle.putBoolean("darkMode", darkMode);

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
            detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(bundle);

            setTitle("JarlVPN - Details");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    detailsFragment).commit();
            navigationView.setCheckedItem(R.id.nav_details);
        }



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
                setTitle("JarlVPN - Details");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        detailsFragment).commit();
                break;
            case R.id.nav_users:
                setTitle("JarlVPN - Users");

                UsersFragment usersFragment = new UsersFragment();
                usersFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        usersFragment).commit();
                break;
            case R.id.nav_servers:
                setTitle("JarlVPN - Servers");

                ServersFragment serversFragment = new ServersFragment();
                serversFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        serversFragment).commit();
                break;
            case R.id.nav_configs:
                setTitle("JarlVPN - Configurations");

                ConfigsFragment configsFragment = new ConfigsFragment();
                configsFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        configsFragment).commit();
                break;
            case R.id.nav_codes:
                setTitle("JarlVPN - Promo codes");

                PromoCodesFragment promoCodesFragment = new PromoCodesFragment();
                promoCodesFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        promoCodesFragment).commit();
                break;
            case R.id.nav_affiliate:
                setTitle("JarlVPN - Partners");

                AffiliateFragment affiliateFragment = new AffiliateFragment();
                affiliateFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        affiliateFragment).commit();
                break;
            case R.id.nav_news:
                setTitle("JarlVPN - News");

                NewsFragment newsFragment = new NewsFragment();
                newsFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        newsFragment).commit();
                break;
            case R.id.nav_invoices:
                setTitle("JarlVPN - Paypal");

                InvoicesFragment invoicesFragment = new InvoicesFragment();
                invoicesFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        invoicesFragment).commit();
                break;
            case R.id.nav_invoices_crypto:
                setTitle("JarlVPN - Crypto");

                InvoicesCryptoFragment invoicesCryptoFragment = new InvoicesCryptoFragment();
                invoicesCryptoFragment.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        invoicesCryptoFragment).commit();
                break;
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setCancelable(true);
                builder.setTitle("Really exit?");
                builder.setMessage("Are you sure you want to exit?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                WebController.logout();
                                setContentView(R.layout.activity_main);
                                Intent intent = new Intent(NavigationDrawerActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int witch){
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }
}