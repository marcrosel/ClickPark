package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
//import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;


public class LoginActivity extends AppCompatActivity{

    private Button inicioSesionButton, registroButton, contraOlvidadaButton;
    private static final String TAG = LoginActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //clearAllData();
        //createBaseData();
        //queryAllData();
        //updateBaseData();
        //queryBaseData();


        inicioSesionButton = (Button) findViewById(R.id.inicio_sesion); // la var inicio sesion le corresponde
        // el boton que tiene la id = inicio_sesion

        inicioSesionButton.setOnClickListener(new View.OnClickListener() { //Realizamos el intent para una vez
            @Override                                                 //iniciado sesion vaya al maps
            public void onClick(View v) {
                Intent intent1= new Intent(getApplicationContext(),MenuInicialActivity.class);
                startActivity(intent1);

            }
        });

        registroButton = (Button) findViewById(R.id.registro);
        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2= new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent2);
            }
        });

        contraOlvidadaButton = (Button) findViewById(R.id.contra_olvidada);
        contraOlvidadaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3= new Intent(getApplicationContext(), RestaurarPasswordActivity.class);
                startActivity(intent3);
            }
        });
    }

}

/*
    protected void clearAllData()
    {

        int numElementsDeleted;
        ContentResolver contentResolver = this.getContentResolver();

        //Slot
        numElementsDeleted = contentResolver.delete(ModelContracts.SlotModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Slots deleted: %d",numElementsDeleted));


        //Floor
        numElementsDeleted = contentResolver.delete(ModelContracts.FloorModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Floors deleted %d",numElementsDeleted));

        //Location
        numElementsDeleted = contentResolver.delete(ModelContracts.LocationModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Locations deleted %d",numElementsDeleted));

        //Parking
        numElementsDeleted = contentResolver.delete(ModelContracts.ParkingModel.buildContentUri(),null,null);
        Log.d(TAG,String.format("Parkings deleted %d",numElementsDeleted));

    }
*/
