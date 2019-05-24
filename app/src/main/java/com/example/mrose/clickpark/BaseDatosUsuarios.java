package com.example.mrose.clickpark;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
public class BaseDatosUsuarios  extends SQLiteOpenHelper {

    private String tablaUsuarios = "CREATE TABLE tablaUsuarios (Id INTEGER PRIMARY KEY AUTOINCREMENT, nombre Text, apellidos Text, email Text, password Text)";

    public BaseDatosUsuarios (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);

    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(tablaUsuarios);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
