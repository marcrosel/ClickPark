package com.example.mrose.clickpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Inicial extends AppCompatActivity {

    Button buscar_parking, lista_parking, favs, consultar_mapa, cerrar_sesion, perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__inicial);

        buscar_parking = (Button) findViewById(R.id.buscar_parking);
        buscar_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),BuscarparkingActivity.class);
                startActivity(intent);
            }
        });

        lista_parking = (Button) findViewById(R.id.lista_parking);
        lista_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ListaparkingActivity.class);
                startActivity(intent);
            }
        });

        favs = (Button) findViewById(R.id.favs);
        favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),Favoritos.class);
                startActivity(intent);
            }
        });

        consultar_mapa = (Button) findViewById(R.id.consultarMapa);
        consultar_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        perfil = (Button) findViewById(R.id.perfil);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        cerrar_sesion = (Button) findViewById(R.id.cerrar_sesion);
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
