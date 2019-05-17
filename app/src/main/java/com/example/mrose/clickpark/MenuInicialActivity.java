package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class MenuInicialActivity extends AppCompatActivity implements IDataReceiver{

    Button buscarParkingButton, listaParkingButton, favsButton, consultarMapaButton, cerrarsesionButton, perfilButton;
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    ListaLocations listaLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__inicial);
        CommManager.initializeQueu(this);

        if (! CommManager.callRequest(AppURL.LOCATIOM_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();



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
        String parse= "{\"locations\":"+s+"}";
        listaLocations=gson.create().fromJson(parse,ListaLocations.class);
        createBaseData();

    }


    protected void createBaseData() {
        ContentResolver contentResolver=this.getContentResolver();

        ContentValues contentValues = new ContentValues();

        for(int i=0; i<listaLocations.getLocations().size(); i++){
            contentValues.put(ModelContracts.LocationContract.ID, listaLocations.getLocations().get(i).getId());
            contentValues.put(ModelContracts.LocationContract.LATITUDE, listaLocations.getLocations().get(i).getLatitude());
            contentValues.put(ModelContracts.LocationContract.LONGITUDE, listaLocations.getLocations().get(i).getLongitude());
            contentValues.put(ModelContracts.LocationContract.NAME, listaLocations.getLocations().get(i).getCity());
            contentValues.put(ModelContracts.LocationContract.POSTAL_CODE, listaLocations.getLocations().get(i).getPostal_code());
            contentValues.put(ModelContracts.LocationContract.STATE_PROVINCE, listaLocations.getLocations().get(i).getState_province());
            contentValues.put(ModelContracts.LocationContract.STREET_ADDRESS, listaLocations.getLocations().get(i).getStreet_address());

            String where = ModelContracts.LocationModel.buildIdSelection();
            String whereArgs[] = ModelContracts.LocationModel.buildIdSelectionArgs(listaLocations.getLocations().get(i).getId());


            int numElementsActualizados= this.getContentResolver().update(ModelContracts.LocationModel.buildContentUri(), contentValues, where, whereArgs);

            if(numElementsActualizados==0) {
                Uri insertUri = contentResolver.insert(ModelContracts.LocationModel.buildContentUri(), contentValues);
            }
        }
    }


}
