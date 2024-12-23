
package com.atenea.market.Servicios;

import com.atenea.market.DTO.ProductoDTO;
import com.atenea.market.DTO.SubRubroDTO;
import com.atenea.market.Entidades.Imagen;
import com.atenea.market.Entidades.Producto;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.Repositorios.ImagenRepositorio;
import com.atenea.market.Repositorios.ProductoRepositorio;
import com.atenea.market.Repositorios.RubroRepositorio;
import com.atenea.market.Repositorios.SubRubroRepositorio;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Service;



@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
    
    public Producto guardar(Producto producto) {
        return productoRepositorio.save(producto);
    }
    

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }
    
    public Producto obtenerProductoConImagenPrincipal(String codigo) {
        return productoRepositorio.obtenerProductoConImagenPrincipal(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id));
        
        
        
    }

//    public ProductoDTO obtenerProductoConImagenes(String codigo) {
//        Producto producto = productoRepositorio.findByCodigo(codigo);
//
//        if (producto == null) {
//            throw new RuntimeException("Producto no encontrado");
//        }
//
//        // Extraer las URLs completas de las imágenes
//        List<String> urls = producto.getGaleriaImagenes().stream()
//                .map(imagen -> "http://localhost:8080/market/productos/" + producto.getCodigo() + "/imagenes/" + imagen.getId())
//                .collect(Collectors.toList());
//
//        System.out.println("ProductoDTO generado: Codigo: " + producto.getCodigo() + ", Nombre: " + producto.getNombre());
//        System.out.println("Galería de Imágenes: " + urls);
//
//        // Crear el ProductoDTO con todos los atributos relevantes de la entidad Producto
//        return new ProductoDTO(
//                producto.getCodigo(),
//                producto.getNombre(),
//                producto.getDescripcion(),
//                producto.getEtiqueta(),
//                producto.getEspecificaciones(),
//                producto.getEstado(),
//                producto.getExistencia(),
//                producto.getCosto(),
//                producto.getPrecioVenta(),
//                urls // Esta es la lista correcta de URLs
//        );
//    }

public Producto obtenerProductoConGaleria(String codigo) {
    // 1. Buscar el producto por código
    Producto producto = productoRepositorio.findByCodigo(codigo);
    System.out.println("Producto obtenido: " + producto); // Ver si el producto se obtiene correctamente
    
    if (producto != null) {
        // 2. Obtener la lista de imágenes asociadas a la galería de este producto
        List<Imagen> galeriaImagenes = imagenRepositorio.findByProductoCodigo(codigo);
        System.out.println("Imágenes obtenidas de la galería: " + galeriaImagenes.size()); // Cuántas imágenes se obtuvieron

        // 3. Definir la URL base para las imágenes
        String baseUrl = "http://localhost:8080/market/productos/" + codigo + "/galeria/";
        System.out.println("URL base generada: " + baseUrl); // Verificar si la URL base está bien formada

        // 4. Asignar la URL completa para cada imagen en la galería
        for (Imagen imagen : galeriaImagenes) {
            // Imprimir datos de la imagen antes de asignar la URL
            System.out.println("Antes de asignar URL: Imagen id=" + imagen.getId() + ", nombre=" + imagen.getNombre());
            
            String imagenUrl = baseUrl + imagen.getNombre();
            imagen.setUrl(imagenUrl); // Asignar la URL completa
            
            // Imprimir la URL asignada para la imagen
            System.out.println("Después de asignar URL: Imagen id=" + imagen.getId() + ", URL=" + imagen.getUrl());
        }

        // 5. Asignar la galería de imágenes al producto
        producto.setGaleriaImagenes(galeriaImagenes);
        System.out.println("Galería de imágenes asignada al producto: " + producto.getGaleriaImagenes()); // Ver la galería final
    } else {
        System.out.println("No se encontró el producto con el código: " + codigo);
    }

    return producto;
}


    
    
}
