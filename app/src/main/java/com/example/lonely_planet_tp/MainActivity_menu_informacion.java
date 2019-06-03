package com.example.lonely_planet_tp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
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
        String idCiudad = "1";

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
                String idCiudad = getIntent().getExtras().getString("idPais");
                intent.putExtra("idCiudad",idCiudad);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });
    }

}
