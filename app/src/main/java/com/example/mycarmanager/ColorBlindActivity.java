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
    // Dichiarazione di variabili di istanza
    private Button applyButton;
    private CircleImageView navbarProfilePic;
    private DrawerLayout drawerLayout;
    private ImageView imageView;
    private LinearLayout navMenuButton,
            bottomNavbarFeaturesButton, bottomNavbarMapButton, bottomNavbarManageButton, bottomNavbarGarageButton,
            bottomNavbarAlertsButton;
    private MaterialButton navbarManageButton, navbarMapButton, navbarFeaturesButton,
            navbarAccountButton, navbarNewCarButton, navbarLogoutButton,
            navbarGarageButton, navbarAlertsButton;
    private NavigationView navMenu;
    private RadioButton radioButtonNone, radioButtonDeu, radioButtonPro, radioButtonTri;
    private RadioGroup radioGroup;
    private TextView filterName, navbarEmail, navBarUsername;
    private User currentUser;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_color_blind_v2);

        // Inizializza gli elementi della pagina
        initViews();

        // Recupera i dati dell'utente corrente
        currentUser = users.get(currentUserIndex);

        // Aggiorna la pagina in base al tema
        updateUI(selectedTheme);

        // Inizializza i listener per gli elementi della pagina
        initListeners();
    }

    private void initViews() {
        // Inizializza gli elementi della pagina
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

        bottomNavbarAlertsButton = findViewById(R.id.alertsButtonContainer);
        navbarAlertsButton = findViewById(R.id.navbarAlertsButton);
    }

    private void initListeners() {
        // Listener per i radio buttons
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                handleRadioButtonChange(checkedId);
            }
        });

        // Listener pulsante "Apply"
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleApplyButtonClick();
            }
        });

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

        // Listener pulsante "Alerts" [navbar laterale]
        navbarAlertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(ColorBlindActivity.this, CarAlertsActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
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

        // Listener pulsante "Alerts" [navbar in basso]
        bottomNavbarAlertsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(ColorBlindActivity.this, CarAlertsActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void handleRadioButtonChange(int checkedId) {
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

    private void handleApplyButtonClick() {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        // Correzione del colore in base al toggle selezionato
        if (checkedRadioButtonId == R.id.noColourBlindRadioButton) { colorFix(1); }
        else if (checkedRadioButtonId == R.id.deuteranopiaRadioButton) { colorFix(2); }
        else if (checkedRadioButtonId == R.id.protanopiaRadioButton) { colorFix(3); }
        else if (checkedRadioButtonId == R.id.tritanopiaRadioButton) { colorFix(4); }
    }

    private void openDrawer() {
        // Apre la navbar laterale
        drawerLayout.openDrawer(GravityCompat.START);
        navMenu.bringToFront();
    }

    private void updateUI(int selectedTheme) {
        // Aggiorna l'UI in base al tema selezionato
        updateRadioButtons(selectedTheme);
        updateExampleImage(selectedTheme);
        updateUserData(selectedTheme);
    }

    private void updateUserData(int filter) {
        // Aggiorna i dati dell'utente nella navbar laterale
        navBarUsername.setText(currentUser.getUsername());
        navbarEmail.setText(currentUser.getEmail());

        // Imposta l'immagine del profilo in base al filtro selezionato
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
    }

    private void updateRadioButtons(int selectedTheme) {
        // Aggiorna l'aspetto dei radio buttons in base al tema
        switch (selectedTheme) {
            case 1:
                radioButtonNone.setChecked(true);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(false);
                break;
            case 2:
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(true);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(false);
                break;
            case 3:
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(true);
                radioButtonTri.setChecked(false);
                break;
            case 4:
                radioButtonNone.setChecked(false);
                radioButtonDeu.setChecked(false);
                radioButtonPro.setChecked(false);
                radioButtonTri.setChecked(true);
                break;
        }
    }

    private void updateExampleImage(int selectedTheme) {
        // Aggiorna l'immagine di esempio in base al tema
        switch (selectedTheme) {
            case 1:
                imageView.setImageResource(R.drawable.car_rainbow_dodge_img);
                filterName.setText("NO FILTER APPLIED");
                break;

            case 2:
                Bitmap originalBitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap2 = ColorBlindFilter.applyFilter(originalBitmap2, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                imageView.setImageBitmap(filteredBitmap2);
                filterName.setText("DEUTERAN FIX FILTER");
                break;

            case 3:
                Bitmap originalBitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap3 = ColorBlindFilter.applyFilter(originalBitmap3, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                imageView.setImageBitmap(filteredBitmap3);
                filterName.setText("PROTAN FIX FILTER");
                break;

            case 4:
                Bitmap originalBitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                Bitmap filteredBitmap4 = ColorBlindFilter.applyFilter(originalBitmap4, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                imageView.setImageBitmap(filteredBitmap4);
                filterName.setText("TRITAN FIX FILTER");
                break;
        }
    }

    private void changeTheme(int selectedTheme) {
        // Cambia il tema in base alla preferenza selezionata
        switch (selectedTheme) {
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

    private void colorFix (int mode) {
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

    public void updatePageColors(String type, int mode) {
        switch (type) {
            case "car":
                switch (mode) {
                    case 1:
                        // Update immagine e testo
                        updateExampleImage(1);

                        // Update tasto radioGroup
                        updateRadioButtons(1);
                        break;
                    case 2:
                        // Update immagine e testo
                        updateExampleImage(2);

                        // Update tasto radioGroup
                        updateRadioButtons(2);
                        break;
                    case 3:
                        // Update immagine e testo
                        updateExampleImage(3);

                        // Update tasto radioGroup
                        updateRadioButtons(3);
                        break;
                    case 4:
                        // Update immagine e testo
                        updateExampleImage(4);

                        // Update tasto radioGroup
                        updateRadioButtons(4);
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
}
