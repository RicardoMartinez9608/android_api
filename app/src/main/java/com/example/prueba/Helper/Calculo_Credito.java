package com.example.prueba.Helper;

import java.util.ArrayList;

public class Calculo_Credito {
    private float cuotaPrimaria;
    private float cuotaAyC;
    private float ivaCalculado;
    private float cuotaTotal;
    public ArrayList< Object > amortizacion = new ArrayList < Object > ();

    // Getter Methods

    public float getCuotaPrimaria() {
        return cuotaPrimaria;
    }

    public float getCuotaAyC() {
        return cuotaAyC;
    }

    public float getIvaCalculado() {
        return ivaCalculado;
    }

    public float getCuotaTotal() {
        return cuotaTotal;
    }

    // Setter Methods

    public void setCuotaPrimaria(float cuotaPrimaria) {
        this.cuotaPrimaria = cuotaPrimaria;
    }

    public void setCuotaAyC(float cuotaAyC) {
        this.cuotaAyC = cuotaAyC;
    }

    public void setIvaCalculado(float ivaCalculado) {
        this.ivaCalculado = ivaCalculado;
    }

    public void setCuotaTotal(float cuotaTotal) {
        this.cuotaTotal = cuotaTotal;
    }
}
