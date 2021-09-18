package com.not.byko.jarlvpn_android_adminpanel;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.ConfigResponse;
import com.not.byko.jarlvpn_android_adminpanel.tools.Utils;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;


public class ConfigActivity extends AppCompatActivity {

    private static String usernameId;
    private static String confNameStr;
    private ImageView qrCodeIV;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        setTitle("JarlVPN - configuration");

        TextView ownerMail = findViewById(R.id.textView16);
        TextView serverIp = findViewById(R.id.textView17);
        TextView confName = findViewById(R.id.textView18);
        TextView orgConfName = findViewById(R.id.textView19);
        qrCodeIV = findViewById(R.id.idIVQrcode);

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
        ConfigResponse configResponse = webController.getConfigContent(usernameId, confNameStr, view);

        Utils.saveConfigAsFile(view.getContext(), configResponse.getConfigName() + ".conf",
                configResponse.getConfigContext());
    }

    public void createQrCode(View view){
        WebController webController = new WebController();
        ConfigResponse configResponse = webController.getConfigContent(usernameId, confNameStr, view);

        if (TextUtils.isEmpty(configResponse.getConfigContext())) {

        } else {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();

            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;

            int dimen = width < height ? width : height;
            dimen = dimen * 3 / 4;
            qrgEncoder = new QRGEncoder(configResponse.getConfigContext(), null, QRGContents.Type.TEXT, dimen);
            bitmap = qrgEncoder.getBitmap();
            qrCodeIV.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}