package com.example.mycarmanager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColorBlindActivity extends AppCompatActivity {
    private Button applyButton;
    private CircleImageView navbarProfilePic;
    private Dialog alertsDialog;
    private DrawerLayout drawerLayout;
    private ImageView imageView;
    private LinearLayout alertIconLayout, navMenuButton,
            bottomNavbarFeaturesButton, bottomNavbarMapButton, bottomNavbarManageButton, bottomNavbarGarageButton;
    private MaterialButton navbarManageButton, navbarMapButton, navbarFeaturesButton,
            navbarAccountButton, navbarNewCarButton, navbarLogoutButton,
            navbarGarageButton;
    private NavigationView navMenu;
    private RadioButton radioButtonNone, radioButtonDeu, radioButtonPro, radioButtonTri;
    private RadioGroup radioGroup;
    private TextView filterName, navbarEmail, navBarUsername;
    private User currentUser;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set del tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Set degli elementi della pagina
        setContentView(R.layout.activity_color_blind_v2);

        // Salvataggio ID elementi della pagina
        alertIconLayout = findViewById(R.id.alertsIcon);
        applyButton = findViewById(R.id.applyFilterButton);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
        drawerLayout = findViewById(R.id.drawerLayout);
        filterName = findViewById(R.id.colourBlindExampleText);
        imageView = findViewById(R.id.colourBlindExampleImage);
        navbarAccountButton = findViewById(R.id.navbarAccountButton);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarFeaturesButton = findViewById(R.id.navbarFeaturesButton);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarLogoutButton = findViewById(R.id.navbarLogoutButton);
        navbarManageButton = findViewById(R.id.navbarManageButton);
        navbarMapButton = findViewById(R.id.navbarMapButton);
        navbarNewCarButton = findViewById(R.id.navbarNewCarButton);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        navBarUsername = findViewById(R.id.navBarUsername);
        navMenu = findViewById(R.id.nav_view);
        navMenuButton = findViewById(R.id.navMenuButton);
        radioButtonDeu = findViewById(R.id.deuteranopiaRadioButton);
        radioButtonNone = findViewById(R.id.noColourBlindRadioButton);
        radioButtonPro = findViewById(R.id.protanopiaRadioButton);
        radioButtonTri = findViewById(R.id.tritanopiaRadioButton);
        radioGroup = findViewById(R.id.radioButtonsContainer);

        // Recupero dati utente
        currentUser = users.get(currentUserIndex);

        // Aggiornamento pagina in base al tema
        updatePageColors(selectedTheme);

        // Aggiornamento dati utente nella navbar laterale
        updateData();

        // Inizializzazione listeners della pagina
        initListeners();
    }

    public void initListeners () {
        // Listener per i radio buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Radio button selezionato
                RadioButton selectedRadioButton = findViewById(checkedId);

                // Cambia l'immagine in base al RadioButton selezionato
                if (selectedRadioButton.getId() == R.id.noColourBlindRadioButton) {
                    imageView.setImageResource(R.drawable.car_rainbow_dodge_img);
                    filterName.setText("NO FILTER APPLIED");
                } else if (selectedRadioButton.getId() == R.id.deuteranopiaRadioButton) {
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                    filterName.setText("DEUTERAN FIX FILTER");
                } else if (selectedRadioButton.getId() == R.id.protanopiaRadioButton) {
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                    filterName.setText("PROTAN FIX FILTER");
                } else if (selectedRadioButton.getId() == R.id.tritanopiaRadioButton) {
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                    filterName.setText("TRITAN FIX FILTER");
                }
            }
        });

        // Listener pulsante "Apply"
        applyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                // Correzione del colore in base al toggle selezionato
                if (checkedRadioButtonId == R.id.noColourBlindRadioButton) { colorFix(1); }
                else if (checkedRadioButtonId == R.id.deuteranopiaRadioButton) { colorFix(2); }
                else if (checkedRadioButtonId == R.id.protanopiaRadioButton) { colorFix(3); }
                else if (checkedRadioButtonId == R.id.tritanopiaRadioButton) { colorFix(4); }
            }
        });

        // Listener pulsante "Logout" [navbar laterale]
        navbarLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                finish();
            }
        });

        // Listener pulsante "New Car" [navbar laterale]
        navbarNewCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Account" [navbar laterale]
        navbarAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, AccountActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar laterale]
        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar laterale]
        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Manage" [navbar laterale]
        navbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, CarManageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar laterale]
        navbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                intent = new Intent(ColorBlindActivity.this, CarGarageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
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

        // Listener pulsante "Manage" [navbar in basso]
        bottomNavbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToManageActivity;
                goToManageActivity = new Intent(ColorBlindActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar in basso]
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(ColorBlindActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar in basso]
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToMapActivity;
                goToMapActivity = new Intent(ColorBlindActivity.this, CarGarageActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar in basso]
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(ColorBlindActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    public void updateData() {
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

    public void showAlertsDialog(){

        alertsDialog = new Dialog(ColorBlindActivity.this);
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

    public void colorFix (int mode) {
        Editor editor = sharedPreferences.edit();

        switch (mode) {
            case 1:
                changeTheme(R.style.BASE_THEME);
                editor.putInt("SelectedTheme", 1);
                break;

            case 2:
                changeTheme(R.style.DEUTERAN_THEME);
                editor.putInt("SelectedTheme", 2);
                break;

            case 3:
                changeTheme(R.style.PROTAN_THEME);
                editor.putInt("SelectedTheme", 3);
                break;

            case 4:
                changeTheme(R.style.TRITAN_THEME);
                editor.putInt("SelectedTheme", 4);
                break;
        }

        editor.apply();
        startActivity(new Intent(ColorBlindActivity.this, ColorBlindActivity.class));
        finish();
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                ColorBlindActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                ColorBlindActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                ColorBlindActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                ColorBlindActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }

    public void updatePageColors(int mode) {
        switch (mode) {
            case 1:
                // Update immagine e testo
                imageView.setImageResource(R.drawable.car_rainbow_dodge_img);
                filterName.setText("NO FILTER APPLIED");

                // Update tasto radioGroup
                radioButtonNone.setChecked(true);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(false);
                break;
            case 2:
                // Update immagine e testo
                Bitmap originalBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap2 = ColorBlindFilter.applyFilter(originalBitmap2, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                imageView.setImageBitmap(filteredBitmap2);
                filterName.setText("DEUTERAN FIX FILTER");

                // Update tasto radioGroup
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(true);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(false);
                break;
            case 3:
                // Update immagine e testo
                Bitmap originalBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap3 = ColorBlindFilter.applyFilter(originalBitmap3, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                imageView.setImageBitmap(filteredBitmap3);
                filterName.setText("PROTAN FIX FILTER");

                // Update tasto radioGroup
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(true);
                radioButtonTri.setChecked(false);
                break;
            case 4:
                // Update immagine e testo
                Bitmap originalBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap4 = ColorBlindFilter.applyFilter(originalBitmap4, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                imageView.setImageBitmap(filteredBitmap4);
                filterName.setText("TRITAN FIX FILTER");

                // Update tasto radioGroup
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(true);
                break;
        }
    }
}