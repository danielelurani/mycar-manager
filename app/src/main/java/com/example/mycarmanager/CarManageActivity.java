package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import static com.example.mycarmanager.LoginActivity.currentCarIndex;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;
import static com.example.mycarmanager.LoginActivity.USER_EXTRA;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import java.io.Serializable;
import de.hdodenhof.circleimageview.CircleImageView;

public class CarManageActivity extends AppCompatActivity {


    private User currentUser;
    private Car currentCar;
    private TextView navBarUsername, navbarEmail, carName, carPlate;
    private Dialog alertsDialog;
    private LinearLayout alertIconLayout, navMenuButton;
    private LinearLayout bottomNavbarGarageButton, bottomNavbarMapButton, bottomNavbarFeaturesButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private ImageView carImage, carBrandImage, carFuelImage, carTypeImage;
    private CircleImageView navbarProfilePic;
    private MaterialButton navbarGarageButton, navbarMapButton, navbarFeaturesButton,
            navbarAccountButton, navbarNewCarButton, navbarColorCorrectionButton,
            navbarLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manage);

        navBarUsername = findViewById(R.id.navBarUsername);
        alertIconLayout = findViewById(R.id.alertsIcon);
        carBrandImage = findViewById(R.id.carBrandImage);
        carFuelImage = findViewById(R.id.carFuelImage);
        carTypeImage = findViewById(R.id.carTypeImage);
        drawerLayout = findViewById(R.id.drawerLayout);
        navMenuButton = findViewById(R.id.navMenuButton);
        navMenu = findViewById(R.id.nav_view);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        carName = findViewById(R.id.carName);
        carImage = findViewById(R.id.carImage);
        carPlate = findViewById(R.id.carPlate);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarMapButton = findViewById(R.id.navbarMapButton);
        navbarFeaturesButton = findViewById(R.id.navbarFeaturesButton);
        navbarAccountButton = findViewById(R.id.navbarAccountButton);
        navbarNewCarButton = findViewById(R.id.navbarNewCarButton);
        navbarColorCorrectionButton = findViewById(R.id.navbarColorCorrectionButton);
        navbarLogoutButton = findViewById(R.id.navbarLogoutButton);

        // prendo i dati dell'utente loggato
        currentUser = users.get(currentUserIndex);

        // aggiorna tutti i dati da visualizzare correttamente
        updateData();

        // metodo che contiene tutti i listeners
        initListeners();
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

        String carBrandName = currentCar.getBrand() + " " + currentCar.getName();
        carName.setText(carBrandName);

        // targa dell'auto
        carPlate.setText(currentCar.getPlate());

        // controlli per il brand della macchina (bmw, vw, toyota ecc.)
        switch (currentCar.getBrand()){
            case "BMW":
                carImage.setImageResource(R.drawable.car_bmw_img);
                carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                break;
            case "Volkswagen":
                carImage.setImageResource(R.drawable.car_volkswagen_img);
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                break;
            case "Jeep":
                carImage.setImageResource(R.drawable.car_jeep_img);
                carBrandImage.setImageResource(R.drawable.logo_jeep_img);
                break;
            default:
                break;
        }

        // controlli per il carburante della macchina (diesel, petrol, electric)
        switch (currentCar.getFuelType()){
            case "diesel":
                carFuelImage.setImageResource(R.drawable.garage1_dieselcar_img);
                break;
            case "petrol":
                carFuelImage.setImageResource(R.drawable.garage1_petrolcar_img);
                break;
            case "electric":
                carFuelImage.setImageResource(R.drawable.garage1_electriccar_img);
                break;
            default:
                break;
        }

        // controlli per il tipo della macchina (citycar, suv, berlina)
        switch (currentCar.getCarType()){
            case "sedan":
                carTypeImage.setImageResource(R.drawable.garage_sedancar_front_icon);
                break;
            case "suv":
                carTypeImage.setImageResource(R.drawable.garage_suv_front_icon);
                break;
            case "citycar":
                carTypeImage.setImageResource(R.drawable.garage_citycar_front_icon);
                break;
            default:
                break;
        }
    }


    public void showAlertsDialog(){

        alertsDialog = new Dialog(CarManageActivity.this);
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

    public void initListeners(){

        navbarLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                finish();
            }
        });

        navbarColorCorrectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, ColorBlindActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarNewCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, AccountActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarGarageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

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
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(CarManageActivity.this, CarGarageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // pulsante per andare alla map activity
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(CarManageActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // pulsante per andare alla features activity
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(CarManageActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
}