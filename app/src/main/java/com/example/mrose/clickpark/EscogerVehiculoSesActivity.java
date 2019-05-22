package com.example.mrose.clickpark;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class EscogerVehiculoSesActivity extends AppCompatActivity {

    ImageButton atras,comunImage, electricoImage, discapacitadoImage, biciImage, caravanaImage, motoImage;;
    TextView dispCommon, dispElectric, dispDiscapacitado, dispBicicleta, dispCaravana, dispMoto;
    public final static  String plantaIDSes = "FloorIdSes";
    public final static String typeSlot = "TypeSlot";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plazas_disp_ses);

        atras = findViewById(R.id.atras);
        dispCommon = findViewById(R.id.dispCommon);
        dispElectric= findViewById(R.id.dispElectric);
        dispDiscapacitado= findViewById(R.id.dispDiscapacitados);
        dispBicicleta = findViewById(R.id.dispBici);
        dispCaravana= findViewById(R.id.dispCaravana);
        dispMoto= findViewById(R.id.dispMoto);
        comunImage = findViewById(R.id.common);
        electricoImage = findViewById(R.id.electric);
        discapacitadoImage = findViewById(R.id.disc);
        biciImage = findViewById(R.id.bici);
        caravanaImage = findViewById(R.id.caravana);
        motoImage = findViewById(R.id.moto);


        Intent intent = getIntent();
        final String floorId = intent.getStringExtra(PlantasSesActivity.PlantasSes);

        ContentResolver contentResolver = this.getContentResolver();
        String projections[] = ModelContracts.SlotModel.DEFAULT_PROJECTIONS;
        String defaultOrder = ModelContracts.FloorModel.DEFAULT_SORT;
        int nPlazas = 0;
        switch (floorId) {
            case "3":
                Cursor c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "COMMON"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        comunImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        comunImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"COMMON");
                                startActivity(intent);
                            }
                        });
                    }

                }
                dispCommon.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "ELECTRIC"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        electricoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        electricoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"ELECTRIC");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispElectric.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "DISABLED"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        discapacitadoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        discapacitadoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"DISABLED");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispDiscapacitado.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "BICYCLE"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        biciImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        biciImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"BICYCLE");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispBicicleta.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "CARAVAN"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        caravanaImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        caravanaImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"CARAVAN");
                                startActivity(intent);
                            }
                        });
                    }

                }
                dispCaravana.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "MOTORBIKE"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        motoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        motoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"MOTORBIKE");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispMoto.setText("  "+ nPlazas);

                break;

            case "4":
                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "COMMON"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        comunImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        comunImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"COMMON");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispCommon.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "ELECTRIC"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        electricoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        electricoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"ELECTRIC");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispElectric.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "DISABLED"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        discapacitadoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        discapacitadoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"DISABLED");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispDiscapacitado.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "BICYCLE"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        biciImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        biciImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"BICYCLE");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispBicicleta.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "CARAVAN"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        caravanaImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        caravanaImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"CARAVAN");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispCaravana.setText("  "+ nPlazas);


                c = contentResolver.query(ModelContracts.SlotModel.buildContentUri(), projections, ModelContracts.SlotModel.buildFloorStateTypeSelection(), ModelContracts.SlotModel.buildFloorStateTypeSelectionArgs(floorId, "FREE", "MOTORBIKE"), defaultOrder);
                if (c != null) {
                    nPlazas = c.getCount();
                    if (nPlazas==0){
                        motoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "No hay plazas disponibles", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        motoImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), PlazaAsignadaSesActivity.class);
                                intent.putExtra(plantaIDSes,floorId);
                                intent.putExtra(typeSlot,"MOTORBIKE");
                                startActivity(intent);
                            }
                        });
                    }
                }
                dispMoto.setText("  "+ nPlazas);
                break;


        }
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PlantasSesActivity.class);
                startActivity(intent);
            }
        });
    }



}


