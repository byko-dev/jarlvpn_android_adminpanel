package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickEvent(View view){
        setContentView(R.layout.activity_navigation_drawer);
        Intent intent = new Intent(this, NavigationDrawerActivity.class);
        startActivity(intent);
    }

}