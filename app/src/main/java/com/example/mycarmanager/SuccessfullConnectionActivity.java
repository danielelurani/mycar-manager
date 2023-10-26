package com.example.mycarmanager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessfullConnectionActivity extends AppCompatActivity {
    public int selectedTheme;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_successfull_connection);
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                SuccessfullConnectionActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                SuccessfullConnectionActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                SuccessfullConnectionActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                SuccessfullConnectionActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }
}