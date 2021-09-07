package com.not.byko.jarlvpn_android_adminpanel;

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
        setContentView(R.layout.activity_delete_news);

        TextView idTextView = findViewById(R.id.textView44);
        TextView newsContextTextView = findViewById(R.id.textView45);
        TextView dateTextView = findViewById(R.id.textView46);

        Intent intent = getIntent();

        newsId = intent.getStringExtra("id");

        idTextView.setText("News document id: " + newsId);
        newsContextTextView.setText(intent.getStringExtra("newsContent"));
        dateTextView.setText("Date: " + intent.getStringExtra("newsDate"));
    }

    public void deleteNews(View view){
        WebController webController = new WebController();
        Toast.makeText(view.getContext(), webController.deleteNews(newsId).getMessage(),
                Toast.LENGTH_SHORT).show();
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