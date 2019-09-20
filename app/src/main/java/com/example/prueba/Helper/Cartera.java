package com.example.prueba.Helper;

public class Cartera {
    private int id_Cartera;
    private String id_Usuario;
    private String colector;
    private String nombre;


    // Getter Methods

    public int getId_Cartera() {
        return id_Cartera;
    }

    public String getId_Usuario() {
        return id_Usuario;
    }

    public String getColector() {
        return colector;
    }

    public String getNombre() {
        return nombre;
    }

    // Setter Methods

    public void setId_Cartera(int id_Cartera) {
        this.id_Cartera = id_Cartera;
    }

    public void setId_Usuario(String id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public void setColector(String colector) {
        this.colector = colector;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
