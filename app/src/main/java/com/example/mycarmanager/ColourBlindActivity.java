package com.example.mycarmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ColourBlindActivity extends AppCompatActivity {

    private ImageView imageView;
    private RadioGroup radioGroup;
    private Button applyButton;
    private TextView filterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_blind_v2);

        imageView = findViewById(R.id.colourBlindExampleImage);
        radioGroup = findViewById(R.id.radioButtonsContainer);
        applyButton = findViewById(R.id.applyFilterButton);
        filterName = findViewById(R.id.colourBlindExampleText);

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
                    imageView.setImageResource(R.drawable.car_rainbow_dodge_img_deuteran);
                    filterName.setText("DEUTERAN FIX FILTER");
                } else if (selectedRadioButton.getId() == R.id.protanopiaRadioButton) {
                    imageView.setImageResource(R.drawable.car_rainbow_dodge_img_protan);
                    filterName.setText("PROTAN FIX FILTER");
                } else if (selectedRadioButton.getId() == R.id.tritanopiaRadioButton) {
                    imageView.setImageResource(R.drawable.car_rainbow_dodge_img_tritan);
                    filterName.setText("TRITAN FIX FILTER");
                }
            }
        });

        // Listener pulsante apply
        applyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reimposta l'immagine iniziale
                imageView.setImageResource(R.drawable.car_rainbow_dodge_img);

                // Da implementare la logica inerente l'applicazione del filtro selezionato
            }
        });
    }
}