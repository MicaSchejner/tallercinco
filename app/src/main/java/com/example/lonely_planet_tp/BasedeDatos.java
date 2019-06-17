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
        GuardarActividades(3,"Pekín, la enorme capital de China, tiene una historia que se remonta a 3 milenios. Aun así, es conocida tanto por su arquitectura moderna como por sus sitios antiguos, como el complejo de la Ciudad Prohibida, el palacio imperial durante las dinastías Ming y Qing. Cerca, en la enorme plaza peatonal de Tiananmén, se encuentra el mausoleo de Mao Zedong y el Museo Nacional de China, que exhibe una amplia colección de reliquias culturales.\n" +
                "Otras atracciones importantes incluyen el Palacio de Verano, el retiro de los emperadores con pabellones y jardines a lo largo del lago Kunming. El Templo del Cielo es un parque donde se encuentra el colorido Salón de Oración por la Buena Cosecha, con forma circular. El Parque Olímpico de Pekín, construido para los juegos de 2008, abarca un extenso paseo costero y el Estadio Nacional de Pekín, apodado el \"Nido de aves\" por su rejilla tramada de acero. El distrito Back Lakes cuenta con estrechas calles antiguas (hutongs) bordeadas de tiendas de moda y cafés. El barrio 798 Art District alberga innovadoras galerías de arte. Un paseo adicional popular es una excursión a una sección cercana de la Gran Muralla China.");
        GuardarActividades(4,"Roma, la capital de Italia, es una extensa ciudad cosmopolita que tiene a la vista casi 3,000 años de arte, arquitectura y cultura de influencia mundial. Las ruinas antiguas como las del Foro y el Coliseo evocan el poder del antiguo Imperio Romano. La ciudad del Vaticano, sede central de la Iglesia católica romana, cuenta con la Basílica de San Pedro y los Museos del Vaticano, que albergan obras maestras como los frescos de la Capilla Sixtina de Miguel Ángel.\n" +
                "Las Escaleras de la Plaza de España del siglo XVIII se ubican en el centro del lujoso distrito comercial de Roma, con tiendas de moda de diseñador en Via Condotti y Via Borgognona. Los visitantes arrojan monedas a la icónica fuente de Trevi de estilo barroco. En el Panteón, que data del siglo II, se encuentra la tumba de Rafael de la época renacentista. La abundante cocina romana incluye platos tradicionales, como la carbonara, la amatriciana y el saltimbocca. Las actividades nocturnas incluyen ver una de las óperas clásicas de Verdi en el Teatro dell'Opera o recorrer los bares del barrio Testaccio de clase trabajadora, que fue el distrito de la industria carnicera en la ciudad.");
        GuardarActividades(5,"Petra es una ciudad arqueológica famosa en el desierto, al sudoeste de Jordania. Fundada alrededor del 300 a. C., era la capital del Reino Nabateo. Se puede acceder a través de un estrecho cañón llamado Al Siq y contiene tumbas y templos tallados en acantilados de arenisca de color rosa, motivo por el que se la conoce como la \"Ciudad Rosa\". Su estructura más famosa probablemente sea el Al Khazneh de 45 metros de altura, un templo con una fachada y decoración al estilo griego, que se conoce como El Tesoro.");
        GuardarActividades(6,"Agra es una ciudad del estado de Uttar Pradesh en el norte de India. En ella, se encuentra el icónico Taj Mahal, un mausoleo construido para la esposa del gobernante mogol Shah Jahan, Mumtaz Mahal (quien murió durante un parto en 1631). El imponente edificio principal incluye un gran domo y complejos tallados en marfil blanco con incrustaciones de piedras preciosas. Se encuentra detrás de un estanque reflectante dentro de un patio demarcado por 4 minaretes.\n" +
                "Cerca del Taj Mahal están las murallas de ladrillo rojo de 20 m de altura del Fuerte de Agra, un gran palacio y una fortaleza mogola, que data en gran parte de los siglos XVI y XVII. Al otro lado del río Yamuna, se encuentra otra tumba sorprendente, Itimad-Ud-Daulah, que antecede al Taj Mahal por un par de años, por lo que se suele llamar \"Pequeño Taj\". Al oeste de la ciudad, se encuentra la notablemente bien conservada \"ciudad fantasma\" de Fatehpur Sikri, cuyos pabellones, barrios harén y apartamentos reales de arenisca roja datan de finales del siglo XVI, cuando fue brevemente la capital del Imperio mogol.");
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
        GuardarDormir(2,"Las mejores zonas donde alojarse en Cusco son la Plaza de Armas y alrededores y el barrio de San Blas. Ambas zonas conforman su centro histórico. Hospedarte aquí te permitirá tener a un paso los atractivos turísticos de la ciudad y situarte donde está la acción.\n" +
                "En cuanto al alojamiento en Cusco, hay que decir que los precios son realmente baratos ya que en Perú el SMI es de 229 euros. Esto permite que los precios medios para dormir en Cusco en hoteles de cuatro y cinco estrellas estén en torno a 150 euros, y que hacerlo en albergues u hostales pueda bajar hasta los 10 euros por noche.\n");
        GuardarDormir(3,"Pekín es una ciudad enorme, donde la oferta hotelera es inmensa e incluye todo tipo de alojamientos: muy lujosos con precios prohibitivos, otros de gama media con precios muy aceptables y albergues o hosteles a precios casi simbólicos.\n" +
                "\n" +
                "Como en el resto de los destinos, cuanto más te acerques el centro de la ciudad o a los principales atractivos turísticos, más te costará el alojamiento. De este modo, las zonas más caras son Wangfujing, Qianmen, y Sanlitun. Por contra, en Houhai, Xidan y Chaoyang Park podrás acceder a hospedajes algo más económicos.\n" +
                "\n" +
                "Asimismo, hay que tener en cuenta que las distancias en la ciudad son relativamente largas, por lo que tendrás que hacer uso con frecuencia del transporte público. Este un aspecto que debes valorar a la hora de elegir la zona más conveniente para dormir en Pekín.");
        GuardarDormir(4,"En general las zonas más populares para dormir en Roma entre los turistas que visitan la ciudad suelen ser su centro histórico y Termini. Otras opciones a considerar son el Vaticano, y el Trastevere, el barrio que más me gusta de la ciudad y donde no pierdo ocasión para salir a cenar o tomar una copa de noche.\n" +
                "El centro histórico de Roma cubre una amplia superficie y se podría dividir en varias zonas. Todo el área que más o menos cubren los alrededores del Panteón, la Plaza Navona y la Fontana di Trevi es lo que podríamos calificar como el centro del centro. Esta es una de las zonas más buscadas, si no la que más, para alojarse en Roma.\n");
        GuardarDormir(5,"Hospedarse en la misma Petra es, como imaginas, imposible. Pero el pueblo más cercano, Wadi Musa, posee una gran oferta hotelera, ya que se ha ido desarrollando gracias al turismo.\n" +
                "El precio de los hoteles en la zona oscila entre los 60 y los 70€ por noche, aunque hay algunos que alcanzan hasta los 150. Estos son más lujosos y ofrecen servicios más exclusivos, al igual que las villas, otra opción para quedarse allí. Las villas son grandes caseríos, con jardines y piscinas y un precio mucho más elevado que el resto de opciones que se pueden encontrar.\n" +
                "Si, por el contrario, lo que buscas es dormir barato, olvídate de dormir en un hostal o en un albergue, pues son prácticamente imposibles de encontrar. Pero sí que existe la opción de hoteles de menor categoría, en los que dormir cómodamente, e incluso algunos con la opción de tener desayuno.\n");
        GuardarDormir(6,"En Agra si no te quieres gastar demasiado dinero y deseas un alojamiento, con vistas al Taj Mahal lo tienes bastante fácil.\n" +
                "La mayoría de Guesthouse baratos están en Kaserat Bazar y algunos tienen muy buenas vistas desde algunas habitaciones (lo pagas bastante bien) o, mejor aun, desde el restaurante que suelen tener en la azotea.\n");
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
        GuardarComer(3,"Los platos chinos, sabrosos y sobradamente condimentados, suelen mezclar los sabores más dulces con los ácidos, amargos, salados y picantes.\n" +
                "\n" +
                "El componente básico de las comidas es el arroz, que suele servirse acompañado de todo tipo de verduras, carnes y pescado de los que se utiliza hasta la parte más remota. Los largos periodos de hambruna que han asolado el país a lo largo de la historia han influido en que todas las partes del animal (cartílagos, garras, entrañas, cabezas) sean aptas para preparar los condimentados platos");
        GuardarComer(4,"La gastronomía italiana es una de las más conocidas y apreciadas a nivel mundial. Una prueba de esto es que es difícil encontrar a alguien que no haya probado la pizza, los raviolis o la lasaña.\n" +
                "\n" +
                "Además de los diferentes tipos de pasta y las pizzas, Roma cuenta muchos otros platos característicos, como la bruschetta (pan tostado, aceite, ajo y sal), los panini (sándwich típico), diferentes tipos de carnes y pescados condimentados con especias (como el famoso pescado a la romana), el carpaccio, los quesos (especialmente el pecorino) y algunos embutidos (bresaola, mortadela, prosciutto y saltimbocca).");
        GuardarComer(5,"La Gastronomía de Jordania se distingue poco de la cocina de sus países vecinos Siria o el Líbano. Se trata de un país musulmán y por lo tanto los ingredientes se ven regulados por las normas del Islam. El plato nacional de Jordania es el Mansaf, aunque es frecuente ver también el musakhan o el maglouba");
        GuardarComer(6,"No te puedes ir sin probar el dulce típico de allí Petha, los puedes encontrar en multitud de tiendas en Taj Ganj. Están riquísimos, son como una especie de gelatina espesa, muy dulce y refrescante. Hay de varios sabores y una caja te cuesta alrededor de 150 rps.\n" +
                "Taj Cafe:\n" +
                "En Taj Ganj a 200m del hostal (Shanti Lodge) está el Taj Café, un restaurante familiar y acogedor donde el wifi va bastante bien para ser India. Los precios son bajos y el ambiente es muy relajado. Desde la terraza puedes ver el Tah Mahal. La comida es buena y barata, puedes desayunar tostadas, café y tortilla por 60rps. El dueño es muy amable y el chai está muy rico.\n" +
                "M.G restaurant: Está al lado del Taj Café en Taj Ganj, justo al doblar la esquina. Está adornado con leds de colores al más puro estilo navideño. La comida es muy abundante y los precios medios. El dueño es un tipo muy majo y curioso que no dudará en sentarse contigo mientras comes para hacerte preguntas hasta saciar su curiosidad.");
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
        GuardarTransporte(4,"El casco histórico tiene tráfico limitado y sólo se accede con permisos especiales. Es algo a tener en cuenta para quienes quieren alquilar auto.\n" +
                "Lo bueno de Roma es que se puede hacer todo caminando. Si bien tienen dos líneas de metro (A y B, azul y roja), no es indispensable usarlo. Para quienes quieren recorrer por encima de la tierra para poder ir viendo absolutamente todo, hay un bus turístico por 20 euros por persona.Hay que tener en cuenta que todo cierra temprano, Sobre todo en temporada baja.");
        GuardarTransporte(5,"Jordania está atravesada de norte a sur por dos carreteras, la Desert´s Highway y la King´s Highway. La Desert´s Higway es la más rápida para moverse de norte a sur, entre Ammán y Aqaba. La King´s Highway es más lenta pero mucho interesante, atravesando valles y montañas siguiendo las antiguas rutas de los cruzados y las peregrinaciones cristianas y musulmanas. A lo largo de ella hay multitud de pueblos, castillos y lugares de interés. Lo ideal es hacer la King´s a la ida y volver por la Desert´s Highway, sobre todo si queréis bucear en el mar Rojo. La King´s Higway pasa por puntos de gran altura y si habéis buceado podríais tener problemas de descompresión, al igual que cuando se coge un avión.\n" +
                "\n" +
                "Los transportes públicos locales son malos, impuntuales y calurosos. Conviene negociar transporte privado, no es caro (sobre todo si viajáis más de 2 personas) y mucho más confortable.");
        GuardarTransporte(6,"Agra no dispone de transporte público. Con todo, en los últimos años se ha implementado un servicio de autobuses que lleva a los principales lugares de la ciudad. El precio de este autobús turístico es de 500 ₹ por persona para realizar un recorrido completo por lugares como el Taj Mahal o el Fuerte de Agra.\n" +
                "Reservar un taxi que te lleve al Taj Mahal o a donde tú quieras es más caro que el autobús, ya que cuesta unas 1200 ₹. Sin embargo, se trata de una opción muy interesante, puesto que permite evitar esperas y colas para viajar con total libertad.\n");
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
        GuardarRecomendados(2,"Hay cosas que inevitablemente formarán parte de tu recorrido como las cientos de calles empedradas con historias por contar, lor mercados de artesanías, la piedra de los doce ángulos, la gran catedral y varias de las iglesias que se cruzarán en tu camino");
        GuardarRecomendados(3,"El tiempo en Pekín es más agradable a principios de otoño (septiembre/octubre), aunque los inviernos con nieve tienen mucho encanto también. A no ser que quieras ver el desfile de Año Nuevo Chino, evita que tu visita coincida con alguna fiesta nacional, ya que hasta la atracción más pequeña se llenará de turistas locales.");
        GuardarRecomendados(4,"La capital de Italia es un lugar que se puede ir durante todo el año, por lo que dependerá de tus preferencias respecto al clima y tu bolsillo.\n" +
                "Roma tiene las estaciones bien definidas. Para tener en cuenta durante el verano, cuando la temperatura promedia es de 30C, el movimiento de turistas es mayor, y los hoteles y restaurantes  se llenan y los costos se encarecen.\n" +
                "Si preferís un clima más ameno, con temperaturas de 20 grados y la ciudad un poco más tranquila recomendamos visitarla entre abril y junio o septiembre y octubre.\n" +
                "Tener en cuenta que la semana Santa y las fechas de Navidad y Año Nuevo, Roma suele ser uno de los destinos más buscados por los católicos de todo el mundo y se refleja directamente en los precios de hospedajes.\n");
        GuardarRecomendados(5,"La mayoría de las personas visitan Petra como parte de un viaje organizado en grupo. Si este es tu caso asegúrate que el viaje que compraste incluya por lo menos un día completo en Petra, es decir un mínimo de dos noches. Así podrás darte una idea general del lugar y recorrer la ruta más conocida.\n" +
                "La población más cercana se llama Wadi Musa, que significa el Valle de Moisés. Está literalmente en la entrada al parque arqueológico y hay todo tipo de opciones de hospedaje. Si viajan por su cuenta, procuren que su hotel esté lo más cerca de la entrada a la zona arqueológica.\n" +
                "Se recomienda estar 3 noches. Wadi Musa está enfocado al turismo, así que encontrarán muchas opciones de restaurantes y tiendas de recuerdos.\n" +
                "Si llegan por el día o la tarde a Wadi Musa y no tienen boleto de entrada vayan al centro de visitantes a comprarlo (hasta las 5pm-4pm en invierno), así se ahorrarán las filas del día siguiente, y si quieren entrar a primera hora lo necesitarán pues el parque abre una hora antes que la taquilla.\n");
        GuardarRecomendados(6,"La mejor ciudad para viajar hacia Agra, es Delhi. El Triángulo de Oro es un excelente punto de partida para aquellos visitantes que se encuentran viajando por primera vez en India. Esto incluye Delhi, Agra y Jaipur; la ciudad rosa. Si quieres conocer más, échale un vistazo a nuestro Itinerario sobre las actividades disponibles, para aprovechar al máximo tu estancia en Delhi\n" +
                "Si bien, no es necesario tener cada segundo del día organizado, te recomendamos planear tus actividades diarias durante tu viaje a la India con anticipación, así mismo tener reservados tus traslados y alojamiento principales previo a tu arribo. Esto significa, reservartus tickets aéreos o terrestres (tren, traslado en coche, etc…) y asegurarte de tener un hotel o Airbnb donde hospedarte.\n" +
                "Dependiendo de tu país de origen, es posible que necesites una visa para viajar a India. Puedes solicitar una visa electrónica en línea. Toma en cuenta que el costo también varía, según tu nacionalidad.\n");
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
        GuardarOcio(2,"En Cusco se puede encontrar clubes de baile para todos los gustos; se puede escuchar muchos tipos diferentes de música; electrónica, salsa, dance, hip hop, reggaeton, reggae y mucho más; la mayoría de estos locales tiene generosas happy hours.\n" +
                "Si tiene su visita Machu Picchu al día siguiente o si acaba de terminar el Camino Inca, lo más probable es que desee un lugar donde relajarse en vez de una discoteca. Una de las mejores alternativas son los hermosos bares del Cusco, con obras de arte en las paredes y hermosas decoraciones con motivos andinos. Pero si lo que quiere es un lugar con ambiente festivo, pues lo encontrará. En muchos bares encontrara juegos de mesa, naipes, mesas de billar, pequeñas bibliotecas y una de las mejores atmosferas en el ombligo del mundo. Estos son algunos muy buenos bares en Cusco.\n");
        GuardarOcio(3,"La noche de Pekín ofrece entretenimiento para todos los gustos. No solo podrás ir de fiesta y probar deliciosos tragos orientales, sino que también tendrás la opción de visitar la ópera, ver un espectáculo de artes marciales o incluso uno de acrobacias. \n" +
                "Los shows de destreza física son un clásico de China. Y durante la noche podrás disfrutar de este tipo de espectáculos en varios teatros de la ciudad.\n" +
                "Si estás buscando diversión, música y tomarte algo hasta el amanecer, el distrito de Sanlitun es tu lugar. Ubicada al noroeste de la ciudad, Sanlitun se ha convertido en la principal zona de fiesta. Allí encontrarás pubs con todo tipo de espectáculos. Destacan los bares de karaoke y los shows de pole dance.\n");
        GuardarOcio(4,"Muchos critican a Roma el no ser una ciudad con una vida nocturna muy divertida, ya que es relativamente difícil encontrar locales abiertos más allá de las 2 de la madrugada.\n" +
                "En la ciudad se llevan a cabo bastantes conciertos para todos los gustos al cabo del año. Un punto bastante activo en este sentido es la Piazza del Popolo, que alberga gran cantidad de espectáculos gratuitos que en general tienen que ver con la conmemoración de algún evento o aniversario importante.\n" +
                "Testaccio es la zona más animada hasta horas más altas de la noche de Roma en la actualidad (aunque será difícil prolongar la serata más allá de las 5). El ambiente recuerda bastante por momentos a zonas costeras tipo Benidorm – aunque eso sí, con menor porcentaje de extranjeros-. Decimos esto por el tipo de locales y música en los mismos y sobre todo por el emplazamiento, un monte alrededor del cual se encuentran los diferentes locales, con sus carteles y luces de neón. En efecto, no da la sensación de encontrarnos en una gran capital europea cuando se llega a Testaccio. Las noches de los miércoles y jueves se convierte absolutamente en zona Erasmus, organizándose un gran número de fiestas para este tipo de estudiantes.\n");
        GuardarOcio(5,"uno de los paseos más populares es Petra nocturna (Petra by night). Este romántico paseo parte al anochecer y recorre el Siq, que destaca con una iluminación a velas que resalta los tonos rosa de la roca. En la Tesorería, también iluminada con velas, se realiza un espectáculo musical con danzas de beduinos. En la actualidad, esta actividad se realiza los lunes, miércoles y jueves de cada semana");
        GuardarOcio(6,"Se puede decir que Agra es más famosa por su actividad turística que por su vida nocturna. Es cierto que esta pequeña ciudad ofrece pocas posibilidades en términos de bares.\n" +
                "Club rush Es el lugar imprescindible para las salidas en Agra. Situado en el distrito de Tajganj, es un club de renombre en la ciudad dividido en dos secciones: una parte para grupos pequeños y una sala para ocasiones especiales. Dentro del Hotel Oberoi Amarvilas, es el lugar de moda para tomar una copa mientras se escucha al DJ mezclado con los últimos sonidos del momento. Estará inmerso en un ambiente acogedor: sofás de seda, taburetes de bar a la antigua usanza\n");
    }
}
