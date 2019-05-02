package com.example.mrose.clickpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuInicialActivity extends AppCompatActivity {

    Button buscarParkingButton, listaParkingButton, favsButton, consultarMapaButton, cerrarsesionButton, perfilButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__inicial);

        buscarParkingButton = (Button) findViewById(R.id.buscar_parking);
        buscarParkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),BuscarParkingActivity.class);
                startActivity(intent);
            }
        });

        listaParkingButton = (Button) findViewById(R.id.lista_parking);
        listaParkingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ListaParkingActivity.class);
                startActivity(intent);
            }
        });

        favsButton = (Button) findViewById(R.id.favs);
        favsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),FavoritosActivity.class);
                startActivity(intent);
            }
        });

        consultarMapaButton = (Button) findViewById(R.id.consultarMapa);
        consultarMapaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        perfilButton = (Button) findViewById(R.id.perfil);
        perfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });

        cerrarsesionButton = (Button) findViewById(R.id.cerrar_sesion);
        cerrarsesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}
