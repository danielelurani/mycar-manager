package com.example.mycarmanager;

import static com.example.mycarmanager.LoginActivity.currentCarIndex;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;
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
import com.google.android.material.navigation.NavigationView;
import de.hdodenhof.circleimageview.CircleImageView;

public class CarFeaturesActivity extends AppCompatActivity {

    private User currentUser;
    private Car currentCar;
    private LinearLayout alertIconLayout, navMenuButton, bottomNavbarGarageButton;
    private LinearLayout bottomNavbarManageButton, bottomNavbarMapButton, bottomNavbarFeaturesButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private CircleImageView navbarProfilePic;
    private TextView navBarUsername, navbarEmail, activityTitle, carPlate, carDoors, carWeight,
                        carSeats, carGears, carConsumptionData, carEmissions,
                        engineHP, carAcceleration, fuelLevelTitle, fuelLevelActual;
    private Dialog alertsDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_features);

        navBarUsername = findViewById(R.id.navBarUsername);
        alertIconLayout = findViewById(R.id.alertsIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        navMenuButton = findViewById(R.id.navMenuButton);
        navMenu = findViewById(R.id.nav_view);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        activityTitle = findViewById(R.id.activityTitle);
        carPlate = findViewById(R.id.carPlate);
        carDoors = findViewById(R.id.carDoors);
        carWeight = findViewById(R.id.carWeight);
        carSeats = findViewById(R.id.carSeats);
        carAcceleration = findViewById(R.id.carAccelleration);
        carGears = findViewById(R.id.carGears);
        engineHP = findViewById(R.id.engineHP);
        carEmissions = findViewById(R.id.carEmissions);
        carConsumptionData = findViewById(R.id.carConsumptionData);
        fuelLevelTitle = findViewById(R.id.fuelLevelTitle);
        fuelLevelActual = findViewById(R.id.fuelLevelActual);

        // prendo i dati dell'utente loggato
        currentUser = users.get(currentUserIndex);

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
                goToManageActivity = new Intent(CarFeaturesActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
            }
        });

        // pulsante per andare alla map activity
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(CarFeaturesActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
            }
        });

        // pulsante per andare alla features activity
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToGarageActivity;
                goToGarageActivity = new Intent(CarFeaturesActivity.this, CarGarageActivity.class);
                startActivity(goToGarageActivity);
            }
        });
    }

    public void updateData(){

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

            // aggiorna informazioni auto da visualizzare
            currentCar = currentUser.getGarage().get(currentCarIndex);

            // aggiorna titolo pagina con macchina corretta
            activityTitle.setText(currentCar.getBrand() + " " + currentCar.getName() +  " Features");

            // aggiornamento di tutti i dati delle features
            carPlate.setText(currentCar.getPlate());
            carDoors.setText(currentCar.getDoors());
            carWeight.setText(currentCar.getWeight() + " Kg");
            carSeats.setText(currentCar.getSeats());
            carAcceleration.setText(currentCar.getAcceleration() + "s");
            carGears.setText(currentCar.getGears());
            engineHP.setText(currentCar.getHorsepower() + "HP");
            carEmissions.setText(currentCar.getEmissions() + "g/1Km");

            if(currentCar.getFuelType().equals("electric")){

                carConsumptionData.setText(currentCar.getConsumption() + "kWh/100Km");
                fuelLevelTitle.setText("Energy level");
            } else {

                carConsumptionData.setText(currentCar.getConsumption() + "L/100Km");
                fuelLevelTitle.setText("Fuel level");
            }

            fuelLevelActual.setText(currentCar.getFuelOrEnergyLevel() + "%");
    }

    public void showAlertsDialog(){

        alertsDialog = new Dialog(CarFeaturesActivity.this);
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