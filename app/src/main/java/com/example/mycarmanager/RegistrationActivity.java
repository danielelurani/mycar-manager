package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    private TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginLink = findViewById(R.id.loginLink);

        // Quando si clicca sull'apposito link si viene mandati alla pagina di login
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goToLogin();
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