package com.example.mycarmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


public class CarManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_manage);
    }

    /*
    PARTE DA IMPLEMENTARE SUCCESSIVAMENTE

    int immaginiTotali = 5, contatoreImmagini = 0;
    ImageView immagini[] = {(ImageView) findViewById(R.id.manageImage1),
            (ImageView) findViewById(R.id.manageImage2),
            (ImageView) findViewById(R.id.manageImage3),
            (ImageView) findViewById(R.id.manageImage4),
            (ImageView) findViewById(R.id.manageImage5),
            (ImageView) findViewById(R.id.manageImage6) };

    protected void leftArrowClick () {
        if (contatoreImmagini != 0) { contatoreImmagini--; aggiornaImmagine(contatoreImmagini); }
        else {contatoreImmagini = 5; aggiornaImmagine(contatoreImmagini); }
    }

    protected void rightArrowClick () {
        if (contatoreImmagini != 5) { contatoreImmagini++; aggiornaImmagine(contatoreImmagini); }
        else {contatoreImmagini = 0; aggiornaImmagine(contatoreImmagini); }
    }

    protected void aggiornaImmagine (int numeroImmagine) {
        modificaImpImmagine(immagini[numeroImmagine], true);
        for (int i = 0; i < immaginiTotali; i++) {
            if (i != numeroImmagine) {
                modificaImpImmagine(immagini[i], false);
            }
        }
    }

    protected void modificaImpImmagine (ImageView immagine, boolean flag) {
        if (flag) {
            immagine.setVisibility(ImageView.VISIBLE);
            immagine.setMinimumHeight(100);
            immagine.setMinimumWidth(100);
            immagine.setMaxHeight(100);
            immagine.setMaxWidth(100);
        }
        else {
            immagine.setVisibility(ImageView.GONE);
            immagine.setMinimumHeight(0);
            immagine.setMinimumWidth(0);
            immagine.setMaxHeight(0);
            immagine.setMaxWidth(0);
        }
    }
     */
}