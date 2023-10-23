package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
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



public class CarGarageActivity extends AppCompatActivity {

    private User currentUser;
    private Car currentCar;
    private TextView navBarUsername, navbarEmail, carName, carPlate, carBrandText;
    private TextView carFuelText, carTypeText;
    private Dialog alertsDialog;
    private ImageButton newCarButton;
    private LinearLayout alertIconLayout, navMenuButton, bottomNavbarGarageButton;
    private LinearLayout bottomNavbarManageButton, bottomNavbarMapButton, bottomNavbarFeaturesButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private CircleImageView navbarProfilePic;
    private ImageView carImage, garageLeftArrow, garageRightArrow, carBrandImage;
    private ImageView carFuelImage, carTypeImage;
    private MaterialButton navbarGarageButton, navbarManageButton, navbarMapButton, navbarFeaturesButton,
                            navbarAccountButton, navbarNewCarButton, navbarColorCorrectionButton,
                            navbarLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_garage);

        navBarUsername = findViewById(R.id.navBarUsername);
        alertIconLayout = findViewById(R.id.alertsIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        navMenuButton = findViewById(R.id.navMenuButton);
        navMenu = findViewById(R.id.nav_view);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        carName = findViewById(R.id.carName);
        carImage = findViewById(R.id.carImage);
        carPlate = findViewById(R.id.carPlate);
        garageLeftArrow = findViewById(R.id.garageLeftArrow);
        garageRightArrow = findViewById(R.id.garageRightArrow);
        carBrandImage = findViewById(R.id.carBrandImage);
        carBrandText = findViewById(R.id.carBrandText);
        carFuelImage = findViewById(R.id.carFuelImage);
        carFuelText = findViewById(R.id.carFuelText);
        carTypeImage = findViewById(R.id.carTypeImage);
        carTypeText = findViewById(R.id.carTypeText);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        newCarButton = findViewById(R.id.newCarButton);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarManageButton = findViewById(R.id.navbarManageButton);
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
                carBrandText.setText("BMW");
                break;
            case "Volkswagen":
                carImage.setImageResource(R.drawable.car_volkswagen_img);
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                carBrandText.setText("Volkswagen");
                break;
            case "Jeep":
                carImage.setImageResource(R.drawable.car_jeep_img);
                carBrandImage.setImageResource(R.drawable.logo_jeep_img);
                carBrandText.setText("Jeep");
                break;
            default:
                break;
        }

        // controlli per il carburante della macchina (diesel, petrol, electric)
        switch (currentCar.getFuelType()){
            case "diesel":
                carFuelText.setText("Diesel");
                carFuelImage.setImageResource(R.drawable.garage1_dieselcar_img);
                break;
            case "petrol":
                carFuelText.setText("Petrol");
                carFuelImage.setImageResource(R.drawable.garage1_petrolcar_img);
                break;
            case "electric":
                carFuelText.setText("Electric");
                carFuelImage.setImageResource(R.drawable.garage1_electriccar_img);
                break;
            default:
                break;
        }

        // controlli per il tipo della macchina (citycar, suv, berlina)
        switch (currentCar.getCarType()){
            case "sedan":
                carTypeText.setText("Sedan");
                carTypeImage.setImageResource(R.drawable.garage_sedancar_front_icon);
                break;
            case "suv":
                carTypeText.setText("Suv");
                carTypeImage.setImageResource(R.drawable.garage_suv_front_icon);
                break;
            case "citycar":
                carTypeText.setText("Citycar");
                carTypeImage.setImageResource(R.drawable.garage_citycar_front_icon);
                break;
            default:
                break;
        }

        // scorre in avanti la lista di auto
        // se arriva all'ultima auto e viene cliccato torna alla prima
        garageRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentCarIndex == (currentUser.getGarage().size()-1)){

                    currentCar = currentUser.getGarage().get(0);
                    currentCarIndex = 0;

                } else {

                    currentCar = currentUser.getGarage().get(currentCarIndex+1);
                    currentCarIndex = currentCarIndex+1;
                }

                String carBrandName = currentCar.getBrand() + " " + currentCar.getName();
                carName.setText(carBrandName);

                // targa dell'auto
                carPlate.setText(currentCar.getPlate());

                // controlli per il brand della macchina (bmw, vw, toyota ecc.)
                switch (currentCar.getBrand()){
                    case "BMW":
                        carImage.setImageResource(R.drawable.car_bmw_img);
                        carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                        carBrandText.setText("BMW");
                        break;
                    case "Volkswagen":
                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                        carBrandText.setText("Volkswagen");
                        break;
                    case "Jeep":
                        carImage.setImageResource(R.drawable.car_jeep_img);
                        carBrandImage.setImageResource(R.drawable.logo_jeep_img);
                        carBrandText.setText("Jeep");
                        break;
                    default:
                        break;
                }

                // controlli per il carburante della macchina (diesel, petrol, electric)
                switch (currentCar.getFuelType()){
                    case "diesel":
                        carFuelText.setText("Diesel");
                        carFuelImage.setImageResource(R.drawable.garage1_dieselcar_img);
                        break;
                    case "petrol":
                        carFuelText.setText("Petrol");
                        carFuelImage.setImageResource(R.drawable.garage1_petrolcar_img);
                        break;
                    case "electric":
                        carFuelText.setText("Electric");
                        carFuelImage.setImageResource(R.drawable.garage1_electriccar_img);
                        break;
                    default:
                        break;
                }

                // controlli per il tipo della macchina (citycar, suv, berlina)
                switch (currentCar.getCarType()){
                    case "sedan":
                        carTypeText.setText("Sedan");
                        carTypeImage.setImageResource(R.drawable.garage_sedancar_front_icon);
                        break;
                    case "suv":
                        carTypeText.setText("Suv");
                        carTypeImage.setImageResource(R.drawable.garage_suv_front_icon);
                        break;
                    case "citycar":
                        carTypeText.setText("Citycar");
                        carTypeImage.setImageResource(R.drawable.garage_citycar_front_icon);
                        break;
                    default:
                        break;
                }
            }
        });

        // scorre all'indietro la lista di auto
        // se arriva alla prima auto e viene cliccato torna all'ultima
        garageLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(currentCarIndex == (0)){

                    currentCar = currentUser.getGarage().get(currentUser.getGarage().size()-1);
                    currentCarIndex = currentUser.getGarage().size()-1;

                } else {

                    currentCar = currentUser.getGarage().get(currentCarIndex-1);
                    currentCarIndex = currentCarIndex-1;
                }

                String carBrandName = currentCar.getBrand() + " " + currentCar.getName();
                carName.setText(carBrandName);

                // targa dell'auto
                carPlate.setText(currentCar.getPlate());

                // controlli per il brand della macchina (bmw, vw, toyota ecc.)
                switch (currentCar.getBrand()){
                    case "BMW":
                        carImage.setImageResource(R.drawable.car_bmw_img);
                        carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                        carBrandText.setText("BMW");
                        break;
                    case "Volkswagen":
                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                        carBrandText.setText("Volkswagen");
                        break;
                    case "Jeep":
                        carImage.setImageResource(R.drawable.car_jeep_img);
                        carBrandImage.setImageResource(R.drawable.logo_jeep_img);
                        carBrandText.setText("Jeep");
                        break;
                    default:
                        break;
                }

                // controlli per il carburante della macchina (diesel, petrol, electric)
                switch (currentCar.getFuelType()){
                    case "diesel":
                        carFuelText.setText("Diesel");
                        carFuelImage.setImageResource(R.drawable.garage1_dieselcar_img);
                        break;
                    case "petrol":
                        carFuelText.setText("Petrol");
                        carFuelImage.setImageResource(R.drawable.garage1_petrolcar_img);
                        break;
                    case "electric":
                        carFuelText.setText("Electric");
                        carFuelImage.setImageResource(R.drawable.garage1_electriccar_img);
                        break;
                    default:
                        break;
                }

                // controlli per il tipo della macchina (citycar, suv, berlina)
                switch (currentCar.getCarType()){
                    case "sedan":
                        carTypeText.setText("Sedan");
                        carTypeImage.setImageResource(R.drawable.garage_sedancar_front_icon);
                        break;
                    case "suv":
                        carTypeText.setText("Suv");
                        carTypeImage.setImageResource(R.drawable.garage_suv_front_icon);
                        break;
                    case "citycar":
                        carTypeText.setText("Citycar");
                        carTypeImage.setImageResource(R.drawable.garage_citycar_front_icon);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void showAlertsDialog(){

        alertsDialog = new Dialog(CarGarageActivity.this);
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
                intent = new Intent(CarGarageActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                finish();
            }
        });

        navbarColorCorrectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, ColorBlindActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarNewCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, AccountActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        navbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarGarageActivity.this, CarManageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // pulsante per collegare una nuova macchina
        newCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToCarConnectionActivity;
                goToCarConnectionActivity = new Intent(CarGarageActivity.this, ConnectionTutorialActivity.class);
                startActivity(goToCarConnectionActivity);
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
        bottomNavbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(CarGarageActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // pulsante per andare alla map activity
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(CarGarageActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // pulsante per andare alla features activity
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(CarGarageActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
}