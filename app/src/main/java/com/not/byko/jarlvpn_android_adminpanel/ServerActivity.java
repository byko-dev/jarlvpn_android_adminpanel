package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        TextView serverIpTextView = findViewById(R.id.textView35);
        TextView expDateHostingTextView = findViewById(R.id.textView36);
        TextView expDateUserTextView = findViewById(R.id.textView37);
        TextView ownerMailTextView = findViewById(R.id.textView38);
        TextView cityAndCountryTextView = findViewById(R.id.textView39);
        TextView statusTextView = findViewById(R.id.textView40);
        TextView hostingTextView = findViewById(R.id.textView41);
        TextView passphraseTextView = findViewById(R.id.textView42);

        Intent intent = getIntent();

        serverIpTextView.setText("ServerIP: " + intent.getStringExtra("ipAddress"));
        expDateHostingTextView.setText("Server expiry on hosting: " + intent.getStringExtra("expDate"));
        expDateUserTextView.setText("User rent expire: " + intent.getStringExtra("userExpDate"));
        ownerMailTextView.setText("Owner email: " + intent.getStringExtra("ownerEmail"));
        cityAndCountryTextView.setText("Location: " + intent.getStringExtra("serverCountry") + ", " +
                intent.getStringExtra("serverCity"));
        statusTextView.setText("Server status: " + intent.getStringExtra("status"));
        hostingTextView.setText("Hosting: " + intent.getStringExtra("hosting"));
        passphraseTextView.setText("Passphrase to private key: " + intent.getStringExtra("passphrase"));

        if(intent.getStringExtra("status").equals("OK"))
            statusTextView.setTextColor(getColor(R.color.green));
        else statusTextView.setTextColor(getColor(R.color.red));
    }

    public void manageServer(View view){

    }

    public void backToServers(View view){
        super.onBackPressed();
    }


}