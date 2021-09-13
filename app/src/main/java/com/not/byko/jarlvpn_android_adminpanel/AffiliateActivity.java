package com.not.byko.jarlvpn_android_adminpanel;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliate);

        TextView usernameTextView = findViewById(R.id.textView67);
        TextView totalEarnedTextView = findViewById(R.id.textView68);
        TextView withdrawValueTextView = findViewById(R.id.textView69);
        TextView discountCodeTextView = findViewById(R.id.textView70);

        Intent intent = getIntent();
        webController = new WebController();

        username = intent.getStringExtra("username");

        AffiliateDetailsResponse affiliateDetailsResponse = webController.getAffiliateDetails(username,  getApplicationContext());

        usernameTextView.setText("User: " + username);
        totalEarnedTextView.setText("Total earned: " + affiliateDetailsResponse.getTotalPrice());
        withdrawValueTextView.setText("Withdraw value: "+ affiliateDetailsResponse.getWithdrawPrice());
        discountCodeTextView.setText("Discount code: " + affiliateDetailsResponse.getCode() + " used " + affiliateDetailsResponse.getUsedTime() + " times");
    }

    public void resetAffiliate(View view){
        Toast.makeText(view.getContext(),
                webController.resetWithdrawValue(username).getMessage(), Toast.LENGTH_LONG).show();
    }

    public void deleteAffiliatePermission(View view){
        Toast.makeText(view.getContext(),
                webController.deleteAffiliatePermission(username).getMessage(),
                Toast.LENGTH_LONG).show();
    }

    public void cryptoInvoiceList(View view){

        Intent intent = new Intent(getApplicationContext(), AffiliateInvoiceActivity.class);
        intent.putExtra("owner", username);
        intent.putExtra("crypto", true);
        startActivity(intent);
    }

    public void paypalInvoiceList(View view){
        Intent intent = new Intent(getApplicationContext(), AffiliateInvoiceActivity.class);
        intent.putExtra("owner", username);
        intent.putExtra("crypto", false);
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