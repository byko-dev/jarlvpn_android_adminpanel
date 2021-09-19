package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.ChangePasswordRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.UserDetailsResponse;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class UserActivity extends AppCompatActivity {

    private WebController webController;
    private String username;
    private boolean darkMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        darkMode = intent.getBooleanExtra("darkMode", false);
        if(darkMode) setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        setContentView(R.layout.activity_user);
        setTitle("JarlVPN - user details");

        username = intent.getStringExtra("username");

        webController = new WebController();
        UserDetailsResponse detailsResponse = webController.getUserDetails(username, getApplicationContext());

        String ipAddresses = "";

        for(String ipAddress : detailsResponse.getIpAddress()){
            ipAddresses += "\n" + ipAddress;
        }

        TextView usernameTextView = findViewById(R.id.textView43);
        TextView createDateTextView = findViewById(R.id.textView47);
        TextView userTypeTextView = findViewById(R.id.textView48);
        TextView vpnActivatedTextView = findViewById(R.id.textView49);
        TextView ipAddressTextView = findViewById(R.id.textView50);
        Button setAffiliatePartner = findViewById(R.id.button28);

        usernameTextView.setText("Username: " + username);
        createDateTextView.setText("Account created date: " + detailsResponse.getCreateDate());
        userTypeTextView.setText("User type: " + detailsResponse.getUserType());
        vpnActivatedTextView.setText("Active subscription: " + detailsResponse.getVpnActivated());
        ipAddressTextView.setText("Client ip addresses" + ipAddresses);
        if(detailsResponse.getAffiliatePartner()) setAffiliatePartner.setVisibility(View.GONE);
    }

    public void blockUser(View view){
        Toast.makeText(view.getContext(), webController.blockUser(username).getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    public void deleteThisUser(View view){
        Toast.makeText(view.getContext(), webController.deleteUserAccount(username).getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    public void changePassword(View view){
        Intent intent = new Intent(view.getContext(), ChangePasswordActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("darkMode", darkMode);
        startActivity(intent);
    }

    public void setAffiliator(View view){
        Intent intent = new Intent(view.getContext(), CreateAffiliatorActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("darkMode", darkMode);
        startActivity(intent);
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