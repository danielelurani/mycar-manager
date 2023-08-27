package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

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

        Dialog editProfileDialog = new Dialog(this);
        editProfileDialog.setContentView(R.layout.edit_profile_dialog);

        ImageButton closeDialog = findViewById(R.id.closeDialog);
        Button saveProfile = findViewById(R.id.saveProfile);

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
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