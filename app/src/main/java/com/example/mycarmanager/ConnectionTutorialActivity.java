package com.example.mycarmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectionTutorialActivity extends AppCompatActivity {

    Button tutorialEndButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_tutorial);

        tutorialEndButton = findViewById(R.id.tutorialEndButton);

        tutorialEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // controllo se il bluetooth è acceso
                // in caso negativo avvisa con un alert
                // in caso positivo continua alla prossima activity
                if(!checkBluetooth()){

                    AlertDialog alertDialog = new AlertDialog.Builder(ConnectionTutorialActivity.this, R.style.TutorialAlertDialogStyle).create();
                    alertDialog.setTitle("BLUETOOTH IS OFF!");
                    alertDialog.setMessage("Turn Bluetooth ON in order to continue!");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.bluetooth_off_dialog);
                    alertDialog.show();
                } else {

                    Intent goToDeviceSelectionActivity;

                    goToDeviceSelectionActivity = new Intent(ConnectionTutorialActivity.this, DeviceSelectionActivity.class);
                    startActivity(goToDeviceSelectionActivity);
                    ConnectionTutorialActivity.this.finish();
                }
            }
        });
    }

    // metodo che controlla se il bluetooth è acceso
    private boolean checkBluetooth(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return false;
        } else if (!mBluetoothAdapter.isEnabled()) {
            return false;
        } else {
            return true;
        }
    }
}