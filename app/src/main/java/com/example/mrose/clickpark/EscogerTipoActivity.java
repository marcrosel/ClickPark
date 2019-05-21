package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class EscogerTipoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazas_disp_coches);
    }

    ContentResolver contentResolver;
    //Cursor c = contentResolver.query(ModelContracts)
}
