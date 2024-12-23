package com.atenea.market.Controladores;

import com.atenea.market.DTO.ProductoDTO;
import com.atenea.market.Entidades.Imagen;
import com.atenea.market.Entidades.Producto;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.Servicios.ImagenServicio;
import com.atenea.market.Servicios.ProductoServicio;
import com.atenea.market.Servicios.RubroServicio;
import com.atenea.market.Servicios.SubRubroServicio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @Autowired
    private ImagenServicio imagenServicio;

    // Endpoint para crear un producto
@PostMapping
    public ResponseEntity<?> crearProducto(
            @RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "etiqueta", required = false) String etiqueta,
            @RequestParam(value = "especificaciones", required = false) String especificaciones,
            @RequestParam(value = "estado", required = false) Boolean estado,
            @RequestParam(value = "rubroId", required = false) String rubroId,
            @RequestParam(value = "subRubroId", required = false) String subRubroId,
            @RequestParam(value = "existencia", required = false) Double existencia,
            @RequestParam(value = "costo", required = false) Double costo,
            @RequestParam(value = "precioVenta", required = false) Double precioVenta,
            @RequestParam(value = "imagenPrincipal", required = false) MultipartFile imagenPrincipal,
            @RequestParam(value = "galeriaImagenes", required = false) List<MultipartFile> galeriaImagenes) {

        if (galeriaImagenes != null && !galeriaImagenes.isEmpty()) {
            System.out.println("Galería de imágenes recibida:");
            for (MultipartFile file : galeriaImagenes) {
                System.out.println("Nombre del archivo en la galería: " + file.getOriginalFilename());
            }
        } else {
            System.out.println("No se recibieron imágenes para la galería.");
        }

        if (galeriaImagenes != null && !galeriaImagenes.isEmpty()) {
            System.out.println("Procesando imágenes de la galería...");
            for (MultipartFile file : galeriaImagenes) {
                System.out.println("Archivo recibido: " + file.getOriginalFilename());
            }
        }

        try {
            // Validar Rubro
            Optional<Rubro> rubroOptional = rubroId != null ? rubroServicio.buscarPorId(rubroId) : Optional.empty();
            Rubro rubro = rubroOptional.orElse(null);

            // Validar SubRubro
            Optional<SubRubro> subRubroOptional = subRubroId != null ? subRubroServicio.buscarPorId(subRubroId) : Optional.empty();
            SubRubro subRubro = subRubroOptional.orElse(null);

            // Crear y guardar Producto
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setEtiqueta(etiqueta);
            producto.setEspecificaciones(especificaciones);
            producto.setEstado(estado != null ? estado : true);
            producto.setRubro(rubro);
            producto.setSubRubro(subRubro);
            producto.setExistencia(existencia != null ? existencia : 0.0);
            producto.setCosto(costo);
            producto.setPrecioVenta(precioVenta);
            producto.setAlta(new Date());

            // Persistir el producto primero
            producto = productoServicio.guardar(producto);

            // Procesar Imagen Principal (si existe)
            if (imagenPrincipal != null && !imagenPrincipal.isEmpty()) {
                Imagen imagen = new Imagen();
                imagen.setMime(imagenPrincipal.getContentType());
                imagen.setNombre(imagenPrincipal.getOriginalFilename());
                imagen.setContenido(imagenPrincipal.getBytes());
                imagen.setProducto(producto); // Asociar producto ya guardado
                
                imagen = imagenServicio.guardar(imagen); // Guardar la imagen
                producto.setImagenPrincipal(imagen); // Asociar imagen al producto
            }

            // Procesar la galería de imágenes (si existe)
            if (galeriaImagenes != null && !galeriaImagenes.isEmpty()) {
                List<Imagen> imagenesGaleria = new ArrayList<>();
                for (MultipartFile file : galeriaImagenes) {
                    if (!file.isEmpty()) {
                        Imagen imagen = new Imagen();
                        imagen.setMime(file.getContentType());
                        imagen.setNombre(file.getOriginalFilename());
                        imagen.setContenido(file.getBytes());
                        imagen.setProducto(producto); // Asociar al producto
                        imagenesGaleria.add(imagen);
                    }

                    if (!imagenesGaleria.isEmpty()) {
                        System.out.println("La lista de imágenes se llenó correctamente con los siguientes elementos:");
                        for (Imagen imagen : imagenesGaleria) {
                            System.out.println("Nombre: " + imagen.getNombre()
                                    + ", MIME: " + imagen.getMime()
                                    + ", Tamaño: " + (imagen.getContenido() != null ? imagen.getContenido().length : 0) + " bytes");
                        }
                    } else {
                        System.out.println("La lista de imágenes está vacía.");
                    }

                }
                // Guardar todas las imágenes de la galería en la base de datos
                imagenServicio.guardarTodas(imagenesGaleria); // Este método debe implementarse
                producto.setGaleriaImagenes(imagenesGaleria); // Asociar las imágenes al producto
            }

            // Guardar cambios en el producto (imagen principal asociada)
            producto = productoServicio.guardar(producto);

            System.out.println("Producto creado con éxito: " + producto.getNombre() + " (ID: " + producto.getCodigo() + ")");

            return ResponseEntity.ok(producto);
//            } catch (Exception e) {
//                return ResponseEntity.badRequest().body(null);
//            }
        } catch (Exception e) {
            e.printStackTrace(); // Mostrar el error en la consola
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear el producto: " + e.getMessage());

        }

    
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoServicio.obtenerTodos());
    }

    // Endpoint para obtener la imagen principal de un producto
    @GetMapping("/{codigo}/imagen-principal")
    public ResponseEntity<byte[]> obtenerImagenPrincipal(@PathVariable String codigo) {
        try {
            // Obtener el producto con la imagen principal
            Producto producto = productoServicio.obtenerProductoConImagenPrincipal(codigo);

            // Verificar si la imagen principal existe
            Imagen imagen = producto.getImagenPrincipal();
            if (imagen == null) {
                return ResponseEntity.notFound().build();
            }

            System.out.println("Información de la imagen principal:");
            System.out.println("Nombre: " + imagen.getNombre());
            System.out.println("MIME Type: " + imagen.getMime());
            System.out.println("Tamaño de la imagen: " + (imagen.getContenido() != null ? imagen.getContenido().length : 0) + " bytes");

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(imagen.getMime())); // MIME type correcto (image/png, image/jpeg, etc.)

            // Retornar la imagen en la respuesta
            return new ResponseEntity<>(imagen.getContenido(), headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{codigo}/galeria/{nombreImagen}")
    public ResponseEntity<byte[]> obtenerImagenGaleria(@PathVariable String codigo, @PathVariable String nombreImagen) {
        try {
            // Obtener el producto con sus imágenes de galería
            Producto producto = productoServicio.obtenerProductoConGaleria(codigo);
            System.out.println("Producto obtenido controlador: " + producto);

            // Buscar la imagen por su nombre en la galería del producto
            Imagen imagenGaleria = producto.getGaleriaImagenes().stream()
                    .filter(imagen -> imagen.getNombre().equals(nombreImagen))
                    .findFirst()
                    .orElse(null);

            if (imagenGaleria == null) {
                return ResponseEntity.notFound().build(); // Si la imagen no se encuentra
            }

            // Información de la imagen
            System.out.println("Información de la imagen de la galería:");
            System.out.println("Nombre: " + imagenGaleria.getNombre());
            System.out.println("MIME Type: " + imagenGaleria.getMime());
            System.out.println("Tamaño de la imagen: " + (imagenGaleria.getContenido() != null ? imagenGaleria.getContenido().length : 0) + " bytes");

            // Configurar los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(imagenGaleria.getMime())); // MIME type correcto (image/png, image/jpeg, etc.)

            // Información adicional que se enviará al frontend
System.out.println("📦 Enviando al frontend:");
System.out.println("➡️ Código del producto: " + codigo);
System.out.println("➡️ Nombre de la imagen: " + imagenGaleria.getNombre());
System.out.println("➡️ Tamaño de la imagen: " + (imagenGaleria.getContenido() != null ? imagenGaleria.getContenido().length : 0) + " bytes");
System.out.println("➡️ Tipo MIME: " + imagenGaleria.getMime());
            
            
            // Retornar la imagen de la galería en la respuesta
            return new ResponseEntity<>(imagenGaleria.getContenido(), headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("❌ Error al obtener la imagen de la galería.");
            System.out.println("➡️ Código del producto: " + codigo);
            System.out.println("➡️ Nombre de la imagen: " + nombreImagen);
            System.out.println("➡️ Causa del error: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza completa de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
