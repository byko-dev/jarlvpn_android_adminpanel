package com.not.byko.jarlvpn_android_adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.not.byko.jarlvpn_android_adminpanel.tools.WebController;

public class ChangePasswordActivity extends AppCompatActivity {

    private String username;
    private EditText passwordEditText;
    private EditText retypePasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        TextView usernameTextView = findViewById(R.id.textView52);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        retypePasswordEditText = findViewById(R.id.editTextTextPassword3);
        CheckBox hidePasswordCheckBox = findViewById(R.id.checkBox3);

        Intent intent = getIntent();

        username = intent.getStringExtra("username");

        usernameTextView.setText("User: " + username);

        hidePasswordCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(hidePasswordCheckBox.isChecked()){
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    retypePasswordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                    retypePasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    public void changePasswordForUser(View view){
        if(passwordEditText.getText().toString().equals(retypePasswordEditText.getText().toString())){
            WebController webController = new WebController();
            Toast.makeText(view.getContext(),
                    webController.changePasswordForUser(username, passwordEditText.getText().toString()).getMessage(),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(view.getContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
        }
    }


    public void backToUsersList(View view){
        super.onBackPressed();
    }
}