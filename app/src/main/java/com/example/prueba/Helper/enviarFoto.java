package com.example.prueba.Helper;

import java.io.Serializable;

public class enviarFoto implements Serializable {

    private int id_Documento;
    private int id_Persona;
    private Persona persona;
    private int id_Descripcion_Documento;
    private String descripcion;
    private String nombreArchivo;
    private String fecha;
    private String archivo_Base64;
    private boolean es_Imagen;
    private boolean es_PDF;


    // Getter Methods

    public int getId_Documento() {
        return id_Documento;
    }

    public int getId_Persona() {
        return id_Persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public int getId_Descripcion_Documento() {
        return id_Descripcion_Documento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public String getFecha() {
        return fecha;
    }

    public String getArchivo_Base64() {
        return archivo_Base64;
    }

    public boolean getEs_Imagen() {
        return es_Imagen;
    }

    public boolean getEs_PDF() {
        return es_PDF;
    }

    // Setter Methods

    public void setId_Documento(int id_Documento) {
        this.id_Documento = id_Documento;
    }

    public void setId_Persona(int id_Persona) {
        this.id_Persona = id_Persona;
    }

    public void setPersona(Persona  persona) {
        this.persona = persona;
    }

    public void setId_Descripcion_Documento(int id_Descripcion_Documento) {
        this.id_Descripcion_Documento = id_Descripcion_Documento;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setArchivo_Base64(String archivo_Base64) {
        this.archivo_Base64 = archivo_Base64;
    }

    public void setEs_Imagen(boolean es_Imagen) {
        this.es_Imagen = es_Imagen;
    }

    public void setEs_PDF(boolean es_PDF) {
        this.es_PDF = es_PDF;
    }
}
