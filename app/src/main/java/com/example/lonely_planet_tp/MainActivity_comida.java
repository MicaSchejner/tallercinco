package com.example.lonely_planet_tp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_comida extends AppCompatActivity {
    private BasedeDatos b;
    private SQLiteDatabase db;
    String idCiudad = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle parametros = this.getIntent().getExtras();

        if(parametros !=null){
            idCiudad = parametros.getString("idCiudad");
        }
        TextView tv = (TextView) findViewById(R.id.tv_lugar);
        b = new BasedeDatos(this,"Ciudades",null,2);
        db = b.getWritableDatabase();

        final ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) b.getCiudades(Integer.parseInt(idCiudad));

        tv.setText(ciudades.get(0).getNombre());


        TextView tvdesc = (TextView) findViewById(R.id.tv_descripcion);

        final ArrayList<String> desc = (ArrayList<String>) b.getComer(Integer.parseInt(idCiudad));

        tvdesc.setText(desc.get(0));



        tvdesc.setMovementMethod(new ScrollingMovementMethod());



        ImageView ivlugar = (ImageView) findViewById(R.id.iv_lugar);
        int resID = getResources().getIdentifier("comer" + idCiudad.toString()  , "drawable", getPackageName());
        ivlugar.setImageResource(resID);

        ImageButton ib = findViewById(R.id.ibatras);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_menu_informacion.class);

                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);


            }
        });

        final double lng = 151;
        final double lat = -34;

        ImageButton ib_map = findViewById(R.id.ibmap);
        ib_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DondeComerActivity.class);

                intent.putExtra("lng",lng);
                intent.putExtra("lat", lat);
                Bundle bundle = new Bundle();
                startActivity(intent);


            }
        });

    }

}
