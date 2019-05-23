package com.example.mrose.clickpark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import net.glxn.qrgen.android.QRCode;

public class QrSesActivity extends AppCompatActivity {

    ImageView QR;
    Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_ses);

        QR = findViewById(R.id.imageQR);
        menuButton = findViewById(R.id.volverMenu);

        Intent intent = getIntent();
        String name = intent.getStringExtra(PlazaAsignadaSesActivity.NombrePlazaSes);

        Bitmap bitmap = QRCode.from(name).bitmap();
        QR.setImageBitmap(bitmap);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuInicialActivity.class);
                startActivity(intent);
            }
        });





    }
}
