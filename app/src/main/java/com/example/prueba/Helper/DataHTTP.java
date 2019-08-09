package com.example.prueba.Helper;

import java.io.Serializable;

public class DataHTTP implements Serializable {
    private String Nombre;
    private String Valor;
    private String Verbo;
    private String Contenido;

    public DataHTTP() {
    }

    public DataHTTP(String nombre, String valor, String verbo, String contenido ) {
        Nombre = nombre;
        Valor = valor;
        Verbo = verbo;
        Contenido = contenido;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getValor() {
        return Valor;
    }

    public void setValor(String valor) {
        Valor = valor;
    }

    public String getVerbo() {
        return Verbo;
    }

    public void setVerbo(String verbo) {
        Verbo = verbo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

}
