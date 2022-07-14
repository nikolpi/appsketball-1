package com.example.appsketballandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    String username;
    String email;
    String password;
    String passwordConfirm;
    Boolean isAdmin;


    EditText usernameInput;
    EditText emailInput;
    EditText passwordInput;
    EditText passwordConfInput;
    CheckBox isAdminInput;


    Button registerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameInput = (EditText) findViewById(R.id.register_username);
        emailInput = (EditText) findViewById(R.id.register_email);
        passwordInput = (EditText) findViewById(R.id.register_password);
        passwordConfInput = (EditText) findViewById(R.id.register_password_conf);
        registerB = (Button) findViewById(R.id.register_btn);
        isAdminInput = (CheckBox) findViewById(R.id.isAdminCheckBox);

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();
                passwordConfirm = passwordConfInput.getText().toString();
                email = emailInput.getText().toString();
                isAdmin = isAdminInput.isChecked();

                if(username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please complete all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(passwordConfirm)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(RegisterAccount.checkIfRegistered(username)){
                    Toast.makeText(RegisterActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                RegisterAccount.register(username, email, password, isAdmin);
                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));

            }
        });

        TextView haveAcc = findViewById(R.id.register_havac);
        haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }
}