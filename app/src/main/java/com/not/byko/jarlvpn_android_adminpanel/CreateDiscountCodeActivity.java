package com.not.byko.jarlvpn_android_adminpanel;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class CreateDiscountCodeActivity extends AppCompatActivity {

    private Spinner spinner;
    private Spinner spinner2;
    private EditText promoCode;
    private EditText percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_discount_code);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> billingAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.billing_spinner));

        billingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(billingAdapter);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<String> planAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.plan_spinner));

        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(planAdapter);

        promoCode = findViewById(R.id.editTextTextPersonName3);
        percentage = findViewById(R.id.editTextNumberSigned);

    }

    public void createDiscountCode(View view){
        WebController webController = new WebController();

        Toast.makeText(view.getContext(),
                webController.createNewDiscountCode(promoCode.getText().toString(),
                        Integer.parseInt(percentage.getText().toString()),
                        translateBillingStr(spinner.getSelectedItem().toString()),
                        spinner2.getSelectedItem().toString()).getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    private int translateBillingStr(String billing){
        switch (billing){
            case "All":
                return -1;
            default:
                return Integer.parseInt(billing.split(" ")[0]);
        }
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