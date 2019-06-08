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
        GuardarActividades(1,"Rio de Janeiro es sin lugar a dudas, una de las mejores ciudades de Sudamérica, para practicar todo tipo de deportes y actividades al aire libre. El clima tropical, la variedad de su geografía con los cerros asomándose sobre el mar, sus extensas playas y las decenas de kilómetros de paseos peatonales y ciclovías, invitan a aprovechar cada minuto para disfrutar de este paraíso urbano.Se puede recorrer la orla carioca en bicicleta para sentir la brisa del mar en la cara, o dar toda la vuelta a la Laguna Rodrigo de Freitas para observar las distintas perspectivas de los morros que la rodean. Allí mismo se puede probar a deslizarse sobre el agua practicando el esquí acuático ante la atenta mirada de las numerosas garzas.");
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
        GuardarDormir(1,"Los distritos de la bahía que forman la ensenada de Río de Janeiro se ordenan de una manera poco convencional. Cada uno ocupando las zonas libres que un día fueron el viejo extrarradio de la ciudad o que fueron ganadas al mar en el último siglo.El viajero podrá disfrutar de ambientes y de alojamientos muy urbanos, algo más caros, en el centro; muy diferentes de los de las zonas residenciales de Lagoa o Flamengo, en las que predominan los apartamentos turísticos con las mejores categorías y calidades.Por su parte los distritos de playa, como los de Ipanema, Copacabana o Botafogo, al borde de sus arenas ofrecen alojamientos de todo tipo, muy variados en cuanto a calidades y precios. Y los de Santa Teresa más bohemios y de un nivel medio-bajo.En general las mejores zonas para hospedarse en Río de Janeiro son Copacabana e Ipanema. Además de éstas hay otras zonas muy recomendables como Centro, Flamengo, Botafogo, Lagoa, Leblon, e incluso Santa Marta.");
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
        GuardarComer(1,"La comida en Río es muy variopinta, ya que sus platos son resultado de muchas influencias: indígenas, africanas, portuguesas, italianas, alemanas, árabes...Las recetas de Rio de Janeiro gustan mucho a los turistas por su peculiar sabor y la variedad de ingredientes, únicas en el mundo.Mariscos, arroz con frijoles, frutas tropicales, carne asada y derivados del maíz son algunos de los principales ingredientes que forman los platos autóctonos de Brasil, aunque cada región de Brasil tiene su repertorio de recetas determinadas por su herencia cultural, los ingredientes de la zona y lo que sea más apropiado comer según el clima.Una de las particularidades de la gastronomia de Rio de Janeiro son las churrasquerías que se hallan por toda la ciudad y que ofrecen diferentes cortes de carnes a sus clientes. La gama de ensaladas de frutas y verduras es muy amplia en estos locales. Comer en estos sitios permite acercarse a la cocina brasileña más auténtica, ya que se puede probar un poco de todo y el precio es muy asequible para todos los bolsillos.Hay también una serie de frutas que tienes que probar en Río, como maracuyá (fruta de la pasión), guayaba, papaya, caña de azúcar, coco y mango, cuyos sabores son realmente espectaculares. ");
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
        GuardarTransporte(1,"En términos generales, moverse por Rio de Janerio es sencillo y barato. El centro y los distintos barrios de la zona sur están muy bien comunicados entre sí y el metro es el medio de transporte más práctico para moverse por esta.El sistema de taxis también funciona bien, es seguro y resulta fácil encontrar uno libre porque hay muchos en circulación.El metro de Rio de Janeiro es rápido, cómodo, económico y seguro. Su precio es de R$4,10, tan sólo ligeramente mayor que el del autobús urbano y es más rápido que cualquier otro transporte a causa del intenso tráfico que hay en la ciudad. Tanto las líneas de autobuses de la ciudad como las paradas están organizadas en 5 grupos: BRS 1, BRS 2, BRS 3, BRS 4 y BRS 5. De esta manera cada autobus para sólo en la parada que le corresponda, es decir, aquella que coincida con el grupo al que pertenece. Todos ellos tienen indicado en su panel luminoso de la parte frontal su número y su grupo. Las Bicis Aunque lógicamente no es un medio de transporte para recorrer grandes distancias, en Rio de Janeiro existe una red de ciclovías muy extensa que une las playas de Sao Conrado, Leblon, Ipanema, Copacabana, Botafogo y Flamengo. Puede ser una forma muy divertida y saludable de llegar hasta alguna de estas playas.");
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
        GuardarRecomendados(1,"Río de Janeiro es una ciudad de una enorme belleza natural, pero no pienses que es algo que los cariocas lo dan por sentado. Los amantes de la playa contemplan el atardecer en Ipanema cada tarde para disfrutar de un espectáculo maravilloso. Si quieres participar de este espectáculo natural siéntate en una roca de Arpoador (justo al final de la playa de Ipanema) con una bebida fresca y contempla la puesta de sol.No te marches cuando caiga la noche. Con frecuencia hay conciertos y fiestas gratis en la playa o en los parques cercanos durante los fines de semana, así que puedes aprovechar para darte un chapuzón nocturno. Cuando cae el sol se encienden los focos y los reflejos de la luz en el agua invitan a darse un baño.");
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
        GuardarOcio(1,"Muchas son las playas de Río de Janeiro y las hay para todos los gustos. Las más famosas son sin duda la playa de Ipanema y la de Copacabana, cuyo paseo marítimo con su ondulante forma en blanco y negro, es reconocida mundialmente. En estas playas es donde se reúne más gente en una mezcla fantástica de color, luz, música y alegría.Pero además de estas dos playas, hay otras que se ajustan a todos los gustos. Si lo que se busca es una playa pequeña, tranquila y de fácil acceso, la playa Vermelha a los pies del Pan de Azúcar, es la indicada. La playa de Leme es perfecta para practicar bodysurf, o para ver a otros practicándolo desde su paseo de los pescadores.Por su parte, las olas del mar en Arpoador son buenas para el surf, aunque si por algo es famosa esta playa es por ser la cita obligada todos los días al caer la tarde. Desde lo alto de la enorme piedra de Arpoador (hay que ir con tiempo para coger buen lugar) se tienen vistas fantásticas de la puesta del sol, que en verano se oculta tras el morro Dois Irmaos creando una combinación de luces y sombras preciosa.");
        GuardarOcio(2,"Cuzco");
        GuardarOcio(3,"Pekin");
        GuardarOcio(4,"Roma");
        GuardarOcio(5,"Petra");
        GuardarOcio(6,"Agra");
    }
}
