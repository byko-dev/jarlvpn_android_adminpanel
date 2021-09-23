package com.not.byko.jarlvpn_android_adminpanel;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
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

        Intent intent = getIntent();
        if (intent.getBooleanExtra("darkMode", false))
            setTheme(R.style.Theme_AppCompat_Dark);

        setContentView(R.layout.activity_change_password);
        setTitle("JarlVPN - new password");

        TextView usernameTextView = findViewById(R.id.textView52);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        retypePasswordEditText = findViewById(R.id.editTextTextPassword3);
        CheckBox hidePasswordCheckBox = findViewById(R.id.checkBox3);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setCancelable(true);
        builder.setTitle("Change password");
        builder.setMessage("Are you sure to change password for user: " + username + "?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(passwordEditText.getText().toString().equals(retypePasswordEditText.getText().toString())){
                            WebController webController = new WebController();
                            Toast.makeText(view.getContext(),
                                    webController.changePasswordForUser(username, passwordEditText.getText().toString()).getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(view.getContext(), "Passwords doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int witch){
                Toast.makeText(view.getContext(), "Password don't changed!", Toast.LENGTH_SHORT).show();
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