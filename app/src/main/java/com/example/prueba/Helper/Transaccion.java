package com.example.prueba.Helper;

import java.io.Serializable;

public class Transaccion implements Serializable {
    private Integer id_Transaccion_Credito;
    private Integer id_Tipo_Operecion_Credito;
    private Object tipo_Operacion_Credito;
    private Integer id_Credito;
    private Object credito;
    private Integer no;
    private String fechaPago;
    private String fechaAmortizacion;
    private Double interes;
    private Double planAyC;
    private Double ivaSobrePlanAyC;
    private Integer otros_Cobros;
    private Integer abono_Capital;
    private Double valor_Cuota;
    private Double saldo_Capital;
    private Integer dias;
    private Integer recargo;
    private Boolean puntual;
    private Boolean vencido;
    private Boolean anticipado;
    private Integer pagos_Acumulado;
    private Boolean al_Dia;
    private String creado_En;
    private String eliminado_En;
    private String modificado_En;
    private String creado_Por;
    private Object eliminado_Por;
    private Object modificado_Por;
    private Boolean modificado;
    private Boolean borrado_Logico;
    private Integer pago_Para_Cancelar;

    public Integer getIdTransaccionCredito() {
        return id_Transaccion_Credito;
    }

    public void setIdTransaccionCredito(Integer idTransaccionCredito) {
        this.id_Transaccion_Credito = idTransaccionCredito;
    }

    public Integer getIdTipoOperecionCredito() {
        return id_Tipo_Operecion_Credito;
    }

    public void setIdTipoOperecionCredito(Integer idTipoOperecionCredito) {
        this.id_Tipo_Operecion_Credito = idTipoOperecionCredito;
    }

    public Object getTipoOperacionCredito() {
        return tipo_Operacion_Credito;
    }

    public void setTipoOperacionCredito(Object tipoOperacionCredito) {
        this.tipo_Operacion_Credito = tipoOperacionCredito;
    }

    public Integer getIdCredito() {
        return id_Credito;
    }

    public void setIdCredito(Integer idCredito) {
        this.id_Credito = idCredito;
    }

    public Object getCredito() {
        return credito;
    }

    public void setCredito(Object credito) {
        this.credito = credito;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaAmortizacion() {
        return fechaAmortizacion;
    }

    public void setFechaAmortizacion(String fechaAmortizacion) {
        this.fechaAmortizacion = fechaAmortizacion;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getPlanAyC() {
        return planAyC;
    }

    public void setPlanAyC(Double planAyC) {
        this.planAyC = planAyC;
    }

    public Double getIvaSobrePlanAyC() {
        return ivaSobrePlanAyC;
    }

    public void setIvaSobrePlanAyC(Double ivaSobrePlanAyC) {
        this.ivaSobrePlanAyC = ivaSobrePlanAyC;
    }

    public Integer getOtrosCobros() {
        return otros_Cobros;
    }

    public void setOtrosCobros(Integer otrosCobros) {
        this.otros_Cobros = otrosCobros;
    }

    public Integer getAbonoCapital() {
        return abono_Capital;
    }

    public void setAbonoCapital(Integer abonoCapital) {
        this.abono_Capital = abonoCapital;
    }

    public Double getValorCuota() {
        return valor_Cuota;
    }

    public void setValorCuota(Double valorCuota) {
        this.valor_Cuota = valorCuota;
    }

    public Double getSaldoCapital() {
        return saldo_Capital;
    }

    public void setSaldoCapital(Double saldoCapital) {
        this.saldo_Capital = saldoCapital;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getRecargo() {
        return recargo;
    }

    public void setRecargo(Integer recargo) {
        this.recargo = recargo;
    }

    public Boolean getPuntual() {
        return puntual;
    }

    public void setPuntual(Boolean puntual) {
        this.puntual = puntual;
    }

    public Boolean getVencido() {
        return vencido;
    }

    public void setVencido(Boolean vencido) {
        this.vencido = vencido;
    }

    public Boolean getAnticipado() {
        return anticipado;
    }

    public void setAnticipado(Boolean anticipado) {
        this.anticipado = anticipado;
    }

    public Integer getPagosAcumulado() {
        return pagos_Acumulado;
    }

    public void setPagosAcumulado(Integer pagosAcumulado) {
        this.pagos_Acumulado = pagosAcumulado;
    }

    public Boolean getAlDia() {
        return al_Dia;
    }

    public void setAlDia(Boolean alDia) {
        this.al_Dia = alDia;
    }

    public String getCreadoEn() {
        return creado_En;
    }

    public void setCreadoEn(String creadoEn) {
        this.creado_En = creadoEn;
    }

    public String getEliminadoEn() {
        return eliminado_En;
    }

    public void setEliminadoEn(String eliminadoEn) {
        this.eliminado_En = eliminadoEn;
    }

    public String getModificadoEn() {
        return modificado_En;
    }

    public void setModificadoEn(String modificadoEn) {
        this.modificado_En = modificadoEn;
    }

    public String getCreadoPor() {
        return creado_Por;
    }

    public void setCreadoPor(String creadoPor) {
        this.creado_Por = creadoPor;
    }

    public Object getEliminadoPor() {
        return eliminado_Por;
    }

    public void setEliminadoPor(Object eliminadoPor) {
        this.eliminado_Por = eliminadoPor;
    }

    public Object getModificadoPor() {
        return modificado_Por;
    }

    public void setModificadoPor(Object modificadoPor) {
        this.modificado_Por = modificadoPor;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public Boolean getBorradoLogico() {
        return borrado_Logico;
    }

    public void setBorradoLogico(Boolean borradoLogico) {
        this.borrado_Logico = borradoLogico;
    }

    public Integer getPagoParaCancelar() {
        return pago_Para_Cancelar;
    }

    public void setPagoParaCancelar(Integer pagoParaCancelar) {
        this.pago_Para_Cancelar = pagoParaCancelar;
    }
}
