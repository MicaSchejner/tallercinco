package com.example.lonely_planet_tp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasedeDatos  extends SQLiteOpenHelper {
    String sqlCreateCiudades     = "CREATE TABLE ciudades(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreateActividades  = "CREATE TABLE ciudades_actividades(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreatedormir       = "CREATE TABLE ciudades_dormir(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreatecomer        = "CREATE TABLE ciudades_comer(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreatetransporte   = "CREATE TABLE ciudades_transporte(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreaterecomendados = "CREATE TABLE ciudades_recomendados(id INTEGER PRIMARY KEY , descripcion TEXT)";
    String sqlCreateocio         = "CREATE TABLE ciudades_ocio(id INTEGER PRIMARY KEY , descripcion TEXT)";

    public BasedeDatos(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateCiudades);
        db.execSQL(sqlCreateActividades);
        db.execSQL(sqlCreatedormir);
        db.execSQL(sqlCreatecomer);
        db.execSQL(sqlCreatetransporte);
        db.execSQL(sqlCreaterecomendados);
        db.execSQL(sqlCreateocio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS ciudades");
        db.execSQL("DROP TABLE IF EXISTS ciudades_actividades");
        db.execSQL("DROP TABLE IF EXISTS ciudades_dormir");
        db.execSQL("DROP TABLE IF EXISTS ciudades_comer");
        db.execSQL("DROP TABLE IF EXISTS ciudades_transporte");
        db.execSQL("DROP TABLE IF EXISTS ciudades_recomendados");
        db.execSQL("DROP TABLE IF EXISTS ciudades_ocio");
        db.execSQL(sqlCreateCiudades);
        db.execSQL(sqlCreateActividades);
        db.execSQL(sqlCreatedormir);
        db.execSQL(sqlCreatecomer);
        db.execSQL(sqlCreatetransporte);
        db.execSQL(sqlCreaterecomendados);
        db.execSQL(sqlCreateocio);
    }

    public List<Ciudad> getCiudades() {
        List<Ciudad> ejemploList = new ArrayList<Ciudad>();
        String selectQuery = "SELECT  * FROM ciudades order by 2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarCiudades();
        }
        if (cursor.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(Integer.parseInt(cursor.getString(0)),cursor.getString(1));

                ejemploList.add(ciudad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ejemploList;
    }
    public List<Ciudad> getCiudades(int id) {
        List<Ciudad> ejemploList = new ArrayList<Ciudad>();
        String selectQuery = "SELECT  * FROM ciudades WHERE id = " + id + " order by 2";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarCiudades();
        }
        if (cursor.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(Integer.parseInt(cursor.getString(0)),cursor.getString(1));

                ejemploList.add(ciudad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ejemploList;
    }
    public void GuardarCiudad (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades",null, contenedor);
    }
    public void CargarCiudades(){
        GuardarCiudad(1,"Rio De Janeiro");
        GuardarCiudad(2,"Cuzco");
        GuardarCiudad(3,"Pekin");
        GuardarCiudad(4,"Roma");
        GuardarCiudad(5,"Petra");
        GuardarCiudad(6,"Agra");
    }

    public List<String> getActividades(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_actividades WHERE id = " + id  +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarActividades();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarActividades (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_actividades",null, contenedor);
    }
    public void CargarActividades(){
        GuardarActividades(1,"Rio De Janeiro");
        GuardarActividades(2,"Cuzco");
        GuardarActividades(3,"Pekin");
        GuardarActividades(4,"Roma");
        GuardarActividades(5,"Petra");
        GuardarActividades(6,"Agra");
    }

    public List<String> getDormir(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_dormir WHERE id = "  + id +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarDormir();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarDormir (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_dormir",null, contenedor);
    }
    public void CargarDormir(){
        GuardarDormir(1,"Rio De Janeiro");
        GuardarDormir(2,"Cuzco");
        GuardarDormir(3,"Pekin");
        GuardarDormir(4,"Roma");
        GuardarDormir(5,"Petra");
        GuardarDormir(6,"Agra");
    }

    public List<String> getComer(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_comer WHERE id = " + id +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarComer();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarComer (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_comer",null, contenedor);
    }
    public void CargarComer(){
        GuardarComer(1,"Rio De Janeiro");
        GuardarComer(2,"Cuzco");
        GuardarComer(3,"Pekin");
        GuardarComer(4,"Roma");
        GuardarComer(5,"Petra");
        GuardarComer(6,"Agra");
    }

    public List<String> getTransporte(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_transporte WHERE id = " + id +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarTransporte();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarTransporte (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_transporte",null, contenedor);
    }
    public void CargarTransporte(){
        GuardarTransporte(1,"Rio De Janeiro");
        GuardarTransporte(2,"Cuzco");
        GuardarTransporte(3,"Pekin");
        GuardarTransporte(4,"Roma");
        GuardarTransporte(5,"Petra");
        GuardarTransporte(6,"Agra");
    }

    public List<String> getRecomendados(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_recomendados WHERE id = " + id +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarRecomendados();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarRecomendados (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_recomendados",null, contenedor);
    }
    public void CargarRecomendados(){
        GuardarRecomendados(1,"Rio De Janeiro");
        GuardarRecomendados(2,"Cuzco");
        GuardarRecomendados(3,"Pekin");
        GuardarRecomendados(4,"Roma");
        GuardarRecomendados(5,"Petra");
        GuardarRecomendados(6,"Agra");
    }

    public List<String> getOcio(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_ocio WHERE id = " + id +   " order by 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarOcio();
        }
        if (cursor.moveToFirst()) {
            do {
                List.add( cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return List;
    }
    public void GuardarOcio (int id, String descrip){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        database.insertOrThrow("ciudades_ocio",null, contenedor);
    }
    public void CargarOcio(){
        GuardarOcio(1,"Rio De Janeiro");
        GuardarOcio(2,"Cuzco");
        GuardarOcio(3,"Pekin");
        GuardarOcio(4,"Roma");
        GuardarOcio(5,"Petra");
        GuardarOcio(6,"Agra");
    }
}
