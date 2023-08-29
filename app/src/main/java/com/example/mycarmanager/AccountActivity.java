package com.example.mycarmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.ClipData;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class AccountActivity extends AppCompatActivity {

    private Dialog editProfileDialog, alertsDialog;
    private LinearLayout alertIconLayout, navMenuButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        Button editProfileButton = findViewById(R.id.editProfileButton);
        alertIconLayout = findViewById(R.id.alertsIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        navMenuButton = findViewById(R.id.navMenuButton);
        navMenu = findViewById(R.id.nav_view);

        navMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);
                navMenu.bringToFront();
            }
        });

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
        editProfileDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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