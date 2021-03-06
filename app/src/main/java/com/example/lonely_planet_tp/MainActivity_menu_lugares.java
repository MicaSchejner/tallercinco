package com.example.lonely_planet_tp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import java.util.ArrayList;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity_menu_lugares extends AppCompatActivity {
    private Spinner spinnerciudades;
    private ArrayList<Ciudad> lista;
    private ArrayAdapter spinnerAdapter;
    private BasedeDatos b;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_lugares);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton ib1 = findViewById(R.id.ib1);
        ImageButton ib2 = findViewById(R.id.ib2);
        ImageButton ib3 = findViewById(R.id.ib3);
        ImageButton ib4 = findViewById(R.id.ib4);
        ImageButton ib5 = findViewById(R.id.ib5);
        ImageButton ib6 = findViewById(R.id.ib6);
        ImageButton ibspin = findViewById(R.id.ibspin);

        spinnerciudades=(Spinner) findViewById(R.id.spinnerCiudades);

        b = new BasedeDatos(this,"Ciudades",null,2);
        db = b.getWritableDatabase();

        final ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) b.getCiudades();

        ArrayAdapter<Ciudad> adapter = new ArrayAdapter<Ciudad>(this, android.R.layout.simple_spinner_item,ciudades);

        spinnerciudades.setAdapter(adapter);
        spinnerciudades.setOnItemSelectedListener(new OnItemSelectedListener() {

            TextView tv = findViewById(R.id.tvCiudades);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ciudad personaSeleccionada = (Ciudad)spinnerciudades.getSelectedItem();
                tv.setText(personaSeleccionada.getId()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "1";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "2";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "3";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });
        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "4";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });
        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "5";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);


            }
        });
        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                String idCiudad = "6";

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);


            }
        });

        ibspin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);
                TextView tv = findViewById(R.id.tvCiudades);
                String idCiudad = tv.getText().toString();

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

    }

    public void GoToMap(View view){
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

}
