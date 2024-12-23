
package com.atenea.market.Entidades;


public class PagoRequest {
    
    private String formaDePago;
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String cvv;
    private String nombreTitular;
    private String saldoBilletera;

    // Getters y Setters
    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getSaldoBilletera() {
        return saldoBilletera;
    }

    public void setSaldoBilletera(String saldoBilletera) {
        this.saldoBilletera = saldoBilletera;
    }
    
}
