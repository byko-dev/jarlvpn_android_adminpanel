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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNewsActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if(intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Light_NoActionBar_Dark);

        setContentView(R.layout.activity_create_news);
        setTitle("JarlVPN - new news");

        TextView date = findViewById(R.id.textView31);

        editText = findViewById(R.id.editTextTextPersonName2);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        date.setText("Date: " + formatter.format(date1));
    }

    public void createNewNews(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Create new news");
        builder.setMessage("Are you sure you want to add new news?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WebController webController = new WebController();
                        Toast.makeText(view.getContext(),
                                webController.createNews(editText.getText().toString()).getMessage(), Toast.LENGTH_SHORT).show();
                        //back to news fragment //TODO: update news fragment after changes
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