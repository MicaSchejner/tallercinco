package com.example.lonely_planet_tp;

public class Ciudad {


    public int id;
    public String nombre;
    private double lat;
    private double lng;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad(int id, String nombre, double lat, double lng) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        // Clave!!!
        return nombre;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
