package com.not.byko.jarlvpn_android_adminpanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private Button blockUser;
    private TextView userTypeTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        darkMode = intent.getBooleanExtra("darkMode", false);
        if(darkMode) setTheme(R.style.Theme_AppCompat_Dark);

        setContentView(R.layout.activity_user);
        setTitle("JarlVPN - user details");

        username = intent.getStringExtra("username");

        webController = new WebController();
        UserDetailsResponse detailsResponse = returnUserDetails();

        String ipAddresses = "";

        for(String ipAddress : detailsResponse.getIpAddress()){
            ipAddresses += "\n" + ipAddress;
        }

        TextView usernameTextView = findViewById(R.id.textView43);
        TextView createDateTextView = findViewById(R.id.textView47);
        userTypeTextView = findViewById(R.id.textView48);
        TextView vpnActivatedTextView = findViewById(R.id.textView49);
        TextView ipAddressTextView = findViewById(R.id.textView50);
        Button setAffiliatePartner = findViewById(R.id.button28);
        blockUser = findViewById(R.id.button22);

        usernameTextView.setText("Username: " + username);
        createDateTextView.setText("Account created date: " + detailsResponse.getCreateDate());
        userTypeTextView.setText("User type: " + detailsResponse.getUserType());
        vpnActivatedTextView.setText("Active subscription: " + detailsResponse.getVpnActivated());
        ipAddressTextView.setText("Client ip addresses" + ipAddresses);
        if(detailsResponse.getAffiliatePartner()) setAffiliatePartner.setVisibility(View.GONE);
        if(!detailsResponse.getUserType().equals("USER")) blockUser.setText("Unblock this account");
    }

    //this need refresh
    public void blockUser(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Block/unblock user");
        builder.setMessage("Are you sure to block/unblock user: " + username + "?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), webController.blockUser(username).getMessage(),
                                Toast.LENGTH_SHORT).show();

                        String userType = returnUserDetails().getUserType();
                        userTypeTextView.setText("User type: " + userType);

                        if(userType.equals("USER")) blockUser.setText("Block this account");
                        else blockUser.setText("Unblock this account");
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
    }

    public void deleteThisUser(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Delete user");
        builder.setMessage("Are you sure to delete user: " + username + "?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), webController.deleteUserAccount(username).getMessage(),
                                Toast.LENGTH_SHORT).show();
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

    public void giveHimServer(View view){
        Intent intent = new Intent(view.getContext(), GiveServerActivity.class);
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

    public UserDetailsResponse returnUserDetails(){
        return webController.getUserDetails(username, getApplicationContext());
    }



}