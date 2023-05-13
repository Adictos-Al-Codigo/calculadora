package com.jahircelorio.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    public int [] canciones = {R.raw.pista001,R.raw.pista002,R.raw.pista003,R.raw.pista004};

    private int cancionActual = 0;

    EditText editText;

    TextView textView;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnBorrar, btnComa;

    double numero1, numero2;
    String operacion;

    boolean comaPresionada = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextText);

        textView = findViewById(R.id.nombre_casio);

        // Fuente 1

        String fuente = "casio.ttf";

        // Fuente 2

        String fuente2 = "matrix2.ttf";

        // Cambio de letra

        Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(),fuente);

        Typeface typeface2 = Typeface.createFromAsset(MainActivity.this.getAssets(),fuente2);

        // Incializando mi media player

        mediaPlayer = MediaPlayer.create(this,canciones[cancionActual]);
        mediaPlayer.setVolume(0.5f, 0.5f);


        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_cero);

        btnSumar = findViewById(R.id.btn_sumar);
        btnRestar = findViewById(R.id.btn_restar);
        btnMultiplicar = findViewById(R.id.btn_multiplicar);
        btnDividir = findViewById(R.id.btn_dividir);
        btnIgual = findViewById(R.id.btn_igual);
        btnBorrar = findViewById(R.id.btn_borrar);
        btnComa = findViewById(R.id.btn_coma);

        btn1.setOnClickListener(v -> editText.append("1"));

        btn2.setOnClickListener(v -> editText.append("2"));

        btn3.setOnClickListener(v -> editText.append("3"));

        btn4.setOnClickListener(v -> editText.append("4"));

        btn5.setOnClickListener(v -> editText.append("5"));

        btn6.setOnClickListener(v -> editText.append("6"));

        btn7.setOnClickListener(v -> editText.append("7"));

        btn8.setOnClickListener(v -> editText.append("8"));

        btn9.setOnClickListener(v -> editText.append("9"));

        btn0.setOnClickListener(v -> editText.append("0"));

        btnComa.setOnClickListener(v ->
        {
            editText.append(",");
            comaPresionada = true;
        });


        btnSumar.setOnClickListener(v -> {
            operacion = "suma";
            String input = editText.getText().toString();
            if (!input.isEmpty()) {
                if (comaPresionada) {
                    input = input.replace(",", ".");
                    comaPresionada = false;
                }

                numero1 = Double.parseDouble(input);
                editText.getText().clear();
            }
        });

        btnRestar.setOnClickListener(v -> {
            operacion = "resta";
            String input = editText.getText().toString();
            if (!input.isEmpty()) {
                if (comaPresionada) {
                    input = input.replace(",", ".");
                    comaPresionada = false;
                }
                numero1 = Double.parseDouble(input);
                editText.getText().clear();
            }
        });

        btnMultiplicar.setOnClickListener(v ->{
            operacion = "multiplicacion";
            String input = editText.getText().toString();
            if (!input.isEmpty()){
                if (comaPresionada) {
                    input = input.replace(",", ".");
                    comaPresionada = false;
                }
                numero1 = Double.parseDouble(input);
                editText.getText().clear();
            }
        });

        btnDividir.setOnClickListener(v ->{
            operacion = "division";
            String input = editText.getText().toString();
            if (!input.isEmpty()){
                if (comaPresionada) {
                    input = input.replace(",", ".");
                    comaPresionada = false;
                }
                numero1 = Double.parseDouble(input);
                editText.getText().clear();
            }
        });

        btnIgual.setOnClickListener(v -> {
            String input = editText.getText().toString();
            if (!input.isEmpty()) {
                if (comaPresionada) {
                    input = input.replace(",", ".");
                }

                numero2 = Double.parseDouble(input);

                switch (operacion) {
                    case "suma":
                        numero1 = numero1 + numero2;
                        break;
                    case "resta":
                        numero1 = numero1 - numero2;
                        break;
                    case "multiplicacion":
                        numero1 = numero1 * numero2;
                        break;
                    case "division":
                        if (numero2 == 0) {
                            Toast.makeText(MainActivity.this, "No se Puede Dividir Entre Cero", Toast.LENGTH_SHORT).show();
                            editText.getText().clear();
                            return;
                        }
                        numero1 = numero1 / numero2;
                        break;
                }

                editText.setText(String.valueOf(numero1));
                numero2 = 0;
                operacion = "";
            }
        });

        btnBorrar.setOnClickListener(v -> {
            editText.getText().clear();
            numero1 = 0;
            numero2 = 0;
            operacion = "";
        });

        textView.setTypeface(typeface);
        editText.setTypeface(typeface2);
    }

    public void reproducirMusica(View view) {
        // Reproducir la música cuando se presione un botón o realices alguna acción
        mediaPlayer.start();
    }

    public void detenerMusica(View view) {
        // Detener la reproducción de la música
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Cambiar de canción

    public void cambiarCancion(View view) {
        // Detener la reproducción de la canción actual
        mediaPlayer.stop();
        mediaPlayer.release();

        // Avanzar a la siguiente canción en el arreglo
        cancionActual = (cancionActual + 1) % canciones.length;

        // Crear un nuevo MediaPlayer con la siguiente canción
        mediaPlayer = MediaPlayer.create(this, canciones[cancionActual]);
        mediaPlayer.setVolume(0.5f, 0.5f);

        // Reproducir la nueva canción
        mediaPlayer.start();
    }

    // Otros métodos y lógica de la calculadora


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Liberar los recursos del MediaPlayer cuando la actividad se destruya
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}


