package com.not.byko.jarlvpn_android_adminpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateDiscountCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_discount_code);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> billingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.billing_spinner));

        billingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(billingAdapter);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> planAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.plan_spinner));

        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(planAdapter);
    }

    public void createDiscountCode(View view){

    }

    public void backToCode(View view){
        super.onBackPressed();
    }
}