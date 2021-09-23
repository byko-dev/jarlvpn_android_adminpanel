package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.not.byko.jarlvpn_android_adminpanel.models.AffiliatePayments;
import com.not.byko.jarlvpn_android_adminpanel.tools.PaymentsAdapter;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

import java.util.ArrayList;
import java.util.List;

public class AffiliateInvoiceActivity extends AppCompatActivity {

    private PaymentsAdapter paymentsAdapter;
    private List<String> usernameList = new ArrayList<>();
    private List<String> purchaseDateList = new ArrayList<>();
    private List<String> daysList = new ArrayList<>();
    private List<String> idPaymentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Dark);

        setContentView(R.layout.activity_affiliate_invoice);

        String owner = intent.getStringExtra("owner");
        Boolean crypto = intent.getBooleanExtra("crypto",false);
        setTitle( crypto ? "Crypto [" + owner + "]" : "Paypal [" +owner + "]");

        List<AffiliatePayments> affiliatePayments = new ArrayList<>();
        WebController webController = new WebController();

        if(crypto) affiliatePayments.addAll(webController.getAffiliateCryptoInvoices(owner, getApplicationContext()));
        else affiliatePayments.addAll(webController.getAffiliatePayPalInvoices(owner, getApplicationContext()));

        for(AffiliatePayments payment : affiliatePayments){
            usernameList.add(payment.getUsername());
            purchaseDateList.add(payment.getPurchaseDate());
            daysList.add(payment.getDays().toString());
            idPaymentList.add(payment.getIdPayment());
        }

        ListView listView = findViewById(R.id.affiliate_listView_payments);

        paymentsAdapter = new PaymentsAdapter(getApplicationContext(), usernameList, purchaseDateList,
                daysList, idPaymentList);

        listView.setAdapter(paymentsAdapter);
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