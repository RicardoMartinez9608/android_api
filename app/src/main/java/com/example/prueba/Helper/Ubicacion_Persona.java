package com.example.prueba.Helper;

public class Ubicacion_Persona {
    private float id_persona;
    private String tipo_ubicacion;
    private String lat;
    private String longi;


    // Getter Methods

    public float getId_persona() {
        return id_persona;
    }

    public String getTipo_ubicacion() {
        return tipo_ubicacion;
    }

    public String getLat() {
        return lat;
    }

    public String getLong() {
        return longi;
    }

    // Setter Methods

    public void setId_persona(float id_persona) {
        this.id_persona = id_persona;
    }

    public void setTipo_ubicacion(String tipo_ubicacion) {
        this.tipo_ubicacion = tipo_ubicacion;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLong(String longi) {
        this.longi = longi;
    }
}
