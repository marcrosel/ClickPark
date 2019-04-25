package com.example.mrose.clickpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button inicio_sesion, registro, contra_olvidada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicio_sesion = (Button) findViewById(R.id.inicio_sesion); // la var inicio sesion le corresponde
        // el boton que tiene la id = inicio_sesion

        inicio_sesion.setOnClickListener(new View.OnClickListener() { //Realizamos el intent para una vez
            @Override                                                 //iniciado sesion vaya al maps
            public void onClick(View v) {
                Intent intent1= new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent1);

            }
        });

        registro = (Button) findViewById(R.id.registro);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
            }
        });

        contra_olvidada = (Button) findViewById(R.id.contra_olvidada);
        contra_olvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(getApplicationContext(), RestaurarpasswordActivity.class);
                startActivity(intent3);
            }
        });
    }

}

