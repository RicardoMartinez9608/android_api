package com.example.prueba.Helper;

import java.io.Serializable;

public class idtipoFoto implements Serializable {

    private int id_Descripcion_Documento;
    private String descripcion;


    // Getter Methods

    public int getId_Descripcion_Documento() {
        return id_Descripcion_Documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setter Methods

    public void setId_Descripcion_Documento(int id_Descripcion_Documento) {
        this.id_Descripcion_Documento = id_Descripcion_Documento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



}
