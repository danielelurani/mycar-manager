package com.example.mycarmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class DeviceSelectionActivity extends AppCompatActivity {

    private MaterialButton cancelConnectionButton, connectButton;
    public int selectedTheme;
    public SharedPreferences sharedPreferences;
    private String deviceSelected;
    private TextView obdDevice, otherDevice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_device_selection);

        // Inizializza gli elementi della pagina
        initViews();

        // Inizializzazione listeners
        initListeners();
    }

    private void initViews() {
        cancelConnectionButton = findViewById(R.id.cancelConnectionButton);
        connectButton = findViewById(R.id.connectButton);
        obdDevice = findViewById(R.id.obdDevice);
        otherDevice = findViewById(R.id.otherDevice);
    }

    private void initListeners() {
        // Listener selezione device funzionante [tabella - alto sx]
        obdDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceSelected = "obd";
                obdDevice.setBackgroundColor(R.attr.greenBorder);
                otherDevice.setBackgroundColor(0x00000000);
            }
        });

        // Listener selezione device non funzionante [tabella - basso sx]
        otherDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceSelected = "other";
                otherDevice.setBackgroundColor(R.attr.greenBorder);
                obdDevice.setBackgroundColor(0x00000000);
            }
        });

        // Listener tasto connessione
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deviceSelected.equals("obd")) {
                    Intent successfullConnection;
                    successfullConnection = new Intent(DeviceSelectionActivity.this, SuccessfullConnectionActivity.class);
                    startActivity(successfullConnection);
                    DeviceSelectionActivity.this.finish();

                }
                else {
                    Intent failedConnection;
                    failedConnection = new Intent(DeviceSelectionActivity.this, FailedConnectionActivity.class);
                    startActivity(failedConnection);
                    DeviceSelectionActivity.this.finish();
                }
            }
        });

        // Listener tasto indietro
        cancelConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(DeviceSelectionActivity.this, R.style.TutorialAlertDialogStyle).create();
                alertDialog.setTitle("ABORT CONNECTION?");
                alertDialog.setMessage("Are you sure you want to abort the connection process?" +
                        " By clicking YES you will be redirect to the Login page!");

                // Tasto negativo [continua con la connessione]
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                // Tasto affermativo [esci dalla pagina]
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent returnToLogin;
                                returnToLogin = new Intent(DeviceSelectionActivity.this, LoginActivity.class);
                                startActivity(returnToLogin);
                                DeviceSelectionActivity.this.finish();
                            }
                        });
                alertDialog.show();
            }
        });
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                DeviceSelectionActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                DeviceSelectionActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                DeviceSelectionActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                DeviceSelectionActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }
}