package com.example.mycarmanager;

import static com.example.mycarmanager.LoginActivity.currentCarIndex;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private User currentUser;
    private Car currentCar;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private Dialog alertsDialog;
    private LinearLayout alertIconLayout, navMenuButton, bottomNavbarGarageButton;
    private LinearLayout bottomNavbarManageButton, bottomNavbarMapButton, bottomNavbarFeaturesButton;
    private TextView activityTitle, parkingSpotSubtext, navBarUsername, navbarEmail;
    private CircleImageView navbarProfilePic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_map);

        alertIconLayout = findViewById(R.id.alertsIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        navMenuButton = findViewById(R.id.navMenuButton);
        navMenu = findViewById(R.id.nav_view);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        activityTitle = findViewById(R.id.activityTitle);
        parkingSpotSubtext = findViewById(R.id.parkingSpotSubtext);
        navBarUsername = findViewById(R.id.navBarUsername);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);

        // prendo i dati dell'utente loggato e della macchina attuale
        currentUser = users.get(currentUserIndex);
        currentCar = currentUser.getGarage().get(currentCarIndex);

        // aggiorna tutti i dati da visualizzare correttamente
        updateData();

        // mostare la barra di navigazione laterale
        navMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);
                navMenu.bringToFront();
            }
        });

        // mostra il dialog degli alert
        alertIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertsDialog();
            }
        });

        // pulsante per andare alla manage activity
        bottomNavbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(CarMapActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
            }
        });

        // pulsante per andare alla garage activity
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToGarageActivity;
                goToGarageActivity = new Intent(CarMapActivity.this, CarGarageActivity.class);
                startActivity(goToGarageActivity);
            }
        });

        // pulsante per andare alla features activity
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(CarMapActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapActual);
        mapFragment.getMapAsync(this);

    }

    public void updateData(){

        //activityTitle.setText(currentCar.getBrand() + " " + currentCar.getName() + " Map");
        parkingSpotSubtext.setText(currentCar.getBrand() + " " + currentCar.getName() + " " + currentCar.getPlate());

        // aggiorna informazioni utente loggato nella navbar
        navBarUsername.setText(currentUser.getUsername());
        navbarEmail.setText(currentUser.getEmail());

        // Imposta la corretta immagine del profilo
        switch (currentUser.getImgPath()) {
            case "default_profile_pic":
                navbarProfilePic.setImageResource(R.drawable.default_profile_pic);
                break;
            case "profile_pic_2":
                navbarProfilePic.setImageResource(R.drawable.profile_pic_2);
                break;
            case "profile_pic_3":
                navbarProfilePic.setImageResource(R.drawable.profile_pic_3);
                break;
            case "profile_pic42":
                navbarProfilePic.setImageResource(R.drawable.profile_pic_4);
                break;
            case "profile_pic_5":
                navbarProfilePic.setImageResource(R.drawable.profile_pic_5);
                break;
            case "profile_pic_6":
                navbarProfilePic.setImageResource(R.drawable.profile_pic_6);
                break;
            default:
                break;
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        double latitude = currentUser.getGarage().get(currentCarIndex).getLatitude();
        double longitude = currentUser.getGarage().get(currentCarIndex).getLongitude();
        String title = currentUser.getGarage().get(currentCarIndex).getBrand() +
                        " " +
                        currentUser.getGarage().get(currentCarIndex).getName();

        LatLng position = new LatLng(latitude, longitude);

        googleMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title));

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16));
    }

    public void showAlertsDialog(){

        alertsDialog = new Dialog(CarMapActivity.this);
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
}