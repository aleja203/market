
package com.atenea.market.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoDTO {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String etiqueta;
    private String especificaciones;
    private Boolean estado;
    private String rubroId;
    private String subRubroId;
    private Double existencia;
    private BigDecimal costo;
    private Double precioVenta;
    private Integer cantidad;
  
    private String imagenPrincipal;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
    @JsonProperty("galeria_imagenes")
    private List<String> galeriaImagenes;

        public ProductoDTO() {
    }

    public ProductoDTO(String codigo, String nombre, String descripcion, String etiqueta, String especificaciones, Boolean estado, String rubroId, String subRubroId, Double existencia, BigDecimal costo, Double precioVenta, Integer cantidad, String imagenPrincipal, List<String> galeriaImagenes) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.etiqueta = etiqueta;
        this.especificaciones = especificaciones;
        this.estado = estado;
        this.rubroId = rubroId;
        this.subRubroId = subRubroId;
        this.existencia = existencia;
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.cantidad = cantidad;
        this.imagenPrincipal = imagenPrincipal;
        this.galeriaImagenes = galeriaImagenes;
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

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(String imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<String> getGaleriaImagenes() {
        return galeriaImagenes;
    }

    public void setGaleriaImagenes(List<String> galeriaImagenes) {
        this.galeriaImagenes = galeriaImagenes;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }



    
    
}
