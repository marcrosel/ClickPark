package com.example.mrose.clickpark;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button crearButton;
    Switch aceptarSwitch;
    ImageButton atrasButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_register);

        crearButton = (Button) findViewById(R.id.crear);
        crearButton.setEnabled(false);
        aceptarSwitch = (Switch) findViewById(R.id.aceptar);



        aceptarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crearButton.setEnabled(isChecked);
            }
        });


        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean guardat= guardarDatos();
                if (guardat=false){
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                }



            }
        });

        atrasButton = (ImageButton) findViewById(R.id.atras);
        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public boolean guardarDatos(){
        boolean guardat =false;
        EditText nombre = (EditText) findViewById(R.id.nombre);
        String nombreUsuario =  nombre.getText().toString();
        EditText apellidos = (EditText) findViewById(R.id.apellidosUsuario);
        String apellidosUsuario =  apellidos.getText().toString();
        EditText email = (EditText) findViewById(R.id.emailUsuario);
        String emailUsuario =  email.getText().toString();
        EditText pass = (EditText) findViewById(R.id.passwordUsuario);
        String password =  pass.getText().toString();
        EditText pass2 = (EditText) findViewById(R.id.password2);
        String password2 =  pass2.getText().toString();
        if (password.equals(password2)) {

            BaseDatosUsuarios baseUsuarios = new BaseDatosUsuarios(this, "DEMODB", null, 1);
            SQLiteDatabase db = baseUsuarios.getWritableDatabase();
            if (db != null) {
                ContentValues regitroNuevo = new ContentValues();
                regitroNuevo.put("nombre", nombreUsuario);
                regitroNuevo.put("apellidos", apellidosUsuario);
                regitroNuevo.put("email", emailUsuario);
                regitroNuevo.put("password", password);
                db.insert("tablaUsuarios", null, regitroNuevo);
                Toast.makeText(this, "Datos Almacenados", Toast.LENGTH_SHORT).show();
                guardat=true;
            }
        }
        else Toast.makeText(this, "Has introducido mal los datos", Toast.LENGTH_SHORT).show();

        return guardat;
    }

}
