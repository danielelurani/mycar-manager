package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class RegistrationActivity extends AppCompatActivity {

    private TextView loginLink;
    private MaterialButton signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginLink = findViewById(R.id.loginLink);
        signupButton = findViewById(R.id.signupButton);

        // Quando si clicca sull'apposito link si viene mandati alla pagina di login
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToLogin();
            }
        });

        // Quando ci si registra si viene mandati alla pagina di prima connessione all'auto
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToConnectionTutorial;

                goToConnectionTutorial = new Intent(RegistrationActivity.this, ConnectionTutorialActivity.class);
                startActivity(goToConnectionTutorial);
                RegistrationActivity.this.finish();
            }
        });
    }

    private void goToLogin(){
        Intent goToLoginPage;
        goToLoginPage = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(goToLoginPage);
        RegistrationActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        goToLogin();
    }
}