package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class FailedConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_connection);

        MaterialButton tryAgainButton = findViewById(R.id.tryagainButton);

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
}