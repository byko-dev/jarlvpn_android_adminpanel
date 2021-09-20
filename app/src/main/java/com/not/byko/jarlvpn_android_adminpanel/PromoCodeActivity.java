package com.not.byko.jarlvpn_android_adminpanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        setContentView(R.layout.activity_promo_code);
        setTitle("JarlVPN - code details");

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
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Delete discount code");
        builder.setMessage("Are you sure to delete discount code: "+code+"?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WebController webController = new WebController();
                        Toast.makeText(view.getContext(), webController.deleteCode(code, view).getMessage(), Toast.LENGTH_LONG).show();
                        //back to discount codes fragment //TODO: update discount codes
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