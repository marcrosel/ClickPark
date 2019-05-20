package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class PlantasBellActivity extends AppCompatActivity {

    int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas_bell);
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
            id  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ModelContracts.FloorModel.PARKING_ID)));
            cursor.moveToNext();
        }

    }
}
