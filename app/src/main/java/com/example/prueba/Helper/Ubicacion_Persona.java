package com.example.prueba.Helper;

public class Ubicacion_Persona {
    private int id_Persona;
    private String tipo_Ubicacion;
    private String lat;
    private String longi;


    // Getter Methods

    public float getId_Persona() {
        return id_Persona;
    }

    public String getTipo_Ubicacion() {
        return tipo_Ubicacion;
    }

    public String getLat() {
        return lat;
    }

    public String getLong() {
        return longi;
    }

    // Setter Methods

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setTipo_Ubicacion(String tipo_Ubicacion) {
        this.tipo_Ubicacion = tipo_Ubicacion;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLong(String longi) {
        this.longi = longi;
    }
}
