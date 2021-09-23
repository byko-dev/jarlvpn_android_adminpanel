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
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class DeleteNewsActivity extends AppCompatActivity {

    private String newsId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Dark);

        setContentView(R.layout.activity_delete_news);
        setTitle("JarlVPN - news");

        TextView idTextView = findViewById(R.id.textView44);
        TextView newsContextTextView = findViewById(R.id.textView45);
        TextView dateTextView = findViewById(R.id.textView46);

        newsId = intent.getStringExtra("id");

        idTextView.setText("News document id: " + newsId);
        newsContextTextView.setText(intent.getStringExtra("newsContent"));
        dateTextView.setText("Date: " + intent.getStringExtra("newsDate"));
    }

    public void deleteNews(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Delete news");
        builder.setMessage("Are you sure you want to remove this news?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WebController webController = new WebController();
                        Toast.makeText(view.getContext(), webController.deleteNews(newsId).getMessage(),
                                Toast.LENGTH_SHORT).show();

                        onBackPressed();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch){
                //Do nothing
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