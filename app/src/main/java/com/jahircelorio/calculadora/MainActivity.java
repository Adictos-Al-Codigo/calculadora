package com.jahircelorio.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
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
                            Toast.makeText(MainActivity.this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
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
    }
}
