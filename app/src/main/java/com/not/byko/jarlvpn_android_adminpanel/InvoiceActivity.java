package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class InvoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        TextView ownerTextView = findViewById(R.id.textView59);
        TextView priceTextView = findViewById(R.id.textView60);
        TextView transactionIdTextView = findViewById(R.id.textView61);
        TextView subTypeTextView = findViewById(R.id.textView62);
        TextView cryptocurrencyTextView = findViewById(R.id.textView64);
        TextView paidInvoiceTextView = findViewById(R.id.textView65);
        TextView purchaseDateTextView = findViewById(R.id.textView66);

        Intent intent = getIntent();

        ownerTextView.setText("Invoice owner: " + intent.getStringExtra("ownersMail"));
        priceTextView.setText("Price: " + intent.getFloatExtra("price", 0.00f));
        transactionIdTextView.setText("Transaction ID: " + intent.getStringExtra("transactionId"));
        subTypeTextView.setText("Subscription type: " + intent.getStringExtra("subType"));
        purchaseDateTextView.setText("Date: " + intent.getStringExtra("purchaseDate"));

        if(intent.getBooleanExtra("paidInvoice", false)){
            paidInvoiceTextView.setText("Invoice paid! :D");
            paidInvoiceTextView.setTextColor(getColor(R.color.green));
        }else{
            paidInvoiceTextView.setText("Invoice not paid yet");
            paidInvoiceTextView.setTextColor(getColor(R.color.red));
        }

        String cryptocurrency = intent.getStringExtra("cryptocurrency");

        if(!cryptocurrency.isEmpty()) cryptocurrencyTextView.setText("Cryptocurrency: " + cryptocurrency);
        else cryptocurrencyTextView.setVisibility(View.GONE);
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