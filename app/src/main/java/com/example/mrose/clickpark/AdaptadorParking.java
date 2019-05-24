package com.example.mrose.clickpark;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class AdaptadorParking extends CursorAdapter {

    private static LayoutInflater layoutInflater = null;


    public AdaptadorParking(Context context, Cursor c) {
        super(context, c);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.elementos__lista_parkings, parent, false);

    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView nameParking = (TextView) view.findViewById(R.id.Name);
        Button verMapa = (Button) view.findViewById(R.id.vermapa);
        Button verPlazas = (Button) view.findViewById(R.id.verplantas);

        ImageView imagenParking = (ImageView) view.findViewById(R.id.imagenParking);

        String name = cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.NAME));
        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ModelContracts.ParkingModel.ID)));
        if(id == 2){
            imagenParking.setImageResource(R.drawable.parking_cat);
            verPlazas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlantasCatActivity.class);
                    context.startActivity(intent);
                }
            });

            verMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapsActivityCat.class);
                    context.startActivity(intent);
                }
            });
        }

        else if(id == 1){
            imagenParking.setImageResource(R.drawable.parking_sescelades);
            verPlazas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlantasSesActivity.class);
                    context.startActivity(intent);
                }
            });

            verMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapsActivitySes.class);
                    context.startActivity(intent);
                }
            });
        }
        else if (id==4) {
            imagenParking.setImageResource(R.drawable.parking_bellisens);
            verPlazas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlantasBellActivity.class);
                    context.startActivity(intent);
                }
            });

            verMapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapsActivityBell.class);
                    context.startActivity(intent);
                }
            });
        }
        nameParking.setText(name);
        }



}
