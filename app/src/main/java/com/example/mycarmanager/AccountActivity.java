package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class AccountActivity extends AppCompatActivity {

    private Dialog editProfileDialog, alertsDialog;
    private LinearLayout alertIconLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button editProfileButton = findViewById(R.id.editProfileButton);
        alertIconLayout = findViewById(R.id.alertsIcon);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showEditProfileDialog();
            }
        });

        alertIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertsDialog();
            }
        });
    }

    public void showAlertsDialog(){

        alertsDialog = new Dialog(AccountActivity.this);
        alertsDialog.setContentView(R.layout.alerts_dialog);
        alertsDialog.getWindow().getAttributes().windowAnimations = R.style.AlertsDialogAnimation;
        alertsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        alertsDialog.getWindow().getAttributes().gravity = Gravity.TOP;

        ImageButton closeDialog = alertsDialog.findViewById(R.id.closeDialog);

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alertsDialog.dismiss();
            }
        });

        alertsDialog.show();
    }

    public void showEditProfileDialog(){

        editProfileDialog = new Dialog(AccountActivity.this);
        editProfileDialog.setContentView(R.layout.edit_profile_dialog);
        editProfileDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        ImageButton closeDialog = editProfileDialog.findViewById(R.id.closeDialog);
        Button saveProfile = editProfileDialog.findViewById(R.id.saveProfile);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileDialog.dismiss();
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileDialog.dismiss();
            }
        });

        editProfileDialog.show();
    }
}