package com.example.mycarmanager;
import android.graphics.Bitmap;
import android.graphics.*;

public class ColorBlindFilter {

    // Enumerazione per i tipi di daltonismo
    public enum ColorBlindType {DEUTERANOPIA, PROTANOPIA, TRITANOPIA};

    // Matrice per la correzione della deuteranopia
    public static final float[] MATRIX_DEUTERANOPIA = {
            0.625f, 0.375f, 0, 0, 0,
            0.7f, 0.3f, 0, 0, 0,
            0, 0.3f, 0.7f, 0, 0,
            0, 0, 0, 1, 0,
            0, 0, 0, 0, 1
    };

    // Matrice per la correzione della protanopia
    public static final float[] MATRIX_PROTANOPIA = {
            0.567f, 0.433f, 0, 0, 0,
            0.558f, 0.442f, 0, 0, 0,
            0, 0.242f, 0.758f, 0, 0,
            0, 0, 0, 1, 0,
            0, 0, 0, 0, 1
    };

    // Matrice per la correzione della tritanopia
    public static final float[] MATRIX_TRITANOPIA = {
            0.95f, 0.05f, 0, 0, 0,
            0, 0.433f, 0.567f, 0, 0,
            0, 0.475f, 0.525f, 0, 0,
            0, 0, 0, 1, 0,
            0, 0, 0, 0, 1
    };

    public static Bitmap applyFilter(Bitmap source, ColorBlindType type) {
        int width = source.getWidth();
        int height = source.getHeight();
        Bitmap filteredBitmap = Bitmap.createBitmap(width, height, source.getConfig());

        float[] matrix;

        switch (type) {
            case DEUTERANOPIA:
                matrix = MATRIX_DEUTERANOPIA;
                break;
            case PROTANOPIA:
                matrix = MATRIX_PROTANOPIA;
                break;
            case TRITANOPIA:
                matrix = MATRIX_TRITANOPIA;
                break;
            default:
                matrix = null;
                break;
        }

        if (matrix != null) {
            ColorMatrix colorMatrix = new ColorMatrix(matrix);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);

            Paint paint = new Paint();
            paint.setColorFilter(filter);

            Canvas canvas = new Canvas(filteredBitmap);
            canvas.drawBitmap(source, 0, 0, paint);
        }
        else {
            // Nessuna correzione applicata
            return source;
        }

        return filteredBitmap;
    }
}