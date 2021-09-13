package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class PromoCodeActivity extends AppCompatActivity {

    private static String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo_code);
        setTitle("JarlVPN - code details");

        Intent intent = getIntent();

        TextView codeTextView = findViewById(R.id.textView20);
        TextView percentageTextView = findViewById(R.id.textView21);
        TextView planTextView = findViewById(R.id.textView22);
        TextView billingTextView = findViewById(R.id.textView23);
        TextView usedTimesTextView = findViewById(R.id.textView24);
        TextView ownerIdTextView = findViewById(R.id.textView25);

        code = intent.getStringExtra("code");

        codeTextView.setText("Discount code name: " + code);
        percentageTextView.setText("Percentage: " + intent.getStringExtra("percentage") + "%");
        planTextView.setText("Plan: " + intent.getStringExtra("plan"));
        billingTextView.setText("Billing: " + intent.getStringExtra("billing"));
        usedTimesTextView.setText("Code used: " + intent.getStringExtra("usedTimes") + " times");
        ownerIdTextView.setText("Code OwnerId: " +
                (intent.getStringExtra("ownerId").isEmpty() ? "admin" : intent.getStringExtra("ownerId")));
    }

    public void deleteDiscountCode(View view){
        WebController webController = new WebController();
        Toast.makeText(view.getContext(), webController.deleteCode(code, view).getMessage(), Toast.LENGTH_LONG).show();
        super.onBackPressed();
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