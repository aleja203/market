
package com.atenea.market.Controladores;

import com.atenea.market.DTO.RubroDTO;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Servicios.RubroServicio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market/rubros")
@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes desde el frontend
public class RubroControlador {

    @Autowired
    private RubroServicio rubroServicio;

    @PostMapping
    public ResponseEntity<Rubro> crearRubro(@RequestBody Rubro rubro) {
        Rubro nuevoRubro = rubroServicio.guardarRubro(rubro);
        return ResponseEntity.ok(nuevoRubro);
    }

@GetMapping
public ResponseEntity<List<RubroDTO>> listarRubros() {
    List<RubroDTO> rubros = rubroServicio.obtenerTodos().stream()
        .map(rubro -> new RubroDTO(rubro.getId(), rubro.getNombre()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(rubros);
}
}
