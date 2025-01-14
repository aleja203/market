
package com.atenea.market.Entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Producto {

    @Id
    private String codigo;
    private String nombre;
    private String descripcion;
    private String etiqueta; // Para filtrados y búsqueda (por ejemplo, "nuevo", "orgánico".
    @Column(length = 1000) // Si está definido así, restringe el tamaño a 255
    private String especificaciones;
    private Boolean estado;
    
    @ManyToOne
    private Rubro rubro;
    @ManyToOne
    private SubRubro subRubro;
    private Double existencia;
    private BigDecimal costo;
    private Double precioVenta;
    @Temporal(TemporalType.DATE)
    private Date alta;
    private Integer cantidad;
    
    @OneToOne(cascade = CascadeType.ALL) // Propaga las operaciones de persistencia a la imagen
    @JoinColumn(name = "imagen_principal_id", referencedColumnName = "id") 
    private Imagen imagenPrincipal;
    
        
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Imagen> galeriaImagenes = new ArrayList<>();

    public Producto() {
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

    public Rubro getRubro() {
        return rubro;
    }

    public void setRubro(Rubro rubro) {
        this.rubro = rubro;
    }

    public SubRubro getSubRubro() {
        return subRubro;
    }

    public void setSubRubro(SubRubro subRubro) {
        this.subRubro = subRubro;
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

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Imagen getImagenPrincipal() {
        return imagenPrincipal;
    }

    public void setImagenPrincipal(Imagen imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    public List<Imagen> getGaleriaImagenes() {
        return galeriaImagenes;
    }

    public void setGaleriaImagenes(List<Imagen> galeriaImagenes) {
        this.galeriaImagenes = galeriaImagenes;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", etiqueta=" + etiqueta + ", especificaciones=" + especificaciones + ", estado=" + estado + ", rubro=" + rubro + ", subRubro=" + subRubro + ", existencia=" + existencia + ", costo=" + costo + ", precioVenta=" + precioVenta + ", alta=" + alta + ", cantidad=" + cantidad + ", imagenPrincipal=" + imagenPrincipal + ", galeriaImagenes=" + galeriaImagenes + '}';
    }

    
    
}
