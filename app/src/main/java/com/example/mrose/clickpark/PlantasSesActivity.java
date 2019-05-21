package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import com.google.gson.GsonBuilder;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class PlantasSesActivity extends AppCompatActivity implements IDataReceiver {

    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    String name;
    TextView textViewP1, textViewP2;
    Button verPlazas, verPlazas2;
    ImageButton atras;
    ListaPlazas listaPlazas;

    public final static  String PlantasSes = "FloorIdSes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas_ses);

        CommManager.initializeQueu(this);
        if (! CommManager.callRequest(AppURL.SLOTS_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();

        textViewP1 = findViewById(R.id.textViewplanta0);
        textViewP2 = findViewById(R.id.textViewplanta1);
        verPlazas = findViewById(R.id.verplazasP1Ses);
        verPlazas2 = findViewById(R.id.verplazasP2Ses);
        atras = findViewById(R.id.atras);

        queryBaseData();

        verPlazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EscogerTipoActivity.class);
                intent.putExtra(PlantasSes,"3");
                startActivity(intent);
            }
        });

        verPlazas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EscogerTipoActivity.class);
                intent.putExtra(PlantasSes,"4");
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
            if(idFloor == 3)
                textViewP1.setText("                    "+name);
            else if(idFloor == 4)
                textViewP2.setText("                    "+name);


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
        String parse= "{\"slots\":"+s+"}";
        listaPlazas=gson.create().fromJson(parse,ListaPlazas.class);
        createBaseData();
    }

    private void createBaseData() {
        ContentResolver contentResolver = this.getContentResolver();

        ContentValues contentValues = new ContentValues();

        for (int i = 0; i < listaPlazas.getPlazas().size(); i++) {
            contentValues.put(ModelContracts.SlotContract.STATE_CHANGE_DATE, listaPlazas.getPlazas().get(i).getState_change_date());
            contentValues.put(ModelContracts.SlotContract.SLOT_TYPE, listaPlazas.getPlazas().get(i).getSlot_type());
            contentValues.put(ModelContracts.SlotContract.SLOT_STATE, listaPlazas.getPlazas().get(i).getSlot_state());
            contentValues.put(ModelContracts.SlotContract.SLOT_COLOR, listaPlazas.getPlazas().get(i).getSlot_color());
            contentValues.put(ModelContracts.SlotContract.COMPANY_NUMBER, listaPlazas.getPlazas().get(i).getCompany_number());
            contentValues.put(ModelContracts.SlotContract.NAME, listaPlazas.getPlazas().get(i).getName());
            contentValues.put(ModelContracts.SlotContract.ID, listaPlazas.getPlazas().get(i).getId());
            contentValues.put(ModelContracts.SlotContract.FLOOR_ID, listaPlazas.getPlazas().get(i).getFloor_id());

            String where = ModelContracts.SlotModel.buildDefaultSelection();
            String whereArgs[] = ModelContracts.SlotModel.buildDefaultSelectionArgs(listaPlazas.getPlazas().get(i).company_number);

            int numElementsActualizados = this.getContentResolver().update(ModelContracts.SlotModel.buildContentUri(), contentValues, where, whereArgs);

            if (numElementsActualizados == 0) {
                contentResolver.insert(ModelContracts.SlotModel.buildContentUri(), contentValues);
            }
        }
    }

}
