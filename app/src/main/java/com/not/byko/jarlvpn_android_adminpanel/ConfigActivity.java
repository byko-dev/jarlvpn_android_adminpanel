package com.not.byko.jarlvpn_android_adminpanel;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        TextView ownerMail = findViewById(R.id.textView16);
        TextView serverIp = findViewById(R.id.textView17);
        TextView confName = findViewById(R.id.textView18);
        TextView orgConfName = findViewById(R.id.textView19);

        Intent intent = getIntent();

        ownerMail.setText("Owner: " + intent.getStringExtra("ownerMail"));
        serverIp.setText("Server ip: " + intent.getStringExtra("serverIp"));
        confName.setText("Configuration name: " + intent.getStringExtra("confName"));
        orgConfName.setText("Orginal configuration name: " + intent.getStringExtra("orgConfName"));
    }

    public void downloadConfiguration(View view){
        Toast.makeText(view.getContext(), "downloadConfiguration", Toast.LENGTH_SHORT).show();
    }

    public void createQrCode(View view){
        Toast.makeText(view.getContext(), "createQrCode", Toast.LENGTH_SHORT).show();
    }

    public void backActivity(View view){
        super.onBackPressed();
    }
}