package com.example.mrose.clickpark;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PerfilActivity extends AppCompatActivity {
    TextView textViewNombre;
    TextView textViewApellidos;
    TextView textViewEmail;
    TextView textViewPassword;

    ImageButton atrasButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_usuario);
        textViewNombre= findViewById(R.id.nombreUsuario);
        textViewApellidos= findViewById(R.id.apellidosUsuario);
        textViewEmail= findViewById(R.id.emailUsuario);
        textViewPassword= findViewById(R.id.passwordUsuario);
        final String email = getIntent().getExtras().getString("email");
        String usuario[] = buscarUsuario(email);
        textViewNombre.setText("  "+usuario[0]);
        textViewApellidos.setText("  "+usuario[1]);
        textViewEmail.setText("  "+email);
        textViewPassword.setText("  "+usuario[2]);

        atrasButton = (ImageButton) findViewById(R.id.atras);
        atrasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), MenuInicialActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }


    private String [] buscarUsuario (String email){
        boolean trobatEmail = false;
        String nombreUsuario = null;
        String apellidosUsuario = null;
        String passwordUsuario = null;

        BaseDatosUsuarios baseDatosUsuarios= new BaseDatosUsuarios(this, "DEMODB", null, 1);
        SQLiteDatabase db = baseDatosUsuarios.getWritableDatabase();
        if(db !=null){
            Cursor cursor = db.rawQuery("select * from tablaUsuarios", null);
            cursor.moveToFirst();
            int cantidad = cursor.getCount();

            if(cantidad !=0){
                if(cursor.moveToFirst() || trobatEmail ){
                    do{
                        String emailBBDD= cursor.getString(3);
                        trobatEmail= email.equals(emailBBDD);
                        if(trobatEmail){
                            nombreUsuario = cursor.getString(1);
                            apellidosUsuario = cursor.getString(2);
                            passwordUsuario = cursor.getString(4);
                        }
                    }while (cursor.moveToNext());
                }
            }
        }

        return new String[] {nombreUsuario, apellidosUsuario, passwordUsuario};
    }
}
