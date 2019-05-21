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

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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
    int datosImagenes[] = {R.drawable.parking_cat, R.drawable.parking_sescelades, R.drawable.parking_bellisens};


    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    ListaPlantas listaPlantas;
    ListView listViewParkings;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_parkings);
        CommManager.initializeQueu(this);
        listViewParkings = (ListView) findViewById(R.id.listViewParkings);

        if (! CommManager.callRequest(AppURL.FLOOR_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();



      /*
        listViewBell = (ListView) findViewById(R.id.list_view_bellissens);
        listViewCat = (ListView) findViewById(R.id.list_view_cat);
        listViewSesc = (ListView) findViewById(R.id.list_view_sescelades);
     */


        atrasButton = (ImageButton) findViewById(R.id.atras);
        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MenuInicialActivity.class);
                startActivity(intent);
            }
        });

        ContentResolver contentResolver = this.getContentResolver();

        String defaultOrder = ModelContracts.ParkingModel.DEFAULT_SORT;
        String projections[] = ModelContracts.ParkingModel.DEFAULT_PROJECTIONS;


        Cursor cursor= contentResolver.query(ModelContracts.ParkingModel.buildContentUri(), projections, null, null, defaultOrder);
        cursor.moveToFirst();
        AdaptadorParking adaptadorParking= new AdaptadorParking(this,cursor);
        listViewParkings.setAdapter(adaptadorParking);
        cursor.moveToNext();

        if(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME))=="Parking Catalunya") {
            listViewParkings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });
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









}
