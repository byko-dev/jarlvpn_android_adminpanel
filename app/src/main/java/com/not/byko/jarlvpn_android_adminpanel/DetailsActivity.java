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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        setContentView(R.layout.activity_details);

        setTitle("JarlVPN - new details");

        TextView oneMonthPriceTextView = findViewById(R.id.textView27);
        TextView sixMonthsDiscountTextView = findViewById(R.id.textView28);

        oneMonthPriceTextView.setText("One month price: " + intent.getStringExtra("oneMonthPrice"));
        sixMonthsDiscountTextView.setText("Six months discount: " + intent.getStringExtra("sixMonthsValue"));
    }

    public void changeConfiguration(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Change details JarlVPN");
        builder.setMessage("Are you sure you want to change JarlVPN prices?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText sixMonthPriceEditText = findViewById(R.id.editTextNumber2);
                        EditText oneMonthPriceEditText = findViewById(R.id.editTextNumber);

                        WebController webController = new WebController();

                        String message = webController.setGutConfig(oneMonthPriceEditText.getText().toString().isEmpty() ? null :
                                        Float.parseFloat(oneMonthPriceEditText.getText().toString()),
                                sixMonthPriceEditText.getText().toString().isEmpty() ? null :
                                        Integer.parseInt(sixMonthPriceEditText.getText().toString())).getMessage();

                        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
                        //back to details fragment //TODO: update details fragment after changes
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