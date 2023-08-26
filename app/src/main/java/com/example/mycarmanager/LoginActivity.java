package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView signupLink;
    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupLink = findViewById(R.id.signupLink);
        testButton = findViewById(R.id.testButton);

        // Quando si clicca sull'apposito link si viene mandati alla pagina di registrazione
        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToSignupPage;
                goToSignupPage = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(goToSignupPage);
            }
        });

        // BOTTONE DI TEST PER TESTARE IMMEDIATAMENTE LE NUOVE ACTIVITY
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent testIntent;
                testIntent = new Intent(LoginActivity.this, CarMapActivity.class);
                startActivity(testIntent);
            }
        });
    }

    // finishaffinity rimuove le connessioni con lo stack, finish chiude l'applicazione
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}