package com.example.prueba.Helper;

public class Periodos_de_Pago {
    private int id_Periodo;
    private float valor;
    private String descripcion;
    private String creditos = null;

    public Periodos_de_Pago(int id_periodo, float Valor, String Descripcion, String Creditos){
        id_Periodo = id_periodo;
        valor = Valor;
        descripcion = Descripcion;
        creditos = Creditos;
    }

    // Getter Methods

    public int getId_Periodo() {
        return id_Periodo;
    }

    public float getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCreditos() {
        return creditos;
    }

    // Setter Methods

    public void setId_Periodo(int id_Periodo) {
        this.id_Periodo = id_Periodo;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }
}
