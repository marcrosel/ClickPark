package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class EscogerTipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazas_disp_coches);
        ImageButton atras;
        atras = findViewById(R.id.atras);


        Intent intent = getIntent();
        String floorId = intent.getStringExtra(PlantasSesActivity.PlantasSes);

        ContentResolver contentResolver = this.getContentResolver();
        String projections[] = ModelContracts.SlotModel.DEFAULT_PROJECTIONS;
        String defaultOrder = ModelContracts.FloorModel.DEFAULT_SORT;

        switch (floorId) {
            case "3":
                Cursor c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "COMMON"), defaultOrder);
                int nPlazas = c.getCount();
                //hacer que dibuje slots libres common debajo de la imagen de common
                break;

            case "4":
                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "COMMON"), defaultOrder);
                nPlazas = c.getCount();
                break;
        }
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaParkingActivity.class);
                startActivity(intent);
            }
        });
    }



}


