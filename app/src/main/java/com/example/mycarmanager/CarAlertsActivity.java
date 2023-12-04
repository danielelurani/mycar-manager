package com.example.mycarmanager;

import static com.example.mycarmanager.LoginActivity.currentCarIndex;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarAlertsActivity extends AppCompatActivity {
    private User currentUser;
    private Car currentCar;

    public ImageView leftArow, rightArrow;
    public LinearLayout bottomNavbarGarageButton, bottomNavbarManageButton, bottomNavbarMapButton,
            bottomNavbarFeaturesButton, navMenuButton, alertBox1, alertBox2, alertBox3, alertBox4,
            noAlerts;

    private TextView navBarUsername, navbarEmail, carName;

    private NavigationView navMenu;
    private CircleImageView navbarProfilePic;
    private MaterialButton navbarGarageButton, navbarManageButton, navbarMapButton, navbarFeaturesButton,
            navbarAccountButton, navbarNewCarButton, navbarColorCorrectionButton,
            navbarLogoutButton, newCarButton;
    private DrawerLayout drawerLayout;

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
        setContentView(R.layout.activity_car_alerts);

        // Inizializza gli elementi della pagina
        initViews();

        // Recupera i dati dell'utente corrente
        currentUser = users.get(currentUserIndex);

        // Aggiorna i dati dell'utente
        updateData(selectedTheme);

        // Imposta gli alert per la macchina corrente
        updateAlertBoxes();

        // Inizializzazione listeners
        initListeners();
    }

    private void initViews () {
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        carName = findViewById(R.id.carName);
        leftArow = findViewById(R.id.garageLeftArrow);
        rightArrow = findViewById(R.id.garageRightArrow);
        navBarUsername = findViewById(R.id.navBarUsername);
        navMenu = findViewById(R.id.nav_view);
        navMenuButton = findViewById(R.id.navMenuButton);
        navbarAccountButton = findViewById(R.id.navbarAccountButton);
        navbarColorCorrectionButton = findViewById(R.id.navbarColorCorrectionButton);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarFeaturesButton = findViewById(R.id.navbarFeaturesButton);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarLogoutButton = findViewById(R.id.navbarLogoutButton);
        navbarManageButton = findViewById(R.id.navbarManageButton);
        navbarMapButton = findViewById(R.id.navbarMapButton);
        navbarNewCarButton = findViewById(R.id.navbarNewCarButton);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        newCarButton = findViewById(R.id.newCarButton);
        drawerLayout = findViewById(R.id.drawerLayout);

        alertBox1 = findViewById(R.id.alertBox1);
        alertBox2 = findViewById(R.id.alertBox2);
        alertBox3 = findViewById(R.id.alertBox3);
        alertBox4 = findViewById(R.id.alertBox4);
        noAlerts = findViewById(R.id.noAlerts);
    }

    private void initListeners () {
        // Listener tasto di apertura della navbar laterale [alto sx]
        navMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });

        // Listener pulsante "Logout" [navbar laterale]
        navbarLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, LoginActivity.class);
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
                intent = new Intent(CarAlertsActivity.this, ColorBlindActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "New Car" [navbar laterale]
        navbarNewCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Account" [navbar laterale]
        navbarAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, AccountActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar laterale]
        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar laterale]
        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Manage" [navbar laterale]
        navbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, CarManageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar laterale]
        navbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(CarAlertsActivity.this, CarGarageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Manage" [navbar in basso]
        bottomNavbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(CarAlertsActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar in basso]
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(CarAlertsActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar in basso]
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(CarAlertsActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar in basso]
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(CarAlertsActivity.this, CarGarageActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener freccia selezione auto [dx]
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextCar(selectedTheme);
            }
        });

        // Listener freccia selezione auto [sx]
        leftArow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevCar(selectedTheme);
            }
        });
    }

    private void openDrawer() {
        // Apre la navbar laterale
        drawerLayout.openDrawer(GravityCompat.START);
        navMenu.bringToFront();
    }

    public void nextCar (int filter) {
        // Verifica se l'auto corrente è l'ultima dell'elenco
        if(currentCarIndex == (currentUser.getGarage().size()-1)) {
            currentCar = currentUser.getGarage().get(0);
            currentCarIndex = 0;
        }
        else {
            currentCar = currentUser.getGarage().get(currentCarIndex+1);
            currentCarIndex = currentCarIndex+1;
        }

        // Aggiorna informazioni auto corrente
        updateCarInformations();
        updateAlertBoxes();
    }

    public void prevCar (int filter) {
        // Verifica se l'auto corrente è la prima dell'elenco
        if(currentCarIndex == (0)) {
            currentCar = currentUser.getGarage().get(currentUser.getGarage().size()-1);
            currentCarIndex = currentUser.getGarage().size()-1;
        }
        else {
            currentCar = currentUser.getGarage().get(currentCarIndex-1);
            currentCarIndex = currentCarIndex-1;
        }

        // Aggiorna informazioni auto corrente
        updateCarInformations();
        updateAlertBoxes();
    }

    private void updateCarInformations() {
        // Informazioni generali dell'auto
        String carBrandName = currentCar.getBrand() + " " + currentCar.getName();
        carName.setText(carBrandName);
    }

    private void updateAlertBoxes () {
        // Impostazione logo automobile
        switch (currentCar.getBrand()) {
            case "BMW":
                alertBox1.setVisibility(View.GONE);
                alertBox2.setVisibility(View.GONE);
                alertBox3.setVisibility(View.GONE);
                alertBox4.setVisibility(View.GONE);
                noAlerts.setVisibility(View.VISIBLE);
                break;

            case "Volkswagen":
                alertBox1.setVisibility(View.VISIBLE);
                alertBox2.setVisibility(View.GONE);
                alertBox3.setVisibility(View.GONE);
                alertBox4.setVisibility(View.GONE);
                noAlerts.setVisibility(View.GONE);
                break;

            case "Jeep":
                alertBox1.setVisibility(View.GONE);
                alertBox2.setVisibility(View.VISIBLE);
                alertBox3.setVisibility(View.VISIBLE);
                alertBox4.setVisibility(View.VISIBLE);
                noAlerts.setVisibility(View.GONE);
            default: break;
        }
    }

    public void updateData(int filter) {
        // Aggiorna informazioni utente [navbar laterale]
        navBarUsername.setText(currentUser.getUsername());
        navbarEmail.setText(currentUser.getEmail());

        // Aggiorna informazioni auto corrente
        currentCar = currentUser.getGarage().get(currentCarIndex);
        updateCarInformations();
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                CarAlertsActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                CarAlertsActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                CarAlertsActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                CarAlertsActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }
}