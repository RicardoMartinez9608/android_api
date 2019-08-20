package com.example.prueba.Helper;

public class LineasCredito {
    private int id_Linea;
    private String codigo;
    private String descripcion_linea;
    private float tipo_Garantia;
    private float tasa;
    private float tasa_Mora;
    private float recargo;
    private float dia_Gracia;
    private float cancelacion_anticipada;
    private boolean planAyC_Cobros_Periodico_Dinero;
    private boolean activar_Deducciones;
    private float planAyC;
    private float plazo_Rango_Inicial;
    private float plazo_Rango_Final;
    private float monto_Rango_Inicial;
    private float monto_Rango_Final;
    private float destino_Credito;
    private String creditos = null;
    private String cobros_Credito = null;
    private String deducciones_Desembolso = null;


    // Getter Methods

    public int getId_Linea() {
        return id_Linea;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion_linea() {
        return descripcion_linea;
    }

    public float getTipo_Garantia() {
        return tipo_Garantia;
    }

    public float getTasa() {
        return tasa;
    }

    public float getTasa_Mora() {
        return tasa_Mora;
    }

    public float getRecargo() {
        return recargo;
    }

    public float getDia_Gracia() {
        return dia_Gracia;
    }

    public float getCancelacion_anticipada() {
        return cancelacion_anticipada;
    }

    public boolean getPlanAyC_Cobros_Periodico_Dinero() {
        return planAyC_Cobros_Periodico_Dinero;
    }

    public boolean getActivar_Deducciones() {
        return activar_Deducciones;
    }

    public float getPlanAyC() {
        return planAyC;
    }

    public float getPlazo_Rango_Inicial() {
        return plazo_Rango_Inicial;
    }

    public float getPlazo_Rango_Final() {
        return plazo_Rango_Final;
    }

    public float getMonto_Rango_Inicial() {
        return monto_Rango_Inicial;
    }

    public float getMonto_Rango_Final() {
        return monto_Rango_Final;
    }

    public float getDestino_Credito() {
        return destino_Credito;
    }

    public String getCreditos() {
        return creditos;
    }

    public String getCobros_Credito() {
        return cobros_Credito;
    }

    public String getDeducciones_Desembolso() {
        return deducciones_Desembolso;
    }

    // Setter Methods

    public void setId_Linea(int id_Linea) {
        this.id_Linea = id_Linea;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion_linea(String descripcion_linea) {
        this.descripcion_linea = descripcion_linea;
    }

    public void setTipo_Garantia(float tipo_Garantia) {
        this.tipo_Garantia = tipo_Garantia;
    }

    public void setTasa(float tasa) {
        this.tasa = tasa;
    }

    public void setTasa_Mora(float tasa_Mora) {
        this.tasa_Mora = tasa_Mora;
    }

    public void setRecargo(float recargo) {
        this.recargo = recargo;
    }

    public void setDia_Gracia(float dia_Gracia) {
        this.dia_Gracia = dia_Gracia;
    }

    public void setCancelacion_anticipada(float cancelacion_anticipada) {
        this.cancelacion_anticipada = cancelacion_anticipada;
    }

    public void setPlanAyC_Cobros_Periodico_Dinero(boolean planAyC_Cobros_Periodico_Dinero) {
        this.planAyC_Cobros_Periodico_Dinero = planAyC_Cobros_Periodico_Dinero;
    }

    public void setActivar_Deducciones(boolean activar_Deducciones) {
        this.activar_Deducciones = activar_Deducciones;
    }

    public void setPlanAyC(float planAyC) {
        this.planAyC = planAyC;
    }

    public void setPlazo_Rango_Inicial(float plazo_Rango_Inicial) {
        this.plazo_Rango_Inicial = plazo_Rango_Inicial;
    }

    public void setPlazo_Rango_Final(float plazo_Rango_Final) {
        this.plazo_Rango_Final = plazo_Rango_Final;
    }

    public void setMonto_Rango_Inicial(float monto_Rango_Inicial) {
        this.monto_Rango_Inicial = monto_Rango_Inicial;
    }

    public void setMonto_Rango_Final(float monto_Rango_Final) {
        this.monto_Rango_Final = monto_Rango_Final;
    }

    public void setDestino_Credito(float destino_Credito) {
        this.destino_Credito = destino_Credito;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public void setCobros_Credito(String cobros_Credito) {
        this.cobros_Credito = cobros_Credito;
    }

    public void setDeducciones_Desembolso(String deducciones_Desembolso) {
        this.deducciones_Desembolso = deducciones_Desembolso;
    }
}
