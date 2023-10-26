package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.example.mycarmanager.ColorBlindActivity;

public class CarGarageActivity extends AppCompatActivity {

    private User currentUser;
    private Car currentCar;
    private TextView navBarUsername, navbarEmail, carName, carPlate, carBrandText;
    private TextView carFuelText, carTypeText;
    private Dialog alertsDialog;
    private ImageButton newCarButton;
    private LinearLayout alertIconLayout, navMenuButton, bottomNavbarGarageButton, carOverlay;
    private LinearLayout bottomNavbarManageButton, bottomNavbarMapButton, bottomNavbarFeaturesButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private CircleImageView navbarProfilePic;
    private ImageView carImage, garageLeftArrow, garageRightArrow, carBrandImage;
    private ImageView carFuelImage, carTypeImage;
    private MaterialButton navbarGarageButton, navbarManageButton, navbarMapButton, navbarFeaturesButton,
                            navbarAccountButton, navbarNewCarButton, navbarColorCorrectionButton,
                            navbarLogoutButton;

    public SharedPreferences sharedPreferences;

    public int selectedTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set del tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

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
        carOverlay = findViewById(R.id.carOverlay);
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
        updateData(selectedTheme);

        // metodo che contiene tutti i listeners
        initListeners();
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                CarGarageActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                CarGarageActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                CarGarageActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                CarGarageActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }

    public void updatePageColors(String type, int mode) {
        switch(type) {
            case "car":
                switch (mode) {
                    // BMW
                    case 1:
                        // Update immagine e testo
                        carImage.setImageResource(R.drawable.car_bmw_img);
                        break;
                    case 2:
                        // Update immagine e testo
                        Bitmap originalBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        Bitmap filteredBitmap2 = ColorBlindFilter.applyFilter(originalBitmap2, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap2);
                        break;
                    case 3:
                        // Update immagine e testo
                        Bitmap originalBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        Bitmap filteredBitmap3 = ColorBlindFilter.applyFilter(originalBitmap3, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap3);
                        break;
                    case 4:
                        // Update immagine e testo
                        Bitmap originalBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        Bitmap filteredBitmap4 = ColorBlindFilter.applyFilter(originalBitmap4, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap4);
                        break;

                    // VOLKSWAGEN
                    case 5:
                        // Update immagine e testo
                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        break;
                    case 6:
                        // Update immagine e testo
                        Bitmap originalBitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        Bitmap filteredBitmap6 = ColorBlindFilter.applyFilter(originalBitmap6, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap6);
                        break;
                    case 7:
                        // Update immagine e testo
                        Bitmap originalBitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        Bitmap filteredBitmap7 = ColorBlindFilter.applyFilter(originalBitmap7, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap7);
                        break;
                    case 8:
                        // Update immagine e testo
                        Bitmap originalBitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        Bitmap filteredBitmap8 = ColorBlindFilter.applyFilter(originalBitmap8, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap8);
                        break;

                    // JEEP
                    case 9:
                        // Update immagine e testo
                        carImage.setImageResource(R.drawable.car_jeep_img);
                        break;
                    case 10:
                        // Update immagine e testo
                        Bitmap originalBitmap10 = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        Bitmap filteredBitmap10 = ColorBlindFilter.applyFilter(originalBitmap10, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap10);
                        break;
                    case 11:
                        // Update immagine e testo
                        Bitmap originalBitmap11 = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        Bitmap filteredBitmap11 = ColorBlindFilter.applyFilter(originalBitmap11, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap11);
                        break;
                    case 12:
                        // Update immagine e testo
                        Bitmap originalBitmap12 = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        Bitmap filteredBitmap12 = ColorBlindFilter.applyFilter(originalBitmap12, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap12);
                        break;
                }
                break;

            case "profile":
                Bitmap originalBitmap, filteredBitmap;

                // 1 - 4    : DEFAULT PROFILE PIC   : [NOR-DEU-PRO-TRI]
                // 5 - 8    : PROFILE PIC 2         : [NOR-DEU-PRO-TRI]
                // 9 - 12   : PROFILE PIC 3         : [NOR-DEU-PRO-TRI]
                // 13 - 16  : PROFILE PIC 4         : [NOR-DEU-PRO-TRI]
                // 17 - 20  : PROFILE PIC 5         : [NOR-DEU-PRO-TRI]
                // 21 - 14  : PROFILE PIC 6         : [NOR-DEU-PRO-TRI]
                switch (mode) {
                    // DEFAULT PROFILE PIC
                    case 1:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.default_profile_pic);
                        break;
                    case 2:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 3:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 4:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;

                    // PROFILE PIC 2
                    case 5:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.profile_pic_2);
                        break;
                    case 6:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 7:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 8:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;

                    // PROFILE PIC 3
                    case 9:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.profile_pic_3);
                        break;
                    case 10:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 11:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 12:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;

                    // PROFILE PIC 4
                    case 13:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.profile_pic_4);
                        break;
                    case 14:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 15:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 16:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;

                    // PROFILE PIC 5
                    case 17:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.profile_pic_5);
                        break;
                    case 18:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 19:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 20:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;

                    // PROFILE PIC 6
                    case 21:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        navbarProfilePic.setImageResource(R.drawable.profile_pic_6);
                        break;
                    case 22:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 23:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                    case 24:
                        // UPDATE IMMAGINE NAVBAR LATERALE
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        navbarProfilePic.setImageBitmap(filteredBitmap);
                        break;
                }
                break;
        }
    }

    public void updateData(int filter){

        // aggiorna informazioni utente loggato nella navbar
        navBarUsername.setText(currentUser.getUsername());
        navbarEmail.setText(currentUser.getEmail());

        // Imposta la corretta immagine del profilo
        switch (currentUser.getImgPath()) {
            case "default_profile_pic":
                switch (filter) {
                    case 1: updatePageColors("profile", 1); break;
                    case 2: updatePageColors("profile", 2); break;
                    case 3: updatePageColors("profile", 3); break;
                    case 4: updatePageColors("profile", 4); break;
                }
                break;

            case "profile_pic_2":
                switch (filter) {
                    case 1: updatePageColors("profile", 5); break;
                    case 2: updatePageColors("profile", 6); break;
                    case 3: updatePageColors("profile", 7); break;
                    case 4: updatePageColors("profile", 8); break;
                }
                break;

            case "profile_pic_3":
                switch (filter) {
                    case 1: updatePageColors("profile", 9); break;
                    case 2: updatePageColors("profile", 10); break;
                    case 3: updatePageColors("profile", 11); break;
                    case 4: updatePageColors("profile", 12); break;
                }
                break;

            case "profile_pic4":
                switch (filter) {
                    case 1: updatePageColors("profile", 13); break;
                    case 2: updatePageColors("profile", 14); break;
                    case 3: updatePageColors("profile", 15); break;
                    case 4: updatePageColors("profile", 16); break;
                }
                break;

            case "profile_pic_5":
                switch (filter) {
                    case 1: updatePageColors("profile", 17); break;
                    case 2: updatePageColors("profile", 18); break;
                    case 3: updatePageColors("profile", 19); break;
                    case 4: updatePageColors("profile", 20); break;
                }
                break;

            case "profile_pic_6":
                switch (filter) {
                    case 1: updatePageColors("profile", 21); break;
                    case 2: updatePageColors("profile", 22); break;
                    case 3: updatePageColors("profile", 23); break;
                    case 4: updatePageColors("profile", 24); break;
                }
                break;

            default: break;
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
                switch(filter) {
                    case 1: updatePageColors("car", 1); break;
                    case 2: updatePageColors("car", 2); break;
                    case 3: updatePageColors("car", 3); break;
                    case 4: updatePageColors("car", 4); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                carBrandText.setText("BMW");
                break;
            case "Volkswagen":
                switch(filter) {
                    case 1: updatePageColors("car", 5); break;
                    case 2: updatePageColors("car", 6); break;
                    case 3: updatePageColors("car", 7); break;
                    case 4: updatePageColors("car", 8); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                carBrandText.setText("Volkswagen");
                break;
            case "Jeep":
                switch(filter) {
                    case 1: updatePageColors("car", 9); break;
                    case 2: updatePageColors("car", 10); break;
                    case 3: updatePageColors("car", 11); break;
                    case 4: updatePageColors("car", 12); break;
                }
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

    public void nextCar (int filter) {
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

        switch (currentCar.getBrand()){
            case "BMW":
                switch(filter) {
                    case 1: updatePageColors("car", 1); break;
                    case 2: updatePageColors("car", 2); break;
                    case 3: updatePageColors("car", 3); break;
                    case 4: updatePageColors("car", 4); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                carBrandText.setText("BMW");
                break;
            case "Volkswagen":
                switch(filter) {
                    case 1: updatePageColors("car", 5); break;
                    case 2: updatePageColors("car", 6); break;
                    case 3: updatePageColors("car", 7); break;
                    case 4: updatePageColors("car", 8); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                carBrandText.setText("Volkswagen");
                break;
            case "Jeep":
                switch(filter) {
                    case 1: updatePageColors("car", 9); break;
                    case 2: updatePageColors("car", 10); break;
                    case 3: updatePageColors("car", 11); break;
                    case 4: updatePageColors("car", 12); break;
                }
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

    public void prevCar (int filter) {
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

        switch (currentCar.getBrand()){
            case "BMW":
                switch(filter) {
                    case 1: updatePageColors("car", 1); break;
                    case 2: updatePageColors("car", 2); break;
                    case 3: updatePageColors("car", 3); break;
                    case 4: updatePageColors("car", 4); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                carBrandText.setText("BMW");
                break;
            case "Volkswagen":
                switch(filter) {
                    case 1: updatePageColors("car", 5); break;
                    case 2: updatePageColors("car", 6); break;
                    case 3: updatePageColors("car", 7); break;
                    case 4: updatePageColors("car", 8); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                carBrandText.setText("Volkswagen");
                break;
            case "Jeep":
                switch(filter) {
                    case 1: updatePageColors("car", 9); break;
                    case 2: updatePageColors("car", 10); break;
                    case 3: updatePageColors("car", 11); break;
                    case 4: updatePageColors("car", 12); break;
                }
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

    public void initListeners(){
        // scorre in avanti la lista di auto
        // se arriva all'ultima auto e viene cliccato torna alla prima
        garageRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCar(selectedTheme);
            }
        });

        // scorre all'indietro la lista di auto
        // se arriva alla prima auto e viene cliccato torna all'ultima
        garageLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevCar(selectedTheme);
            }
        });

        carOverlay.setOnTouchListener(new View.OnTouchListener() {

            float x1, x2;
            static final int MIN_DISTANCE = 150;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        x1 = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        x2 = event.getX();
                        float deltaX = x2 - x1;

                        if (Math.abs(deltaX) > MIN_DISTANCE) {
                            if (x2 > x1) {
                                prevCar(selectedTheme);
                            }
                            else {
                                nextCar(selectedTheme);
                            }
                        }
                        break;
                }
                return true;
            }
        });

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