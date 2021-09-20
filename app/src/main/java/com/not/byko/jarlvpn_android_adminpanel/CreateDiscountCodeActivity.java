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

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        setContentView(R.layout.activity_create_discount_code);
        setTitle("JarlVPN - new discount");

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
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Create new discount code");
        builder.setMessage("Are you sure you want to add new discount code: "+promoCode.getText()+"?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WebController webController = new WebController();

                        Toast.makeText(view.getContext(),
                                webController.createNewDiscountCode(promoCode.getText().toString(),
                                        Integer.parseInt(percentage.getText().toString()),
                                        translateBillingStr(spinner.getSelectedItem().toString()),
                                        spinner2.getSelectedItem().toString()).getMessage(),
                                Toast.LENGTH_SHORT).show();
                        //back to discount fragment //TODO: update discount fragment after changes
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