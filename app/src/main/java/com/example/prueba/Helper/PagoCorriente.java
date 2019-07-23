package com.example.prueba.Helper;

import java.io.Serializable;

public class PagoCorriente implements Serializable {
    private Integer id_Persona;
    private String id_Usuario;
    private String nombre_Completo;
    private Transaccion transaccion;

    public Integer getIdPersona() {
        return id_Persona;
    }

    public void setIdPersona(Integer idPersona) {
        this.id_Persona = idPersona;
    }

    public String getIdUsuario() {
        return id_Usuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.id_Usuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombre_Completo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombre_Completo = nombreCompleto;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }
}
