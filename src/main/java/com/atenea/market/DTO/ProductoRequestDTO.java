
package com.atenea.market.DTO;

import java.util.List;

public class ProductoRequestDTO {

    private String codigo;
    private String nombre;
    private String descripcion;
    private String etiqueta;
    private String especificaciones;
    private Boolean estado;
    private String rubroId;
    private String subRubroId;
    private Double existencia;
    private Double costo;
    private Double precioVenta;
    private ImagenDTO imagenPrincipal;
    private List<ImagenDTO> galeriaImagenes;

    public ProductoRequestDTO() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(String especificaciones) {
        this.especificaciones = especificaciones;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getRubroId() {
        return rubroId;
    }

    public void setRubroId(String rubroId) {
        this.rubroId = rubroId;
    }

    public String getSubRubroId() {
        return subRubroId;
    }

    public void setSubRubroId(String subRubroId) {
        this.subRubroId = subRubroId;
    }

    public Double getExistencia() {
        return existencia;
    }

    public void setExistencia(Double existencia) {
        this.existencia = existencia;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public ImagenDTO getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(ImagenDTO imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<ImagenDTO> getGaleriaImagenes() {
        return galeriaImagenes;
    }

    public void setGaleriaImagenes(List<ImagenDTO> galeriaImagenes) {
        this.galeriaImagenes = galeriaImagenes;
    }
    
    
    
}
