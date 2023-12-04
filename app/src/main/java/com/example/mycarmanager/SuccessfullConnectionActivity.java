package com.example.mycarmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.widget.Toast;
import static com.example.mycarmanager.LoginActivity.currentUserIndex;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.mycarmanager.User.users;
import java.util.Random;

public class SuccessfullConnectionActivity extends AppCompatActivity {
    public int selectedTheme;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Imposta il tema in base alle preferenze
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        selectedTheme = sharedPreferences.getInt("SelectedTheme", 1);
        changeTheme(selectedTheme);

        // Imposta il layout della pagina
        setContentView(R.layout.activity_successfull_connection);

        // Aggiunta di un auto casuale al garage dell'utente
        boolean carAdded;
        do {
            carAdded = addRandomCar();
        } while (!carAdded);

        // Secondi di caricamento dopo la prima connessione
        // prima di essere indirizzati al garage
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goToHomePage;
                goToHomePage = new Intent(SuccessfullConnectionActivity.this, CarGarageActivity.class);
                startActivity(goToHomePage);
            }
        }, 3000);
    }

    // Metodo che aggiunge un auto random al garage dell'utente
    public boolean addRandomCar(){

        boolean alreadyIn = false;
        boolean added = false;
        int numberOfCars = users.get(currentUserIndex).getGarage().size();
        Random rand = new Random();
        int n = rand.nextInt(3);

        if(numberOfCars == 0){

            switch (n) {
                case 0:
                    Car volkswagenPolo = new Car("Volkswagen", "Polo", "volkswagenPolo",
                            "GH236FF", "petrol", "sedan", "5", "1478",
                            "5", "abs", "5", "22", "103", "76",
                            "10.9", "98", 39.222334, 9.114042,
                            true, 100, new AirConditioning(true,
                            18.0f, 3), true, new Radio(true,
                            103.2f, 25), false);
                    users.get(currentUserIndex).getGarage().add(volkswagenPolo);
                    added = true;
                    break;
                case 1:
                    Car bmwI3 = new Car("BMW", "I3", "bmwI3",
                            "GK211TR", "electric", "citycar", "5", "1345",
                            "5", "abs", "5", "12.9", "9.3", "170",
                            "7.1", "67", 39.222334, 9.114042,
                            false, 95, new AirConditioning(false,
                            0f, 0), false, new Radio(false,
                            0f, 0), false);
                    users.get(currentUserIndex).getGarage().add(bmwI3);
                    added = true;
                    break;
                case 2:
                    Car jeepCherokee = new Car("Jeep", "Cherokee", "jeepCherokee",
                            "GS011FA", "diesel", "suv", "5", "2097",
                            "5", "abs", "6", "15.7", "385", "380",
                            "6.3", "87", 39.222334, 9.114042,
                            true, 0, new AirConditioning(false,
                            0f, 0), false, new Radio(true,
                            69.2f, 10), true);
                    users.get(currentUserIndex).getGarage().add(jeepCherokee);
                    added = true;
                    break;
                default:break;
            }

        } else {

            switch (n) {
                case 0:
                    Car volkswagenPolo = new Car("Volkswagen", "Polo", "volkswagenPolo",
                            "GH236FF", "petrol", "sedan", "5", "1478",
                            "5", "abs", "5", "22", "103", "76",
                            "10.9", "98", 39.222334, 9.114042,
                            true, 100, new AirConditioning(true,
                            18.0f, 3), true, new Radio(true,
                            103.2f, 25), false);
                    for (int i = 0; i < numberOfCars; i++) {
                        alreadyIn = users.get(currentUserIndex).getGarage().get(i).getPlate().equals(volkswagenPolo.getPlate());
                        if(alreadyIn)
                            break;
                    }
                    if (!alreadyIn) {
                        users.get(currentUserIndex).getGarage().add(volkswagenPolo);
                        added = true;
                    }
                    break;
                case 1:
                    Car bmwI3 = new Car("BMW", "I3", "bmwI3",
                            "GK211TR", "electric", "citycar", "5", "1345",
                            "5", "abs", "5", "12.9", "9.3", "170",
                            "7.1", "67", 39.222334, 9.114042,
                            false, 95, new AirConditioning(false,
                            0f, 0), false, new Radio(false,
                            0f, 0), false);
                    for (int i = 0; i < numberOfCars; i++) {
                        alreadyIn = users.get(currentUserIndex).getGarage().get(i).getPlate().equals(bmwI3.getPlate());
                        if(alreadyIn)
                            break;
                    }
                    if (!alreadyIn){
                        users.get(currentUserIndex).getGarage().add(bmwI3);
                        added = true;
                    }
                    break;
                case 2:
                    Car jeepCherokee = new Car("Jeep", "Cherokee", "jeepCherokee",
                            "GS011FA", "diesel", "suv", "5", "2097",
                            "5", "abs", "6", "15.7", "385", "380",
                            "6.3", "87", 39.222334, 9.114042,
                            true, 0, new AirConditioning(false,
                            0f, 0), false, new Radio(true,
                            69.2f, 10), true);
                    for (int i = 0; i < numberOfCars; i++){
                        alreadyIn = users.get(currentUserIndex).getGarage().get(i).getPlate().equals(jeepCherokee.getPlate());
                        if (alreadyIn)
                            break;
                    }
                    if(!alreadyIn) {
                        users.get(currentUserIndex).getGarage().add(jeepCherokee);
                        added = true;
                    }
                    break;
                default:break;
            }
        }

        return added;
    }

    public void changeTheme (int newThemeId) {
        switch (newThemeId) {
            case 1:
                SuccessfullConnectionActivity.this.setTheme(R.style.BASE_THEME);
                break;
            case 2:
                SuccessfullConnectionActivity.this.setTheme(R.style.DEUTERAN_THEME);
                break;
            case 3:
                SuccessfullConnectionActivity.this.setTheme(R.style.PROTAN_THEME);
                break;
            case 4:
                SuccessfullConnectionActivity.this.setTheme(R.style.TRITAN_THEME);
                break;
        }
    }
}