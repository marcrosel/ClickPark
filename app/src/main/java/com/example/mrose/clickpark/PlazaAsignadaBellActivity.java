package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class PlazaAsignadaBellActivity extends AppCompatActivity {

    TextView plazaAsignada;
    Button mapCat, QrButton;
    public final static String NombrePlazaBell = "NombrePlazaBell";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plaza_asignada_bell);

        QrButton = findViewById(R.id.QR);
        plazaAsignada= findViewById(R.id.plaza);
        mapCat = findViewById(R.id.vermapa);

        Intent intent = getIntent();
        final String floorId = intent.getStringExtra(EscogerVehiculoBellActivity.plantaIDBell);
        final String tipo = intent.getStringExtra(EscogerVehiculoBellActivity.typeSlot);

        ContentResolver contentResolver = this.getContentResolver();
        String projections[] = ModelContracts.SlotModel.DEFAULT_PROJECTIONS;
        String defaultOrder = ModelContracts.SlotModel.DEFAULT_SORT;

        Cursor c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", tipo), defaultOrder);
        c.moveToFirst();
        final String name = c.getString(c.getColumnIndex(ModelContracts.SlotModel.NAME));
        plazaAsignada.setText(name);

        mapCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivityBell.class);
                startActivity(intent);
            }
        });

        QrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QrBellActivity.class);
                intent.putExtra(NombrePlazaBell, name);
                startActivity(intent);
            }
        });
    }
}
