package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView oneMonthPriceTextView = findViewById(R.id.textView27);
        TextView sixMonthsDiscountTextView = findViewById(R.id.textView28);

        Intent intent = getIntent();

        oneMonthPriceTextView.setText("One month price: " + intent.getStringExtra("oneMonthPrice"));
        sixMonthsDiscountTextView.setText("Six months discount: " + intent.getStringExtra("sixMonthsValue"));
    }

    public void backToDetails(View view){
        super.onBackPressed();
    }

    public void changeConfiguration(View view){
        EditText sixMonthPriceEditText = findViewById(R.id.editTextNumber2);
        EditText oneMonthPriceEditText = findViewById(R.id.editTextNumber);

        WebController webController = new WebController();

        String message = webController.setGutConfig(oneMonthPriceEditText.getText().toString().isEmpty() ? null :
                        Float.parseFloat(oneMonthPriceEditText.getText().toString()),
                sixMonthPriceEditText.getText().toString().isEmpty() ? null :
                        Integer.parseInt(sixMonthPriceEditText.getText().toString())).getMessage();

        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}