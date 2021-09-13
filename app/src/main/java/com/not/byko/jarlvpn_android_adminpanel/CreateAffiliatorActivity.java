package com.not.byko.jarlvpn_android_adminpanel;

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

public class CreateAffiliatorActivity extends AppCompatActivity {

    private String username;
    private EditText promoCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_affiliator);
        setTitle("JarlVPN - new partner");

        TextView usernameTextView = findViewById(R.id.textView53);
        promoCodeEditText = findViewById(R.id.editTextTextPersonName4);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        usernameTextView.setText("Selected user: " + username);
    }

    public void newAffiliatePartner(View view){
        WebController webController = new WebController();

        Toast.makeText(view.getContext(),
                webController.createNewAffiliator(username, promoCodeEditText.getText().toString()).getMessage(),
                Toast.LENGTH_LONG).show();
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