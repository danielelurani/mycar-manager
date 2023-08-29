package com.example.mycarmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class DeviceSelectionActivity extends AppCompatActivity {

    private MaterialButton cancelConnectionButton, connectButton;
    private TextView obdDevice, otherDevice;
    private String deviceSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_selection);

        cancelConnectionButton = findViewById(R.id.cancelConnectionButton);
        connectButton = findViewById(R.id.connectButton);
        obdDevice = findViewById(R.id.obdDevice);
        otherDevice = findViewById(R.id.otherDevice);

        obdDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deviceSelected = "obd";
                obdDevice.setBackgroundColor(getResources().getColor(R.color.selected_item_color));
                otherDevice.setBackgroundColor(0x00000000);
            }
        });

        otherDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deviceSelected = "other";
                otherDevice.setBackgroundColor(getResources().getColor(R.color.selected_item_color));
                obdDevice.setBackgroundColor(0x00000000);
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(deviceSelected.equals("obd")){

                    Intent successfullConnection;
                    successfullConnection = new Intent(DeviceSelectionActivity.this, SuccessfullConnectionActivity.class);
                    startActivity(successfullConnection);
                    DeviceSelectionActivity.this.finish();

                } else {

                    Intent failedConnection;
                    failedConnection = new Intent(DeviceSelectionActivity.this, FailedConnectionActivity.class);
                    startActivity(failedConnection);
                    DeviceSelectionActivity.this.finish();
                }
            }
        });

        cancelConnectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(DeviceSelectionActivity.this, R.style.TutorialAlertDialogStyle).create();
                alertDialog.setTitle("ABORT CONNECTION?");
                alertDialog.setMessage("Are you sure you want to abort the connection process?" +
                        " By clicking YES you will be redirect to the Login page!");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
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
}