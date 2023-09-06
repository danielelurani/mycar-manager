package com.example.mycarmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ColorBlindActivity extends AppCompatActivity {

    private ImageView imageView;
    private RadioGroup radioGroup;
    private Button applyButton;
    private TextView filterName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blind_v2);

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

        // Listener pulsante apply
        applyButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                if (checkedRadioButtonId == R.id.noColourBlindRadioButton) {
                    // Nessun filtro
                    imageView.setImageResource(R.drawable.car_rainbow_dodge_img);
                } else if (checkedRadioButtonId == R.id.deuteranopiaRadioButton) {
                    // Applica il filtro Deuteranopia
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.DEUTERANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                } else if (checkedRadioButtonId == R.id.protanopiaRadioButton) {
                    // Applica il filtro Protanopia
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.PROTANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                } else if (checkedRadioButtonId == R.id.tritanopiaRadioButton) {
                    // Applica il filtro Tritanopia
                    Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.car_rainbow_dodge_img);
                    Bitmap filteredBitmap = ColorBlindFilter.applyFilter(originalBitmap, ColorBlindFilter.ColorBlindType.TRITANOPIA);
                    imageView.setImageBitmap(filteredBitmap);
                }
            }
        });

    }
}