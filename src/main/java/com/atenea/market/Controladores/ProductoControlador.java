
package com.atenea.market.Controladores;

import com.atenea.market.DTO.ProductoDTO;
import com.atenea.market.Entidades.Imagen;
import com.atenea.market.Entidades.Producto;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.DTO.SubRubroDTO;
import com.atenea.market.Servicios.ProductoServicio;
import com.atenea.market.Servicios.RubroServicio;
import com.atenea.market.Servicios.SubRubroServicio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/market/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private RubroServicio rubroServicio;

    @Autowired
    private SubRubroServicio subRubroServicio;

    // Endpoint para crear un producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            // Validar Rubro
            Optional<Rubro> rubroOptional = productoDTO.getRubroId() != null
                ? rubroServicio.buscarPorId(productoDTO.getRubroId())
                : Optional.empty();
            Rubro rubro = rubroOptional.orElse(null);

            // Validar SubRubro
            Optional<SubRubro> subRubroOptional = productoDTO.getSubRubroId() != null
                ? subRubroServicio.buscarPorId(productoDTO.getSubRubroId())
                : Optional.empty();
            SubRubro subRubro = subRubroOptional.orElse(null);

            // Construir el producto
            Producto producto = new Producto();
            producto.setCodigo(productoDTO.getCodigo());
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setEtiqueta(productoDTO.getEtiqueta());
            producto.setEspecificaciones(productoDTO.getEspecificaciones());
            producto.setEstado(productoDTO.getEstado() != null ? productoDTO.getEstado() : true); // Activo por defecto
            producto.setRubro(rubro);
            producto.setSubRubro(subRubro);
            producto.setExistencia(productoDTO.getExistencia() != null ? productoDTO.getExistencia() : 0.0);
            producto.setCosto(productoDTO.getCosto());
            producto.setPrecioVenta(productoDTO.getPrecioVenta());
            producto.setAlta(new Date()); // Fecha actual como alta

            // Procesar imagen principal
            if (productoDTO.getImagenPrincipal() != null) {
                Imagen imagen = new Imagen();
                imagen.setMime(productoDTO.getImagenPrincipal().getMimeType());
                imagen.setNombre(productoDTO.getImagenPrincipal().getNombre());
                imagen.setContenido(productoDTO.getImagenPrincipal().getContenido());
                producto.setImagenPrincipal(imagen);
            }

            // Guardar producto
            Producto productoCreado = productoServicio.guardar(producto);
            return ResponseEntity.ok(productoCreado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Endpoint para listar productos
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoServicio.obtenerTodos());
    }
}
