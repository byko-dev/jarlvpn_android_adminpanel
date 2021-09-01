package com.not.byko.jarlvpn_android_adminpanel;


import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.ConfigResponse;
import com.not.byko.jarlvpn_android_adminpanel.tools.Utils;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ConfigActivity extends AppCompatActivity {

    private static String usernameId;
    private static String confNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        TextView ownerMail = findViewById(R.id.textView16);
        TextView serverIp = findViewById(R.id.textView17);
        TextView confName = findViewById(R.id.textView18);
        TextView orgConfName = findViewById(R.id.textView19);

        Intent intent = getIntent();

        usernameId = intent.getStringExtra("ownerId");
        confNameStr = intent.getStringExtra("confName");

        ownerMail.setText("Owner: " + intent.getStringExtra("ownerMail"));
        serverIp.setText("Server ip: " + intent.getStringExtra("serverIp"));
        confName.setText("Configuration name: " + confNameStr);
        orgConfName.setText("Orginal configuration name: " + intent.getStringExtra("orgConfName"));
    }

    public void downloadConfiguration(View view){
        WebController webController = new WebController();
        ConfigResponse configResponse = webController.getConfigContent(usernameId, confNameStr);

        Utils.saveConfigAsFile(view.getContext(), configResponse.getConfigName() + ".conf",
                configResponse.getConfigContext(), "JarlVpnConfigs");
    }

    public void createQrCode(View view){
        Toast.makeText(view.getContext(), "Coming soon!", Toast.LENGTH_SHORT).show();
    }

    public void backActivity(View view){
        super.onBackPressed();
    }





}