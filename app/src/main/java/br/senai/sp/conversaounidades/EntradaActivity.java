package br.senai.sp.conversaounidades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EntradaActivity extends AppCompatActivity {

    EditText etValor;
    Spinner spOriginal, spConvertido;
    TextView tvConvertido;
    String[] sUnidades = {"mm", "cm", "m", "km"};
    List<String> alUnidades;

    float[][] fMultiplicadores = {{1        , 10      , 1000  , 1000000},
                                  {0.1f     , 1       , 100   , 100000f},
                                  {0.001f   , 0.01f   , 1     , 1000   },
                                  {0.000001f, 0.00001f, 0.001f, 1      }};

    int iOriginal = 0, iConverter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada);
        etValor = findViewById(R.id.etValor);
        spOriginal = findViewById(R.id.spOriginal);
        spConvertido = findViewById(R.id.spConvertido);
        tvConvertido = findViewById(R.id.tvConvertido);
        alUnidades = new ArrayList<>();

        tvConvertido.setText(null);

        for (int i = 0; i < sUnidades.length; i++) {
            alUnidades.add(sUnidades[i]);
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, alUnidades);
        spOriginal.setAdapter(adaptador);
        spConvertido.setAdapter(adaptador);

        spOriginal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iOriginal = position;
                calcular();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spConvertido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                iConverter = position;
                calcular();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        etValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcular();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void calcular() {
        try{
            float fValor = Float.parseFloat(etValor.getText().toString()) * fMultiplicadores[iConverter][iOriginal];
            tvConvertido.setText(String.valueOf(fValor)+ " " + sUnidades[iConverter]);
        }catch (NumberFormatException err){
            tvConvertido.setText(null);
        }
    }
}
