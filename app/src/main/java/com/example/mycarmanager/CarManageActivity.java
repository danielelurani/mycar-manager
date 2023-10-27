package com.example.mycarmanager;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.mycarmanager.LoginActivity.currentCarIndex;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;


public class CarManageActivity extends AppCompatActivity {


    private Car currentCar;

    private CircleImageView navbarProfilePic;

    private Dialog alertsDialog;

    private ImageView carBrandImage, carFuelImage, carImage, carTypeImage, manageImage,
            manageLeftArrow, manageRightArrow;

    private DrawerLayout drawerLayout;

    private MaterialButton functionButton, navbarAccountButton, navbarColorCorrectionButton,
            navbarFeaturesButton, navbarGarageButton, navbarLogoutButton, navbarMapButton,
            navbarNewCarButton;

    private LinearLayout alertIconLayout, bottomNavbarFeaturesButton, bottomNavbarGarageButton,
            bottomNavbarMapButton, carFunctionContainer, navMenuButton;

    private NavigationView navMenu;

    private TextView carFunctionText, carName, carPlate, navBarUsername, navbarEmail;

    private User currentUser;

    // Variabili per il tema
    public SharedPreferences sharedPreferences;
    public int selectedTheme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_car_manage);

        // Inizializza gli elementi della pagina
        initViews();

        // Recupera i dati dell'utente corrente
        currentUser = users.get(currentUserIndex);

        // Aggiorna i dati dell'utente
        updateData(selectedTheme);

        // Inizializzazione listeners
        initListeners();
    }

    private void initViews() {
        alertIconLayout = findViewById(R.id.alertsIcon);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        carBrandImage = findViewById(R.id.carBrandImage);
        carFuelImage = findViewById(R.id.carFuelImage);
        carFunctionContainer = findViewById(R.id.carFunctionContainer);
        carFunctionText = findViewById(R.id.carFunctionText);
        carImage = findViewById(R.id.carImage);
        carName = findViewById(R.id.carName);
        carPlate = findViewById(R.id.carPlate);
        carTypeImage = findViewById(R.id.carTypeImage);
        drawerLayout = findViewById(R.id.drawerLayout);
        //functionButton = findViewById(R.id.functionButton);
        navBarUsername = findViewById(R.id.navBarUsername);
        navbarAccountButton = findViewById(R.id.navbarAccountButton);
        navbarColorCorrectionButton = findViewById(R.id.navbarColorCorrectionButton);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarFeaturesButton = findViewById(R.id.navbarFeaturesButton);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarLogoutButton = findViewById(R.id.navbarLogoutButton);
        navbarMapButton = findViewById(R.id.navbarMapButton);
        navbarNewCarButton = findViewById(R.id.navbarNewCarButton);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        navMenu = findViewById(R.id.nav_view);
        navMenuButton = findViewById(R.id.navMenuButton);
        //manageImage = findViewById(R.id.manageImage);
        manageLeftArrow = findViewById(R.id.manageLeftArrow);
        manageRightArrow = findViewById(R.id.manageRightArrow);
    }

    public void initListeners() {
        // Listener freccia selezione auto [dx]
        manageRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextSetting(selectedTheme);
            }
        });

        // Listener freccia selezione auto [sx]
        manageLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevSetting(selectedTheme);
            }
        });

        // Listener scorrimento dito selezione impostazione
        carFunctionContainer.setOnTouchListener(new View.OnTouchListener() {
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
                            if (x2 > x1) { prevSetting(selectedTheme); }
                            else { nextSetting(selectedTheme); }
                        }
                        break;
                }
                return true;
            }
        });

        // Listener tasto di apertura della navbar laterale [alto sx]
        navMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(GravityCompat.START);
                navMenu.bringToFront();
            }
        });

        // Listener pulsante di apertura degli alerts [alto dx]
        alertIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertsDialog();
            }
        });

        // Listener pulsante "Logout" [navbar laterale]
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

        // Listener pulsante "Color Correction" [navbar laterale]
        navbarColorCorrectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, ColorBlindActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "New Car" [navbar laterale]
        navbarNewCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Account" [navbar laterale]
        navbarAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, AccountActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar laterale]
        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar laterale]
        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar laterale]
        navbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarManageActivity.this, CarGarageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar in basso]
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(CarManageActivity.this, CarGarageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar in basso]
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(CarManageActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar in basso]
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

    // DA IMPLEMENTARE
    private void nextSetting(int selectedTheme) {
        /*
        // Verifica se l'auto corrente è l'ultima dell'elenco
        if(currentCarIndex == (currentUser.getGarage().size()-1)) {
            currentCar = currentUser.getGarage().get(0);
            currentCarIndex = 0;
        }
        else {
            currentCar = currentUser.getGarage().get(currentCarIndex+1);
            currentCarIndex = currentCarIndex+1;
        }

         */

        // Aggiorna informazioni auto corrente
        updateCarSettings();
    }

    // DA IMPLEMENTARE
    private void prevSetting(int selectedTheme) {
        /*
        // Verifica se l'auto corrente è l'ultima dell'elenco
        if(currentSettingIndex == (currentUser.getSettings().size()-1)) {
            currentSetting = currentUser.getSettings().get(0);
            currentSettingIndex = 0;
        }
        else {
            currentSetting = currentUser.getSettings().get(currentSettingIndex+1);
            currentSettingIndex = currentSettingIndex+1;
        }

         */

        // Aggiorna informazioni auto corrente
        updateCarSettings();
    }

    public void updateData(int filter) {
        // Aggiorna informazioni utente [navbar laterale]
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

        // Aggiorna informazioni auto corrente
        currentCar = currentUser.getGarage().get(currentCarIndex);
        updateCarInformations(filter);

        // Aggiorna informazioni sulle impostazioni
        updateCarSettings();
    }

    private void updateCarInformations(int filter) {
        // Informazioni generali dell'auto
        String carBrandName = currentCar.getBrand() + " " + currentCar.getName();
        carName.setText(carBrandName);
        carPlate.setText(currentCar.getPlate());

        // Impostazione logo automobile
        switch (currentCar.getBrand()) {
            case "BMW":
                switch(filter) {
                    case 1: updatePageColors("car", 1); break;
                    case 2: updatePageColors("car", 2); break;
                    case 3: updatePageColors("car", 3); break;
                    case 4: updatePageColors("car", 4); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_bmw_img);
                break;

            case "Volkswagen":
                switch(filter) {
                    case 1: updatePageColors("car", 5); break;
                    case 2: updatePageColors("car", 6); break;
                    case 3: updatePageColors("car", 7); break;
                    case 4: updatePageColors("car", 8); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_volkswagen_img);
                break;

            case "Jeep":
                switch(filter) {
                    case 1: updatePageColors("car", 9); break;
                    case 2: updatePageColors("car", 10); break;
                    case 3: updatePageColors("car", 11); break;
                    case 4: updatePageColors("car", 12); break;
                }
                carBrandImage.setImageResource(R.drawable.logo_jeep_img);
                break;

            default: break;
        }

        // Impostazione logo tipo carburante
        switch (currentCar.getFuelType()) {
            case "diesel":
                carFuelImage.setImageResource(R.drawable.garage1_dieselcar_img);
                break;

            case "petrol":
                carFuelImage.setImageResource(R.drawable.garage1_petrolcar_img);
                break;

            case "electric":
                carFuelImage.setImageResource(R.drawable.garage1_electriccar_img);
                break;

            default: break;
        }

        // Impostazione logo tipo auto
        switch (currentCar.getCarType()) {
            case "sedan":
                carTypeImage.setImageResource(R.drawable.garage_sedancar_front_icon);
                break;

            case "suv":
                carTypeImage.setImageResource(R.drawable.garage_suv_front_icon);
                break;

            case "citycar":
                carTypeImage.setImageResource(R.drawable.garage_citycar_front_icon);
                break;

            default: break;
        }
    }

    // DA IMPLEMENTARE
    private void updateCarSettings() {
        /*
        // Informazioni generali dell'auto
        String settingName = currentCar.getSettingName();
        carFunctionText.setText(settingName);

        // Impostazione logo automobile
        switch (currentCar.getSettingName()) {
            case "powerOn":
                // Update immagine e tasto
                currentCar.setSettingName("POWER ON");
                manageImage.setImageResource(R.drawable.manage_on);

                if(currentCar.getPowerOn()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("ON");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("OFF");
                }
                break;

            case "air":
                // Update immagine e tasto
                currentCar.setSettingName("AC");
                manageImage.setImageResource(R.drawable.manage_ac);

                if(currentCar.getAir()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("ON");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("OFF");
                }
                break;

            case "windows":
                // Update immagine e tasto
                currentCar.setSettingName("WINDOWS");
                manageImage.setImageResource(R.drawable.manage_carwindow);

                if(currentCar.getWindows()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("OPEN");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("CLOSED");
                }
                break;

            case "lock":
                // Update immagine e tasto
                currentCar.setSettingName("LOCK");
                manageImage.setImageResource(R.drawable.manage_lock);

                if(currentCar.getLock()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("OPEN");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("CLOSED");
                }
                break;

            case "radio":
                // Update immagine e tasto
                currentCar.setSettingName("RADIO");
                manageImage.setImageResource(R.drawable.manage_radio);

                if(currentCar.getRadio()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("ON");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("OFF");
                }
                break;

            case "headlights":
                // Update immagine e tasto
                currentCar.setSettingName("HEADLIGHTS");
                manageImage.setImageResource(R.drawable.manage_headlights);

                if(currentCar.getHeadlights()) {
                    functionButton.setBackgroundColor(R.attr.okBtnBackground);
                    functionButton.setText("ON");
                }
                else {
                    functionButton.setBackgroundColor(R.attr.notOkBtnBackground);
                    functionButton.setText("OFF");
                }
                break;

            default: break;
        }

         */
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

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                CarManageActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                CarManageActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                CarManageActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                CarManageActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }

    public void updatePageColors(String type, int mode) {
        Bitmap originalBitmap, filteredBitmap;

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
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 3:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 4:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_bmw_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;

                    // VOLKSWAGEN
                    case 5:
                        // Update immagine e testo
                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        break;
                    case 6:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 7:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 8:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_volkswagen_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;

                    // JEEP
                    case 9:
                        // Update immagine e testo
                        carImage.setImageResource(R.drawable.car_jeep_img);
                        break;
                    case 10:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 11:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                    case 12:
                        // Update immagine e testo
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_jeep_img);
                        filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                        carImage.setImageBitmap(filteredBitmap);
                        break;
                }
                break;

            case "profile":
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
}