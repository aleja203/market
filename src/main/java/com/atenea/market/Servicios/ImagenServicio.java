
package com.atenea.market.Servicios;

import com.atenea.market.Entidades.Imagen;
import com.atenea.market.Repositorios.ImagenRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenServicio {
    
    @Autowired
    private ImagenRepositorio imagenRepositorio;
    
        public Imagen guardar(Imagen imagen) {
        if (imagen == null || imagen.getContenido() == null || imagen.getContenido().length == 0) {
            throw new IllegalArgumentException("La imagen no puede ser nula o vacía.");
        }
        return imagenRepositorio.save(imagen);
    }
    
    public List<Imagen> guardarTodas(List<Imagen> imagenesGaleria) {
        // Código para guardar cada imagen, por ejemplo, usando un repositorio
        
        if (imagenesGaleria != null && !imagenesGaleria.isEmpty()) {
            System.out.println("Imágenes recibidas en el servicio:");
            for (Imagen imagen : imagenesGaleria) {
                System.out.println("Nombre: " + imagen.getNombre() + ", MIME: " + imagen.getMime() + ", Tamaño: " + (imagen.getContenido() != null ? imagen.getContenido().length : 0) + " bytes");
            }
        } else {
            System.out.println("No se recibieron imágenes en el servicio.");
        }
        
    List<Imagen> imagenesGuardadas = imagenRepositorio.saveAll(imagenesGaleria);
    
    // Verificar si las imágenes se han guardado realmente
    if (imagenesGuardadas != null && !imagenesGuardadas.isEmpty()) {
        System.out.println("Imágenes guardadas en la base de datos:");
        for (Imagen imagen : imagenesGuardadas) {
            System.out.println("ID: " + imagen.getId() + ", Nombre: " + imagen.getNombre());
        }
    } else {
        System.out.println("No se guardaron imágenes en la base de datos.");
    }
    
    return imagenesGuardadas;


//        return imagenRepositorio.saveAll(imagenesGaleria);
    }
        
}
