package com.example.mrose.clickpark;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class PrePerfilActivity extends AppCompatActivity {

    private Button irPerfil;
    ImageButton atrasButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preperfil);

        irPerfil = (Button) findViewById(R.id.perfil); // la var inicio sesion le corresponde
        // el boton que tiene la id = inicio_sesion

         atrasButton = (ImageButton) findViewById(R.id.atras);

        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuInicialActivity.class);
                startActivity(intent);
            }
        });

        irPerfil.setOnClickListener(new View.OnClickListener() { //Realizamos el intent para una vez
            @Override                                                 //iniciado sesion vaya al maps
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.emailUsuario);
                String emailusuario =  email.getText().toString();
                EditText pass = (EditText) findViewById(R.id.passwordUsuario);
                String password =  pass.getText().toString();

                boolean trobat = usuarioCorrecto(emailusuario, password);
                if(trobat){
                    Intent intent1 = new Intent(getApplicationContext(), PerfilActivity.class);
                    intent1.putExtra("email", emailusuario);
                    startActivity(intent1);
                }
                else{
                    Intent intent1 = new Intent(getApplicationContext(), PrePerfilActivity.class);
                    startActivity(intent1);
                }
            }
        });

    }



    private boolean usuarioCorrecto(String email, String password){
        boolean trobat = true;
        boolean trobatEmail = false;
        boolean trobatPassword = false;
        BaseDatosUsuarios baseDatosUsuarios= new BaseDatosUsuarios(this, "DEMODB", null, 1);
        SQLiteDatabase db = baseDatosUsuarios.getWritableDatabase();
        if(db !=null){
            Cursor cursor = db.rawQuery("select * from tablaUsuarios", null);
            cursor.moveToFirst();
            int cantidad = cursor.getCount();

            if(cantidad !=0){
                if(cursor.moveToFirst() || (trobatEmail && trobatPassword)  ){
                    do{
                        String emailBBDD= cursor.getString(3);
                        String passwordBBDD = cursor.getString(4);
                        trobatEmail= email.equals(emailBBDD);
                        trobatPassword = password.equals(passwordBBDD);
                    }while (cursor.moveToNext());
                }
            }
        }

        if(!trobatEmail || !trobatPassword) Toast.makeText(this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        else trobat= true;

        return trobat;
    }
}
