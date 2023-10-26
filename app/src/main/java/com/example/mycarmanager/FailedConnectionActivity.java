package com.example.mycarmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class FailedConnectionActivity extends AppCompatActivity {
    public int selectedTheme;
    private MaterialButton tryAgainButton;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_failed_connection);

        // Inizializza gli elementi della pagina
        initViews();

        // Inizializzazione listeners
        initListeners();
    }

    private void initViews () {
        tryAgainButton = findViewById(R.id.tryagainButton);
    }

    private void initListeners() {
        // Listener pulsante riprova
        tryAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnToDeviceSelection;
                returnToDeviceSelection = new Intent(FailedConnectionActivity.this, DeviceSelectionActivity.class);
                startActivity(returnToDeviceSelection);
                FailedConnectionActivity.this.finish();
            }
        });
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                FailedConnectionActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                FailedConnectionActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                FailedConnectionActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                FailedConnectionActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }
}