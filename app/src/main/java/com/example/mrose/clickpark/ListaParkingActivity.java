package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;




public class ListaParkingActivity extends AppCompatActivity implements IDataReceiver {

    ImageButton atrasButton;
    ImageView imageViewCat, imageViewSes, imageViewBell;
    Button buttonCat, buttonSes, buttonBell, buttonMapCat, buttonMapBell, buttonMapSes;
    //ListView listViewCat, listViewSesc, listViewBell;
    TextView textViewCat, textViewBell, textViewSes;
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    ListaPlantas listaPlantas;
    String name;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_parkings);
        CommManager.initializeQueu(this);

        if (! CommManager.callRequest(AppURL.FLOOR_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();

        imageViewBell = findViewById(R.id.imageBell);
        imageViewCat = findViewById(R.id.imageCat);
        imageViewSes = findViewById(R.id.imageSes);

        textViewBell = (TextView) findViewById(R.id.textViewBell);
        textViewCat = (TextView) findViewById(R.id.textViewCat);
        textViewSes = (TextView) findViewById(R.id.textViewSes);

        buttonBell = (Button) findViewById(R.id.buttonBell);
        buttonCat = (Button) findViewById(R.id.buttonCat);
        buttonSes = (Button) findViewById(R.id.buttonSes);

        buttonMapBell = (Button) findViewById(R.id.mapBell);
        buttonMapCat = (Button) findViewById(R.id.mapCat);
        buttonMapSes = (Button) findViewById(R.id.mapSes);


        queryBaseData();

        atrasButton = (ImageButton) findViewById(R.id.atras);
        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MenuInicialActivity.class);
                startActivity(intent);
            }
        });

        buttonBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PlantasBellActivity.class);
                startActivity(intent);

            }
        });

        buttonSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlantasSesActivity.class);
                startActivity(intent);
            }
        });

        buttonCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlantasCatActivity.class);
                startActivity(intent);
            }
        });

        buttonMapSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivitySes.class);
                startActivity(intent);
            }
        });

        buttonMapCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivityCat.class);
                startActivity(intent);
            }
        });

        buttonMapBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivityBell.class);
                startActivity(intent);
            }
        });
    }

    protected void Escribir(String name, int id){
        if(id==2) {
            textViewCat.setText(name);
            imageViewCat.setImageResource(R.drawable.parking_cat);
        }

        else if (id == 1) {
            textViewSes.setText(name);
            imageViewSes.setImageResource(R.drawable.parking_sescelades);
        }
        else if (id == 4) {
            textViewBell.setText(name);
            imageViewBell.setImageResource(R.drawable.parking_bellisens);
        }

    }

    @Override
    public void onReceiveData(String s) {
        if (s !=null) {
            if (s.length() > 0) {
                Toast.makeText(this, "Data received", Toast.LENGTH_SHORT).show();
                Log.d(TAG,s);
            }
        }
        else
        {
            Toast.makeText(this, "Data NOT received", Toast.LENGTH_SHORT).show();
            Log.e(TAG,"No data to show");
        }

        GsonBuilder gson = new GsonBuilder();
        String parse= "{\"floors\":"+s+"}";
        listaPlantas=gson.create().fromJson(parse,ListaPlantas.class);
        createBaseData();
    }

    protected void createBaseData() {
        ContentResolver contentResolver=this.getContentResolver();

        ContentValues contentValues = new ContentValues();

        for(int i=0; i<listaPlantas.getPlantas().size();i++){
            contentValues.put(ModelContracts.FloorContract.COMPANY_NUMBER, listaPlantas.getPlantas().get(i).getCompany_number());
            contentValues.put(ModelContracts.FloorContract.ID, listaPlantas.getPlantas().get(i).getId());
            contentValues.put(ModelContracts.FloorContract.NAME, listaPlantas.getPlantas().get(i).getName());
            contentValues.put(ModelContracts.FloorContract.PARKING_ID, listaPlantas.getPlantas().get(i).getId());

            String where = ModelContracts.FloorModel.buildDefaultSelection();
            String whereArgs[] = ModelContracts.FloorModel.buildIdSelectionArgs(listaPlantas.getPlantas().get(i).getId());

            int numElementsActualizados = this.getContentResolver().update(ModelContracts.FloorModel.buildContentUri(), contentValues, where, whereArgs);

            if (numElementsActualizados == 0) {
                contentResolver.insert(ModelContracts.FloorModel.buildContentUri(), contentValues);
            }


        }
    }

    protected void queryBaseData(){
        ContentResolver contentResolver = this.getContentResolver();

        String defaultOrder = ModelContracts.ParkingModel.DEFAULT_SORT;
        String projections[] = ModelContracts.ParkingModel.DEFAULT_PROJECTIONS;

        Cursor cursor= contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), projections, null, null, defaultOrder);
        int numParkings = cursor.getCount();

        cursor.moveToFirst();
        for(int i=0; i<numParkings; i++){
            name = cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME));
            id  = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.ID)));
            cursor.moveToNext();
            Escribir(name,id);
        }

    }
}
