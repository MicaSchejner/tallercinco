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
    String sqlCreateCiudades     = "CREATE TABLE ciudades(id INTEGER PRIMARY KEY , descripcion TEXT,latitud DECIMAL, longitud DECIMAL)";
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
        String selectQuery = "SELECT  * FROM ciudades";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarCiudades();
        }
        if (cursor.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3));

                ejemploList.add(ciudad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ejemploList;
    }
    public List<Ciudad> getCiudades(int id) {
        List<Ciudad> ejemploList = new ArrayList<Ciudad>();
        String selectQuery = "SELECT  * FROM ciudades WHERE id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() == 0) {
            CargarCiudades();
        }
        if (cursor.moveToFirst()) {
            do {
                Ciudad ciudad = new Ciudad(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getDouble(2),cursor.getDouble(3));

                ejemploList.add(ciudad);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ejemploList;
    }
    public void GuardarCiudad (int id, String descrip, double latitud, double longitud){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("id", id);
        contenedor.put("descripcion", descrip);
        contenedor.put("latitud", latitud);
        contenedor.put("longitud", longitud);
        database.insertOrThrow("ciudades",null, contenedor);
    }
    public void CargarCiudades(){
        GuardarCiudad(1,"Rio De Janeiro",-22, -43);
        GuardarCiudad(2,"Cuzco",-13,-71);
        GuardarCiudad(3,"Pekin",39,116);
        GuardarCiudad(4,"Roma",41,12);
        GuardarCiudad(5,"Petra",30,35);
        GuardarCiudad(6,"Agra",27,78);
    }

    public List<String> getActividades(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_actividades WHERE id = " + id;
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
        GuardarActividades(2,"Cusco es uno de los principales destinos del mundo, es una ciudad enclavada majestuosamente en medio de un gigantesco valle alimentado por dos ríos; con un diseño urbanístico único, pues fue diseñada por los incas con la forma de un puma. Muchos de los mejores tesoros arquitectónicos de la ciudad, se pueden encontrar caminando por el centro histórico del Cusco; a través de estrechas calles con muros incas de piedra, sobre los cuales están construidos templos y residencias de la época de la colonia; ocasionando una fusión arquitectónica única, entre las culturas de los conquistadores (españoles) y los conquistados (incas). La mayoría de estos lugares se encuentran muy cerca de la Plaza de Armas del Cusco (plaza principal de la ciudad). Usted podrá recorrer fácilmente la mayoría de barrios tradicionales de esta ciudad, en medio día, y podrá cubrir la mayor parte de la ciudad en 3 o 4 días; y definitivamente lo invitamos a disfrutar de las noches del Cusco");
        GuardarActividades(3,"Pekinn");
        GuardarActividades(4,"Roma");
        GuardarActividades(5,"Petra");
        GuardarActividades(6,"Agra");
    }

    public List<String> getDormir(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_dormir WHERE id = "  + id;
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
        String selectQuery = "SELECT  * FROM ciudades_comer WHERE id = " + id;
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
        GuardarComer(2,"La gastronomía de Perú ha sido clasificada por muchos chefs expertos como una de las mejores del mundo tanto por su gran variedad de sabores y aromas, tan auténticos como antiguos, como por su riqueza alimenticia.\n" +
                "\n" +
                "La ciudad de Cusco, en especial, posee alguno de los mejores platos de los Andes Peruanos donde prevalece el uso de la papa y el maíz. Si bien es un destino muy elegido por los turistas por su historia, paisajes andinos y sus construcciones incaicas, la gastronomía se ha vuelto hoy en día parte fundamental de las atracciones turísticas.\n" +
                "\n" +
                "Los lugares habituales para comer los platos típicos son restaurantes tradicionales llamados picanterías o chicherías. Los mejores están ubicados alrededor de la Plaza de Armas y algunos ofrecen peñas, es decir, música en vivo con danzas folcklóricas.");
        GuardarComer(3,"Pekin");
        GuardarComer(4,"Roma");
        GuardarComer(5,"Petra");
        GuardarComer(6,"Agra");
    }

    public List<String> getTransporte(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_transporte WHERE id = " + id ;
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
        GuardarTransporte(2,"Desplazarse en Cusco es muy sencillo, ya que muchos de los atractivos más impresionantes de la ciudad están a poca distancia del centro histórico y de la plaza de armas del Cusco. Puede trasladarse a ellos caminando, pero no es recomendable, ya que es necesario de algún tiempo de aclimatación en la ciudad, antes de realizar esfuerzo físico, de lo contrario corre el riesgo de enfermar de Mal de altura.Una de las mejores alternativas para desplazarse dentro de Cusco es utilizar el transporte público (buses y minibuses), que ofrecen tarifas muy económicas y cubren un trayecto predeterminado. Si quiere viajar un poco más cómodo, los taxis son una buena opción, pero hay que tener en cuenta que ninguna cuenta con taxímetro y por ello las tarifas deben pactarse de antemano.");
        GuardarTransporte(3,"El crecimiento de la ciudad a partir de las reformas económicas ha convertido a Pekín en un importante nudo de transportes. La ciudad está rodeada de cinco anillos de circunvalación, nueve autopistas, once carreteras principales, diversas líneas de ferrocarril y un aeropuerto internacional.");
        GuardarTransporte(4,"Roma");
        GuardarTransporte(5,"Petra");
        GuardarTransporte(6,"Agra");
    }

    public List<String> getRecomendados(int id) {
        List<String> List = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM ciudades_recomendados WHERE id = " + id;
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
        String selectQuery = "SELECT  * FROM ciudades_ocio WHERE id = " + id;
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
