package com.example.lonely_planet_tp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity_menu_informacion extends AppCompatActivity {
    private BasedeDatos b;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_informacion);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle parametros = this.getIntent().getExtras();
        String idCiudad = "";
        if(parametros !=null){
            idCiudad = parametros.getString("idCiudad");
        }
        TextView tv = (TextView) findViewById(R.id.tvLugar);
        b = new BasedeDatos(this,"Ciudades",null,2);
        db = b.getWritableDatabase();

        final ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) b.getCiudades(Integer.parseInt(idCiudad));

        tv.setText(ciudades.get(0).getNombre());

        ImageButton bactividades = findViewById(R.id.ibActividades);
        bactividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_actividad.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton bdormir = findViewById(R.id.ibDormir);
        bdormir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_dormir.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton bcomer = findViewById(R.id.ibComer);
        bcomer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_comida.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton btransporte = findViewById(R.id.ibTransporte);
        btransporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_transporte.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton brecomendado = findViewById(R.id.ibRecomendados);
        brecomendado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_recomendado.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton bocio = findViewById(R.id.ibOcio);
        bocio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_ocio.class);
                String idCiudad = getIntent().getExtras().getString("idCiudad");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        ImageButton binstagram = findViewById(R.id.ibInstagram);
        binstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://www.instagram.com/despegar/?hl=es-la")));
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.instagram.android"));
                    startActivity(i);
                }
            }
        });

        ImageButton btwitter = findViewById(R.id.ibTwitter);
        btwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=despegar")));
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.twitter.android"));
                    startActivity(i);
                }

            }
        });

        ImageButton bpinterest = findViewById(R.id.ibPinterest);
        bpinterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("pinterest://www.pinterest.com/despegarcom")));
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.pinterest"));
                    startActivity(i);
                }
            }
        });

        ImageButton bface = findViewById(R.id.ibFacebook);
        bface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String facebookId = "fb://page/108270726024539";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId )));
                } catch (Exception e) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana&hl=es_AR"));
                    startActivity(i);
                }
            }
        });



        ImageButton ib = findViewById(R.id.ibatras);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_lugares.class);


                Bundle bundle = new Bundle();
                startActivity(intent);


            }
        });
    }

    public void GoToMap(View view){
        Intent intent = new Intent(this,DondeComerActivity.class);
        startActivity(intent);
    }

}
