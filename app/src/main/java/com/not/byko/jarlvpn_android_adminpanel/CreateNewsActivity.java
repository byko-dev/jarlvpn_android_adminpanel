package com.not.byko.jarlvpn_android_adminpanel;

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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewsActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);

        TextView date = findViewById(R.id.textView31);

        editText = findViewById(R.id.editTextTextPersonName2);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        date.setText("Date: " + formatter.format(date1));
    }

    public void createNewNews(View view){
        WebController webController = new WebController();
        Toast.makeText(view.getContext(),
                webController.createNews(editText.getText().toString()).getMessage(), Toast.LENGTH_SHORT).show();
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