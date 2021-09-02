package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.ChangePasswordRequest;
import com.not.byko.jarlvpn_android_adminpanel.models.UserDetailsResponse;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class UserActivity extends AppCompatActivity {

    private WebController webController;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");

        webController = new WebController();
        UserDetailsResponse detailsResponse = webController.getUserDetails(username);

        String ipAddresses = "";

        for(String ipAddress : detailsResponse.getIpAddress()){
            ipAddresses += "\n" + ipAddress;
        }

        TextView usernameTextView = findViewById(R.id.textView43);
        TextView createDateTextView = findViewById(R.id.textView47);
        TextView userTypeTextView = findViewById(R.id.textView48);
        TextView vpnActivatedTextView = findViewById(R.id.textView49);
        TextView ipAddressTextView = findViewById(R.id.textView50);

        usernameTextView.setText("Username: " + username);
        createDateTextView.setText("Account created date: " + detailsResponse.getCreateDate());
        userTypeTextView.setText("User type: " + detailsResponse.getUserType());
        vpnActivatedTextView.setText("Active subscription: " + detailsResponse.getVpnActivated());
        ipAddressTextView.setText("Client ip addresses" + ipAddresses);
    }

    public void backToUsers(View view){
        super.onBackPressed();
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
        startActivity(intent);
    }

}