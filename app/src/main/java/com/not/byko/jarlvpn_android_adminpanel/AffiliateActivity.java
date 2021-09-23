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
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.models.AffiliateDetailsResponse;
import com.not.byko.jarlvpn_android_adminpanel.models.AffiliatePayments;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.util.ArrayList;
import java.util.List;

public class AffiliateActivity extends AppCompatActivity {

    private String username;
    private WebController webController;
    private boolean darkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        darkMode = intent.getBooleanExtra("darkMode", false);
        if(darkMode) setTheme(R.style.Theme_AppCompat_Dark);

        setContentView(R.layout.activity_affiliate);

        setTitle("JarlVPN - Partner");

        TextView usernameTextView = findViewById(R.id.textView67);
        TextView totalEarnedTextView = findViewById(R.id.textView68);
        TextView withdrawValueTextView = findViewById(R.id.textView69);
        TextView discountCodeTextView = findViewById(R.id.textView70);


        webController = new WebController();

        username = intent.getStringExtra("username");

        AffiliateDetailsResponse affiliateDetailsResponse = webController.getAffiliateDetails(username,  getApplicationContext());

        usernameTextView.setText("User: " + username);
        totalEarnedTextView.setText("Total earned: " + affiliateDetailsResponse.getTotalPrice());
        withdrawValueTextView.setText("Withdraw value: "+ affiliateDetailsResponse.getWithdrawPrice());
        discountCodeTextView.setText("Discount code: " + affiliateDetailsResponse.getCode() + " used " + affiliateDetailsResponse.getUsedTime() + " times");
    }

    public void resetAffiliate(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Reset affiliate partner scores");
        builder.setMessage("Are you sure to reset affiliate partner: "+username+" withdraw value?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                webController.resetWithdrawValue(username).getMessage(), Toast.LENGTH_LONG).show();
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

    public void deleteAffiliatePermission(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Delete affiliate permission");
        builder.setMessage("Are you sure to delete affiliate permission for user: " + username + "?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),
                                webController.deleteAffiliatePermission(username).getMessage(),
                                Toast.LENGTH_LONG).show();

                        onBackPressed();
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

    public void cryptoInvoiceList(View view){

        Intent intent = new Intent(getApplicationContext(), AffiliateInvoiceActivity.class);
        intent.putExtra("owner", username);
        intent.putExtra("crypto", true);
        intent.putExtra("darkMode", darkMode);
        startActivity(intent);
    }

    public void paypalInvoiceList(View view){
        Intent intent = new Intent(getApplicationContext(), AffiliateInvoiceActivity.class);
        intent.putExtra("owner", username);
        intent.putExtra("crypto", false);
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