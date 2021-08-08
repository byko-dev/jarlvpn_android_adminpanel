package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    private static EditText login;
    private static EditText password;
    private CheckBox checkBox;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY_PASS = "password";
    private static final String KEY_USERNAME = "username";
    private static final String PREF_NAME = "pref";
    private static final String KEY_REMEMBER = "remember";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fix to NetworkOnMainThreadException
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        login = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        checkBox = findViewById(R.id.checkBox);

        //set saved credentials
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false)) checkBox.setChecked(true);
        else checkBox.setChecked(false);

        login.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        password.setText(sharedPreferences.getString(KEY_PASS, ""));

        login.addTextChangedListener(this);
        password.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(this);


    }

    public void onClickEvent(View view){
        WebController webController = new WebController();
        if (webController.loginToAdminPanel(login.getText().toString(),password.getText().toString())){
            setContentView(R.layout.activity_navigation_drawer);
            Intent intent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
            intent.putExtra("username", login.getText().toString());
            startActivity(intent);
        }else{
            Toast.makeText(view.getContext(), "Password or login wrong!", Toast.LENGTH_LONG).show();
        }

    }

    private void setCredentials(){
        if(checkBox.isChecked()){
            editor.putString(KEY_USERNAME, login.getText().toString().trim());
            editor.putString(KEY_PASS, password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
        }
        editor.apply();
    }


    //implementations of TextWatcher and CompoundButton.OnCheckedChangeListener
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setCredentials();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        setCredentials();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}