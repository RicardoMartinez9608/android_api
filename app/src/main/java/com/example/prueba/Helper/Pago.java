package com.example.prueba.Helper;

public class Pago {
    private String Codigo_Credito;
    private String Nombre_Completo;
    private Double Valor_Cuota;
    private Double Saldo_Capital;

    public Pago(String codigo_Credito, String nombre_Completo, Double valor_Cuota, Double saldo_Capital){
        Codigo_Credito=codigo_Credito;
        Nombre_Completo=nombre_Completo;
        Valor_Cuota=valor_Cuota;
        Saldo_Capital=saldo_Capital;
    }

    public String getCodigo_Credito() {
        return Codigo_Credito;
    }

    public void setCodigo_Credito(String codigo_Credito) {
        Codigo_Credito = codigo_Credito;
    }

    public String getNombre_Completo() {
        return Nombre_Completo;
    }

    public void setNombre_Completo(String nombre_Completo) {
        Nombre_Completo = nombre_Completo;
    }

    public Double getValor_Cuota() {
        return Valor_Cuota;
    }

    public void setValor_Cuota(Double valor_Cuota) {
        Valor_Cuota = valor_Cuota;
    }

    public Double getSaldo_Capital() {
        return Saldo_Capital;
    }

    public void setSaldo_Capital(Double saldo_Capital) {
        Saldo_Capital = saldo_Capital;
    }
}
