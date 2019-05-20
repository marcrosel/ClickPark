package com.example.mrose.clickpark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PlantasSesActivity extends AppCompatActivity {
    String name;
    TextView textViewP1, textViewP2;
    Button verPlazas, verPlazas2;
    ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas_ses);

        textViewP1 = findViewById(R.id.textViewplanta0);
        textViewP2 = findViewById(R.id.textViewplanta1);
        verPlazas = findViewById(R.id.verplazas1);
        verPlazas2 = findViewById(R.id.verplazas2);
        atras = findViewById(R.id.atras);
    }

}
