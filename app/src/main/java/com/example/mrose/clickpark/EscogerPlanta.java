package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

public class EscogerPlanta extends AppCompatActivity implements IDataReceiver {

    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);
    ListaPlazas listaPlazas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escoger_planta);

        if (! CommManager.callRequest(AppURL.SLOTS_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();
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

    protected void createBaseData() {
        ContentResolver contentResolver=this.getContentResolver();

        ContentValues contentValues = new ContentValues();

        for(int i=0; i<listaPlazas.getPlazas().size(); i++){
            contentValues.put(ModelContracts.SlotContract.COMPANY_NUMBER, listaPlazas.getPlazas().get(i).getCompany_number());
            contentValues.put(ModelContracts.SlotContract.FLOOR_ID, listaPlazas.getPlazas().get(i).getFloor_id());
            contentValues.put(ModelContracts.SlotContract.ID, listaPlazas.getPlazas().get(i).getId());
            contentValues.put(ModelContracts.SlotContract.NAME, listaPlazas.getPlazas().get(i).getName());
            contentValues.put(ModelContracts.SlotContract.SLOT_COLOR, listaPlazas.getPlazas().get(i).getSlot_color());
            contentValues.put(ModelContracts.SlotContract.SLOT_STATE, listaPlazas.getPlazas().get(i).getSlot_state());
            contentValues.put(ModelContracts.SlotContract.SLOT_TYPE, listaPlazas.getPlazas().get(i).getSlot_type());
            contentValues.put(ModelContracts.SlotContract.STATE_CHANGE_DATE, listaPlazas.getPlazas().get(i).getState_change_date());

            String where = ModelContracts.SlotModel.buildDefaultSelection();
            String whereArgs[] = ModelContracts.LocationModel.buildIdSelectionArgs(listaPlazas.getPlazas().get(i).getId());


            int numElementsActualizados= this.getContentResolver().update(ModelContracts.SlotModel.buildContentUri(), contentValues, where, whereArgs);

            if(numElementsActualizados==0) {
                Uri insertUri = contentResolver.insert(ModelContracts.SlotModel.buildContentUri(), contentValues);
            }
        }
    }
}
