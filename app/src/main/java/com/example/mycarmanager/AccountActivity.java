package com.example.mycarmanager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import static com.example.mycarmanager.User.users;


public class AccountActivity extends AppCompatActivity {

    // Variabile che conserva temporaneamente l'immagine del profilo selezionata
    // nel dialog di modifica del profilo
    String currentImagePath;
    private Button editProfileButton;
    private CircleImageView navbarProfilePic, profilePic;
    private Dialog alertsDialog, editProfileDialog;
    private DrawerLayout drawerLayout;
    private int selectedTheme;
    private LinearLayout alertIconLayout, bottomNavbarFeaturesButton, bottomNavbarGarageButton,
            bottomNavbarManageButton, bottomNavbarMapButton, navMenuButton;
    private MaterialButton navbarColorCorrectionButton, navbarFeaturesButton, navbarGarageButton,
            navbarLogoutButton, navbarManageButton, navbarMapButton, navbarNewCarButton, logoutButton;
    private NavigationView navMenu;
    public SharedPreferences sharedPreferences;
    private TextView navbarEmail, navBarUsername;
    private User currentUser;
    private TextInputEditText usernameField, emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_account);

        // Inizializza gli elementi della pagina
        initViews();

        // Recupera i dati dell'utente corrente
        currentUser = users.get(currentUserIndex);

        // Aggiorna i dati dell'utente [navbar laterale, immagine profilo e dati profilo]
        updateData(selectedTheme);

        // Inizializzazione listeners
        initListeners();

        currentImagePath = currentUser.getImgPath();
    }

    private void initViews () {

        // Main page
        editProfileButton = findViewById(R.id.editProfileButton);
        alertIconLayout = findViewById(R.id.alertsIcon);
        drawerLayout = findViewById(R.id.drawerLayout);
        profilePic = findViewById(R.id.profilePic);
        logoutButton = findViewById(R.id.logoutButton);

        // Dati account
        usernameField = findViewById(R.id.usernameField);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);

        // Navbar [laterale]
        navMenuButton = findViewById(R.id.navMenuButton);
        navbarColorCorrectionButton = findViewById(R.id.navbarColorCorrectionButton);
        navbarEmail = findViewById(R.id.navbarEmail);
        navbarFeaturesButton = findViewById(R.id.navbarFeaturesButton);
        navbarGarageButton = findViewById(R.id.navbarGarageButton);
        navbarLogoutButton = findViewById(R.id.navbarLogoutButton);
        navbarManageButton = findViewById(R.id.navbarManageButton);
        navbarMapButton = findViewById(R.id.navbarMapButton);
        navbarNewCarButton = findViewById(R.id.navbarNewCarButton);
        navbarProfilePic = findViewById(R.id.navbarProfilePic);
        navBarUsername = findViewById(R.id.navBarUsername);

        // Navbar [in basso]
        navMenu = findViewById(R.id.nav_view);
        bottomNavbarManageButton = findViewById(R.id.manageButtonContainer);
        bottomNavbarFeaturesButton = findViewById(R.id.featuresButtonContainer);
        bottomNavbarGarageButton = findViewById(R.id.garageButtonContainer);
        bottomNavbarMapButton = findViewById(R.id.mapButtonContainer);
    }

    public void initListeners() {
        // Listener tasto di apertura della navbar laterale [alto sx]
        navMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer();
            }
        });

        // Listener tasto di apertura del pop-up di modifica del profilo
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditProfileDialog();
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
                goToManageActivity = new Intent(AccountActivity.this, CarManageActivity.class);
                startActivity(goToManageActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar in basso]
        bottomNavbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMapActivity;
                goToMapActivity = new Intent(AccountActivity.this, CarMapActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar in basso]
        bottomNavbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMapActivity;
                goToMapActivity = new Intent(AccountActivity.this, CarGarageActivity.class);
                startActivity(goToMapActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar in basso]
        bottomNavbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFeaturesActivity;
                goToFeaturesActivity = new Intent(AccountActivity.this, CarFeaturesActivity.class);
                startActivity(goToFeaturesActivity);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Color Correction" [navbar laterale]
        navbarColorCorrectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, ColorBlindActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Logout" [navbar laterale]
        navbarLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, LoginActivity.class);
                startActivity(intent);
                finishAffinity();
                finish();
            }
        });

        // Listener pulsante "Logout" [pagina]
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, LoginActivity.class);
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
                intent = new Intent(AccountActivity.this, ConnectionTutorialActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Features" [navbar laterale]
        navbarFeaturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, CarFeaturesActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Map" [navbar laterale]
        navbarMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, CarMapActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Manage" [navbar laterale]
        navbarManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, CarManageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        // Listener pulsante "Garage" [navbar laterale]
        navbarGarageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(AccountActivity.this, CarGarageActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    public void updateData(int filter) {
        // Aggiorna informazioni utente [navbar laterale]
        navBarUsername.setText(currentUser.getUsername());
        navbarEmail.setText(currentUser.getEmail());

        // Dati profilo
        usernameField.setText(currentUser.getUsername());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(currentUser.getPassword());

        // Imposta immagine del profilo
        switch (currentUser.getImgPath()) {
            case "default_profile_pic":
                switch (filter) {
                    case 1: updatePageColors(1); break;
                    case 2: updatePageColors(2); break;
                    case 3: updatePageColors(3); break;
                    case 4: updatePageColors(4); break;
                }
                break;

            case "profile_pic_2":
                switch (filter) {
                    case 1: updatePageColors(5); break;
                    case 2: updatePageColors(6); break;
                    case 3: updatePageColors(7); break;
                    case 4: updatePageColors(8); break;
                }
                break;

            case "profile_pic_3":
                switch (filter) {
                    case 1: updatePageColors(9); break;
                    case 2: updatePageColors(10); break;
                    case 3: updatePageColors(11); break;
                    case 4: updatePageColors(12); break;
                }
                break;

            case "profile_pic4":
                switch (filter) {
                    case 1: updatePageColors(13); break;
                    case 2: updatePageColors(14); break;
                    case 3: updatePageColors(15); break;
                    case 4: updatePageColors(16); break;
                }
                break;

            case "profile_pic_5":
                switch (filter) {
                    case 1: updatePageColors(17); break;
                    case 2: updatePageColors(18); break;
                    case 3: updatePageColors(19); break;
                    case 4: updatePageColors(20); break;
                }
                break;

            case "profile_pic_6":
                switch (filter) {
                    case 1: updatePageColors(21); break;
                    case 2: updatePageColors(22); break;
                    case 3: updatePageColors(23); break;
                    case 4: updatePageColors(24); break;
                }
                break;

            default: break;
        }
    }

    private void openDrawer() {
        // Apre la navbar laterale
        drawerLayout.openDrawer(GravityCompat.START);
        navMenu.bringToFront();
    }

    public void showAlertsDialog() {
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

    public void showEditProfileDialog() {
        editProfileDialog = new Dialog(AccountActivity.this);
        editProfileDialog.setContentView(R.layout.edit_profile_dialog);
        editProfileDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editProfileDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        // Prende tutte le views utili del dialog
        ImageButton closeDialog = editProfileDialog.findViewById(R.id.closeDialog);
        Button saveProfile = editProfileDialog.findViewById(R.id.saveProfile);
        TextInputEditText editUsernameField = editProfileDialog.findViewById(R.id.editUsernameField);
        TextInputEditText editEmailField = editProfileDialog.findViewById(R.id.editEmailField);
        TextInputEditText editPasswordField = editProfileDialog.findViewById(R.id.editPasswordField);
        TextInputEditText editPasswordRepeatField = editProfileDialog.findViewById(R.id.editPasswordRepeatField);
        CircleImageView defaultPic = editProfileDialog.findViewById(R.id.defaultPic);
        CircleImageView pic2 = editProfileDialog.findViewById(R.id.pic2);
        CircleImageView pic3 = editProfileDialog.findViewById(R.id.pic3);
        CircleImageView pic4 = editProfileDialog.findViewById(R.id.pic4);
        CircleImageView pic5 = editProfileDialog.findViewById(R.id.pic5);
        CircleImageView pic6 = editProfileDialog.findViewById(R.id.pic6);

        // Riempi i campi da editare con i dati attuali dell'utente
        editUsernameField.setText(users.get(currentUserIndex).getUsername());
        editEmailField.setText(users.get(currentUserIndex).getEmail());
        editPasswordField.setText(users.get(currentUserIndex).getPassword());
        editPasswordRepeatField.setText(users.get(currentUserIndex).getPassword());

        // Controlla quale immagine del profilo ha l'utente e la rendere riconoscibile
        // tramite il bordo verde
        switch (currentUser.getImgPath()){
            case "default_profile_pic":
                defaultPic.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                break;
            case "profile_pic_2":
                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                break;
            case "profile_pic_3":
                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                break;
            case "profile_pic_4":
                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                break;
            case "profile_pic_5":
                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                break;
            case "profile_pic_6":
                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                break;
        }

        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Aggiorno i dati dell'account con i nuovi dati inseriti nel dialog
                currentUser.setUsername(editUsernameField.getText().toString());
                currentUser.setEmail(editEmailField.getText().toString());
                currentUser.setPassword(editPasswordField.getText().toString());
                currentUser.setImgPath(currentImagePath);

                updateData(selectedTheme);

                editProfileDialog.dismiss();
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProfileDialog.dismiss();
            }
        });

        // Listeners per la selezione temporanea della nuova immagine del profilo
        defaultPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                currentImagePath = "default_profile_pic";
            }
        });

        pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                currentImagePath = "profile_pic_2";
            }
        });

        pic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                currentImagePath = "profile_pic_3";
            }
        });

        pic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                currentImagePath = "profile_pic_4";
            }
        });

        pic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                pic6.setBorderColor(getResources().getColor(R.color.black));
                currentImagePath = "profile_pic_5";
            }
        });

        pic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defaultPic.setBorderColor(getResources().getColor(R.color.black));
                pic2.setBorderColor(getResources().getColor(R.color.black));
                pic3.setBorderColor(getResources().getColor(R.color.black));
                pic4.setBorderColor(getResources().getColor(R.color.black));
                pic5.setBorderColor(getResources().getColor(R.color.black));
                pic6.setBorderColor(getResources().getColor(R.color.material_500_light_green));
                currentImagePath = "profile_pic_6";
            }
        });

        editProfileDialog.show();
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                AccountActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                AccountActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                AccountActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                AccountActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }

    public void updatePageColors (int mode) {
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

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.default_profile_pic);
                break;
            case 2:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 3:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 4:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;

            // PROFILE PIC 2
            case 5:
                // UPDATE IMMAGINE NAVBAR LATERALE
                navbarProfilePic.setImageResource(R.drawable.profile_pic_2);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.profile_pic_2);
                break;
            case 6:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 7:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 8:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_2);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;

            // PROFILE PIC 3
            case 9:
                // UPDATE IMMAGINE NAVBAR LATERALE
                navbarProfilePic.setImageResource(R.drawable.profile_pic_3);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.profile_pic_3);
                break;
            case 10:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 11:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 12:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_3);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;

            // PROFILE PIC 4
            case 13:
                // UPDATE IMMAGINE NAVBAR LATERALE
                navbarProfilePic.setImageResource(R.drawable.profile_pic_4);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.profile_pic_4);
                break;
            case 14:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 15:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 16:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_4);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;

            // PROFILE PIC 5
            case 17:
                // UPDATE IMMAGINE NAVBAR LATERALE
                navbarProfilePic.setImageResource(R.drawable.profile_pic_5);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.profile_pic_5);
                break;
            case 18:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 19:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 20:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_5);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;

            // PROFILE PIC 6
            case 21:
                // UPDATE IMMAGINE NAVBAR LATERALE
                navbarProfilePic.setImageResource(R.drawable.profile_pic_6);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageResource(R.drawable.profile_pic_6);
                break;
            case 22:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 23:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
            case 24:
                // UPDATE IMMAGINE NAVBAR LATERALE
                originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic_6);
                filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                navbarProfilePic.setImageBitmap(filteredBitmap);

                // UPDATE IMMAGINE PROFILO
                profilePic.setImageBitmap(filteredBitmap);
                break;
        }
    }
}