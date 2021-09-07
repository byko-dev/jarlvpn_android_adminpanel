package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.DownloadPrivateKeyResponse;
import com.not.byko.jarlvpn_android_adminpanel.tools.Utils;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class ServerActivity extends AppCompatActivity {

    private WebController webController;
    private String ipAddress;
    private boolean wipeWg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        webController = new WebController();

        TextView serverIpTextView = findViewById(R.id.textView35);
        TextView expDateHostingTextView = findViewById(R.id.textView36);
        TextView expDateUserTextView = findViewById(R.id.textView37);
        TextView ownerMailTextView = findViewById(R.id.textView38);
        TextView cityAndCountryTextView = findViewById(R.id.textView39);
        TextView statusTextView = findViewById(R.id.textView40);
        TextView hostingTextView = findViewById(R.id.textView41);
        TextView passphraseTextView = findViewById(R.id.textView42);
        CheckBox wipeWgCheckBox = findViewById(R.id.checkBox2);

        Intent intent = getIntent();

        ipAddress = intent.getStringExtra("ipAddress");

        serverIpTextView.setText("ServerIP: " + ipAddress);
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

        wipeWgCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wipeWg = (!wipeWg);
            }
        });
    }

    public void resetServer(View view){
        Toast.makeText(view.getContext(), webController.resetServer(ipAddress).getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    public void downloadPrivateKey(View view){
        DownloadPrivateKeyResponse downloadPrivateKeyResponse = webController.downloadPPk(ipAddress, view);

        Utils.saveConfigAsFile(view.getContext(), downloadPrivateKeyResponse.getFileNamePpk(),
                downloadPrivateKeyResponse.getFileContentPpk(), "JarlVpnPrivateKeys");
    }

    public void refundServer(View view){
        Toast.makeText(view.getContext(), webController.refundServer(ipAddress).getMessage(),
                Toast.LENGTH_LONG).show();
    }

    public void deleteServer(View view){
        Toast.makeText(view.getContext(), webController.deleteServer(ipAddress, wipeWg).getMessage(),
                Toast.LENGTH_SHORT).show();
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