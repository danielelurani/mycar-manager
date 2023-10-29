package com.example.mycarmanager;

import static com.example.mycarmanager.User.users;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationActivity extends AppCompatActivity {

    private TextView loginLink, usernameEmpty, usernameAlreadyUsed, emailEmpty, emailAlreadyUsed;
    private MaterialButton signupButton;
    private TextInputEditText registrationUsername, registrationEmail, registrationPassword, registrationPasswordRepeat;
    private LinearLayout emailEmptyLayout, emailAlreadyUsedLayout, usernameEmptyLayout, usernameAlreadyUsedLayout,
                            passwordReqErrorLayout, passwordsEqualsErrorLayout, emailInvalidFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginLink = findViewById(R.id.loginLink);
        signupButton = findViewById(R.id.signupButton);
        registrationUsername = findViewById(R.id.registrationUsername);
        registrationEmail = findViewById(R.id.registrationEmail);
        registrationPassword = findViewById(R.id.registrationPassword);
        registrationPasswordRepeat = findViewById(R.id.registrationPasswordRepeat);
        usernameEmpty = findViewById(R.id.usernameEmpty);
        usernameEmptyLayout = findViewById(R.id.usernameEmptyLayout);
        usernameAlreadyUsed = findViewById(R.id.usernameAlreadyUsed);
        usernameAlreadyUsedLayout = findViewById(R.id.usernameAlreadyUsedLayout);
        emailEmpty = findViewById(R.id.emailEmpty);
        emailEmptyLayout = findViewById(R.id.emailEmptyLayout);
        emailAlreadyUsed = findViewById(R.id.emailAlreadyUsed);
        emailAlreadyUsedLayout = findViewById(R.id.emailAlreadyUsedLayout);
        emailInvalidFormat = findViewById(R.id.emailInvalidFormat);
        passwordReqErrorLayout = findViewById(R.id.passwordReqErrorLayout);
        passwordsEqualsErrorLayout = findViewById(R.id.passwordEqualsErrorLayout);

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

                if(checkRegistrationData()) {

                    User newUser = new User
                                    (registrationUsername.getText().toString(),
                                    registrationPassword.getText().toString(),
                                    registrationEmail.getText().toString(),
                                    "default_profile_pic");

                    Intent goToConnectionTutorial;

                    goToConnectionTutorial = new Intent(RegistrationActivity.this, ConnectionTutorialActivity.class);
                    startActivity(goToConnectionTutorial);
                    RegistrationActivity.this.finish();
                }
            }
        });
    }

    private void goToLogin(){
        Intent goToLoginPage;
        goToLoginPage = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(goToLoginPage);
        RegistrationActivity.this.finish();
    }

    protected boolean checkRegistrationData(){

        int numberOfUsers = users.size();
        int errors = 0;

        for (int i = 0; i < numberOfUsers; i++) {

            if (registrationUsername.getText().toString().equals("")){
                usernameEmptyLayout.setVisibility(View.VISIBLE);
                errors++;
             }
            else
                usernameEmptyLayout.setVisibility(View.GONE);

            if (registrationUsername.getText().toString().equals(users.get(i).getUsername())){
                usernameAlreadyUsedLayout.setVisibility(View.VISIBLE);
                errors++;
            }
            else
                usernameAlreadyUsedLayout.setVisibility(View.GONE);

            if (registrationEmail.getText().toString().equals("")){
                emailEmptyLayout.setVisibility(View.VISIBLE);
                errors++;
            }
            else
                emailEmptyLayout.setVisibility(View.GONE);

            if (registrationEmail.getText().toString().equals(users.get(i).getEmail())){
                emailAlreadyUsedLayout.setVisibility(View.VISIBLE);
                errors++;
            }
            else
                emailAlreadyUsedLayout.setVisibility(View.GONE);

            if(!registrationEmail.getText().toString().matches(".*[@].*[.].*")){
                emailInvalidFormat.setVisibility(View.VISIBLE);
                errors++;
            } else
                emailInvalidFormat.setVisibility(View.GONE);

            // Controlli input password
            if (registrationPassword.getText().toString().length() < 8) {

                errors++;
                passwordReqErrorLayout.setVisibility(View.VISIBLE);

            } else if (!((registrationPassword.getText().toString()).matches(".*[0-9].*"))) {

                errors++;
                passwordReqErrorLayout.setVisibility(View.VISIBLE);

            } else if (!((registrationPassword.getText().toString()).matches(".*[a-z].*"))) {

                errors++;
                passwordReqErrorLayout.setVisibility(View.VISIBLE);

            } else if (!((registrationPassword.getText().toString()).matches(".*[A-Z].*"))) {

                errors++;
                passwordReqErrorLayout.setVisibility(View.VISIBLE);

            } else if ((registrationPassword.getText().toString()).matches("[a-zA-Z0-9]*")) {

                errors++;
                passwordReqErrorLayout.setVisibility(View.VISIBLE);
            } else {

                passwordReqErrorLayout.setVisibility(View.GONE);
            }

            if (!(registrationPassword.getText().toString().equals(registrationPasswordRepeat.getText().toString()))) {

                errors++;
                passwordsEqualsErrorLayout.setVisibility(View.VISIBLE);
            } else {

                passwordsEqualsErrorLayout.setVisibility(View.GONE);
            }
        }

        return errors <= 0;
    }

    @Override
    public void onBackPressed() {
        goToLogin();
    }
}