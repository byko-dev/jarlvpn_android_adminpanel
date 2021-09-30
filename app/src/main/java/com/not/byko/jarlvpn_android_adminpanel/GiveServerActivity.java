package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class GiveServerActivity extends AppCompatActivity {

    private WebController webController;
    private Spinner citySpinner;
    private Spinner subLengthSpinner;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Dark);
        username = intent.getStringExtra("username");

        setContentView(R.layout.activity_give_server);

        webController = new WebController();

        TextView usernameTextView = findViewById(R.id.textView75);
        citySpinner = findViewById(R.id.spinner3);
        subLengthSpinner = findViewById(R.id.spinner4);

        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.months));
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subLengthSpinner.setAdapter(monthsAdapter);

        ArrayAdapter<String> cityListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                webController.getAvailableRegions(getApplicationContext()).getCity());
        cityListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityListAdapter);

        usernameTextView.setText("Username: " + username);
    }

    public void giveServerForUser(View view){
        Toast.makeText(view.getContext(), webController.giveUserForUser(username,
                citySpinner.getSelectedItem().toString(),
                Integer.parseInt(subLengthSpinner.getSelectedItem().toString().split(" ")[0])).getMessage(),
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