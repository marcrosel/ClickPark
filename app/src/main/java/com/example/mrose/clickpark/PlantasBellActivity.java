package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class PlantasBellActivity extends AppCompatActivity {

    String name;
    TextView textViewP1;
    Button verPlazas;
    ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas_bell);

        textViewP1 = findViewById(R.id.textViewplanta0);
        verPlazas = findViewById(R.id.verplazas1);
        atras = findViewById(R.id.atras);

        queryBaseData();

        verPlazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EscogerTipoActivity.class);
                startActivity(intent);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListaParkingActivity.class);
                startActivity(intent);
            }
        });
    }











    protected void queryBaseData(){
        ContentResolver contentResolver = this.getContentResolver();

        String defaultOrder = ModelContracts.FloorModel.DEFAULT_SORT;
        String projections[] = ModelContracts.FloorModel.DEFAULT_PROJECTIONS;

        Cursor cursor= contentResolver.query(ModelContracts.FloorModel.buildContentUri(), projections, null, null, defaultOrder);
        int numPlantas = cursor.getCount();

        cursor.moveToFirst();
        for(int i=0; i<numPlantas; i++){
            name = cursor.getString(cursor.getColumnIndex(ModelContracts.FloorModel.NAME));
            int idFloor = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ModelContracts.FloorModel.ID)));
            cursor.moveToNext();
            if (idFloor == 5)
                textViewP1.setText("               "+name);


        }

    }


}
