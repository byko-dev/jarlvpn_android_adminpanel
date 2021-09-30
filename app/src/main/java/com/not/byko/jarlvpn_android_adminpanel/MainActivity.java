package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class MainActivity extends AppCompatActivity implements TextWatcher, CompoundButton.OnCheckedChangeListener{

    private static EditText login;
    private static EditText password;
    private CheckBox checkBox;
    private CheckBox darkModeCheckBox;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String KEY_PASS = "password";
    private static final String KEY_USERNAME = "username";
    private static final String PREF_NAME = "pref";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_DARK_MODE = "dark_mode";

    private String captchaResponse;

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
        darkModeCheckBox = findViewById(R.id.checkBox35);

        //set saved credentials
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false)) checkBox.setChecked(true);
        else checkBox.setChecked(false);

        login.setText(sharedPreferences.getString(KEY_USERNAME, ""));
        password.setText(sharedPreferences.getString(KEY_PASS, ""));
        darkModeCheckBox.setChecked(sharedPreferences.getBoolean(KEY_DARK_MODE, false));

        login.addTextChangedListener(this);
        password.addTextChangedListener(this);
        checkBox.setOnCheckedChangeListener(this);
        darkModeCheckBox.setOnCheckedChangeListener(this);

        //captcha executor
        CheckBox checkBoxCaptcha = findViewById(R.id.checkBox33);

        checkBoxCaptcha.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                SafetyNet.getClient(getApplicationContext()).verifyWithRecaptcha(getResources().getString(R.string.site_key_captcha))
                        .addOnSuccessListener(getMainExecutor(),
                                new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                                    @Override
                                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                        String userResponseToken = response.getTokenResult();
                                        if (!userResponseToken.isEmpty()) {
                                            captchaResponse = userResponseToken;
                                            checkBoxCaptcha.setChecked(true);
                                        }
                                    }
                                })
                        .addOnFailureListener(getMainExecutor(), new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                if (e instanceof ApiException) {
                                    ApiException apiException = (ApiException) e;
                                    int statusCode = apiException.getStatusCode();
                                    Toast.makeText(getApplicationContext(), "Invalid Captcha! Code: " + statusCode,
                                            Toast.LENGTH_LONG).show();
                                    checkBoxCaptcha.setChecked(false);
                                } else {
                                    // A different, unknown type of error occurred.
                                    Toast.makeText(getApplicationContext(), "Captcha error!",
                                            Toast.LENGTH_LONG).show();
                                    checkBoxCaptcha.setChecked(false);
                                }
                            }
                        });
            }
        });
    }

    public void onClickEvent(View view){
        WebController webController = new WebController();
        if (webController.loginToAdminPanel(login.getText().toString(),password.getText().toString(), captchaResponse)){
            setContentView(R.layout.activity_navigation_drawer);
            Intent intent = new Intent(MainActivity.this, NavigationDrawerActivity.class);
            intent.putExtra("username", login.getText().toString());
            intent.putExtra("darkMode", darkModeCheckBox.isChecked());
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
            editor.putBoolean(KEY_DARK_MODE, darkModeCheckBox.isChecked());
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.remove(KEY_DARK_MODE);
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