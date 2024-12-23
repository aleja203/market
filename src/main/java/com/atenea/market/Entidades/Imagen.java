
package com.atenea.market.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String mime;
    
    private String nombre;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @JsonIgnore
    private byte[] contenido;
    
    @ManyToOne
    @JoinColumn(name = "producto_codigo", nullable = false) // Esto asegura la clave foránea
    @JsonBackReference
    private Producto producto;
    
    public Imagen() {
    }
    
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public void setContenido(byte[] contenido) {
        this.contenido = contenido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    @JsonProperty("url")
    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

@Override
public String toString() {
    return "Imagen{" +
            "id=" + id +
            ", mime='" + mime + '\'' +
            ", nombre='" + nombre + '\'' +
            ", contenido.length=" + (contenido != null ? contenido.length : 0) +
            ", producto=" + (producto != null ? producto.getCodigo() : "null") + 
            ", url='" + url + '\'' +
            '}';
}



   
    
}
