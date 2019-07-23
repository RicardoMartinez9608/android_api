package com.example.prueba.Helper;

import java.io.Serializable;

public class DataHTTP implements Serializable {
    private String Nombre;
    private String Valor;
    private String Verbo;

    public DataHTTP() {
    }

    public DataHTTP(String nombre, String valor, String verbo) {
        Nombre = nombre;
        Valor = valor;
        Verbo = verbo;
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
}
