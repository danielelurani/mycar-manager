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
import static com.example.mycarmanager.User.users;
import static com.example.mycarmanager.LoginActivity.USER_EXTRA;
import com.google.android.material.navigation.NavigationView;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import de.hdodenhof.circleimageview.CircleImageView;



public class CarGarageActivity extends AppCompatActivity {

    public static int currentCarIndex = 0;
    public static int currentUserIndex;
    private User user;
    private Car currentCar;
    private TextView navBarUsername, navbarEmail, carName, carPlate;
    private Dialog alertsDialog;
    private LinearLayout alertIconLayout, navMenuButton;
    private DrawerLayout drawerLayout;
    private NavigationView navMenu;
    private CircleImageView navbarProfilePic;
    private ImageView carImage, garageLeftArrow, garageRightArrow;

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

        // Prendo tutti gli intent passati a questa activity
        Intent intentGetter = getIntent();

        Serializable obj = intentGetter.getSerializableExtra(USER_EXTRA);

        // Controllo che l'oggetto passato sia un User. Se non lo è lo istanzio
        if(obj instanceof User)
            user = (User) obj;
        else
            user = new User();

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
    }

    public void updateData(){

        int numberOfUsers = users.size();

        for (int i = 0; i < numberOfUsers; i++){
            if(user.getUsername().equals(users.get(i).getUsername()) &&
                    user.getPassword().equals(users.get(i).getPassword())){

                User currentUser = users.get(i);

                // aggiorna informazioni utente loggato nella navbar
                navBarUsername.setText(users.get(i).getUsername());
                navbarEmail.setText(users.get(i).getEmail());

                // Imposta la corretta immagine del profilo
                switch (users.get(i).getImgPath()) {
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
                currentCar = users.get(i).getGarage().get(currentCarIndex);

                String carBrandName = StringUtils.capitalize(currentCar.getBrand()) + " "
                        + StringUtils.capitalize(currentCar.getName());
                carName.setText(carBrandName);

                carImage.setImageResource(R.drawable.car_volkswagen_img);
                carPlate.setText(currentCar.getPlate());

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

                        String carBrandName = StringUtils.capitalize(currentCar.getBrand()) + " "
                                + StringUtils.capitalize(currentCar.getName());
                        carName.setText(carBrandName);

                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        carPlate.setText(currentCar.getPlate());
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

                        String carBrandName = StringUtils.capitalize(currentCar.getBrand()) + " "
                                + StringUtils.capitalize(currentCar.getName());
                        carName.setText(carBrandName);

                        carImage.setImageResource(R.drawable.car_volkswagen_img);
                        carPlate.setText(currentCar.getPlate());
                    }
                });
            }
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
}