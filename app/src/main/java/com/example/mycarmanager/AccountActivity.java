package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AccountActivity extends AppCompatActivity {

    private Dialog editProfileDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button editProfileButton = findViewById(R.id.editProfileButton);

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();
            }
        });
    }

    public void showDialog(){

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