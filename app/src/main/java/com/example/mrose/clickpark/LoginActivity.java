package com.example.mrose.clickpark;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import cat.tomasgis.module.communication.CommManager;
import cat.tomasgis.module.communication.base.AppURL;
import cat.tomasgis.module.communication.listeners.IDataReceiver;
import cat.tomasgis.module.communication.listeners.StringResponseListener;

import cat.tomasgis.module.communication.CommManager;

public class LoginActivity extends AppCompatActivity implements IDataReceiver{

    private Button inicioSesionButton, registroButton, contraOlvidadaButton;
    private static final String TAG = cat.tomasgis.module.communication.commapptesting.MainActivity.class.getSimpleName();
    StringResponseListener stringListener = new StringResponseListener(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CommManager.initializeQueu(this);

        if (! CommManager.callRequest(AppURL.PARKING_URL,stringListener))
            Toast.makeText(this, "Call error", Toast.LENGTH_SHORT).show();


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
        String parse= "{\"Parking\":"+s+"}";
        ListaParkings parkings=gson.create().fromJson(parse,ListaParkings.class);

    }
}

