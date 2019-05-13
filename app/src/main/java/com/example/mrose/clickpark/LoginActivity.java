package com.example.mrose.clickpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;


public class LoginActivity extends AppCompatActivity{

    private Button inicioSesionButton, registroButton, contraOlvidadaButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        inicioSesionButton = (Button) findViewById(R.id.inicio_sesion); // la var inicio sesion le corresponde
        // el boton que tiene la id = inicio_sesion

        inicioSesionButton.setOnClickListener(new View.OnClickListener() { //Realizamos el intent para una vez
            @Override                                                 //iniciado sesion vaya al maps
            public void onClick(View v) {
                Intent intent1= new Intent(getApplicationContext(),MenuInicialActivity.class);
                startActivity(intent1);

            }
        });

        registroButton = (Button) findViewById(R.id.registro);
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
            }
        });

        contraOlvidadaButton = (Button) findViewById(R.id.contra_olvidada);
        contraOlvidadaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(getApplicationContext(), RestaurarPasswordActivity.class);
                startActivity(intent3);
            }
        });
    }

}

