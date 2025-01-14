
package com.atenea.market.Entidades;

import java.math.BigDecimal;
import java.util.List;


public class PagoRequest {
    
    private String formaDePago;
    private String token;
    private String fechaVencimiento;
    private String cvv;
    private String email;
    private String saldoBilletera;
    private BigDecimal montoTransaccion ;
    private String descripcion;
    private String tipoIdentificacion; // DNI, CUIL, etc.
    private String numeroIdentificacion;
    private List<Producto> productos;
    

    // Getters y Setters
    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSaldoBilletera() {
        return saldoBilletera;
    }

    public void setSaldoBilletera(String saldoBilletera) {
        this.saldoBilletera = saldoBilletera;
    }

    public BigDecimal getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(BigDecimal montotransaccion) {
        this.montoTransaccion = montotransaccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    

    @Override
    public String toString() {
        return "PagoRequest{" + "formaDePago=" + formaDePago + ", token=" + token + ", fechaVencimiento=" + fechaVencimiento + ", cvv=" + cvv + ", email=" + email + ", saldoBilletera=" + saldoBilletera + ", montoTransaccion=" + montoTransaccion + ", descripcion=" + descripcion + ", tipoIdentificacion=" + tipoIdentificacion + ", numeroIdentificacion=" + numeroIdentificacion + '}';
    }
    
}
