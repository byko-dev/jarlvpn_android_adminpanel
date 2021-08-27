package com.not.byko.jarlvpn_android_adminpanel;

import static java.time.LocalDate.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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

    public void backToNews(View view){
        super.onBackPressed();
    }
}