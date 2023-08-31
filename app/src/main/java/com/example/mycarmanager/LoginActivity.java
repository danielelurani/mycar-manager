package com.example.mycarmanager;

import static com.example.mycarmanager.User.users;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextView signupLink;
    private TextInputEditText usernameLogin, passwordLogin;
    private User user;
    private Button testButton;
    private MaterialButton loginButton;
    public static final String USER_EXTRA = "com.example.mycarmanager.user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        User loggedUser = new User("user", "user", "user@email.it", "");

        signupLink = findViewById(R.id.signupLink);
        testButton = findViewById(R.id.testButton);
        loginButton = findViewById(R.id.loginButton);
        usernameLogin = findViewById(R.id.usernameLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        // istanzio l'oggetto utente
        user = new User();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkUserData()){

                    updateUserData();

                    Intent goToHomePage;
                    goToHomePage = new Intent(LoginActivity.this, CarGarageActivity.class);
                    goToHomePage.putExtra(USER_EXTRA, user);
                    startActivity(goToHomePage);
                } else {

                }
            }
        });

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
                //testIntent = new Intent(LoginActivity.this, AccountActivity.class);
                testIntent = new Intent(LoginActivity.this, ColourBlindActivity.class);
                startActivity(testIntent);
            }
        });
    }

    protected boolean checkUserData(){
        int numberOfUsers = users.size();

        for (int i = 0; i < numberOfUsers; i++){
            if(usernameLogin.getText().toString().equals(users.get(i).getUsername()) &&
                    passwordLogin.getText().toString().equals(users.get(i).getPassword())){

                return true;
            }
        }
        return false;
    }

    protected void updateUserData(){

        String username = usernameLogin.getText().toString();
        this.user.setUsername(username);

        String password = passwordLogin.getText().toString();
        this.user.setPassword(password);
    }

    // finishaffinity rimuove le connessioni con lo stack, finish chiude l'applicazione
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}