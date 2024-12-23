
package com.atenea.market.DTO;

import java.util.List;

public class ProductoResponseDTO {

    private Long id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String etiqueta;
    private Double precioVenta;
    private String nombreImagenPrincipal; // Solo el nombre de la imagen principal
    private List<String> nombresGaleriaImagenes; // Nombres de la galería de imágenes

    public ProductoResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getNombreImagenPrincipal() {
        return nombreImagenPrincipal;
    }

    public void setNombreImagenPrincipal(String nombreImagenPrincipal) {
        this.nombreImagenPrincipal = nombreImagenPrincipal;
    }

    public List<String> getNombresGaleriaImagenes() {
        return nombresGaleriaImagenes;
    }

    public void setNombresGaleriaImagenes(List<String> nombresGaleriaImagenes) {
        this.nombresGaleriaImagenes = nombresGaleriaImagenes;
    }
    
    
    
}
