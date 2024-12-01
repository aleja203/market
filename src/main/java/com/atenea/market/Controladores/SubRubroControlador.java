    
package com.atenea.market.Controladores;

import com.atenea.market.DTO.SubRubroDTO;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.Servicios.SubRubroServicio;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/market/subrubros")
public class SubRubroControlador {

    @Autowired
    private SubRubroServicio subRubroServicio;

    @PostMapping
    public ResponseEntity<SubRubro> crearSubRubro(@RequestBody SubRubroDTO subRubroDTO) {
        SubRubro nuevoSubRubro = subRubroServicio.guardarSubRubro(subRubroDTO);
        return ResponseEntity.ok(nuevoSubRubro);
    }

//    @GetMapping
//    public ResponseEntity<List<SubRubro>> listarSubRubros() {
//        return ResponseEntity.ok(subRubroServicio.obtenerTodos());
//    }
    
    @GetMapping
public ResponseEntity<List<SubRubro>> listarSubRubros(@RequestParam(required = false) String rubroId) {
    List<SubRubro> subRubros;
    if (rubroId != null) {
        subRubros = subRubroServicio.obtenerPorRubro(rubroId);
    } else {
        subRubros = subRubroServicio.obtenerTodos();
    }
    return ResponseEntity.ok(subRubros != null ? subRubros : Collections.emptyList());
}
    
    
    
    @GetMapping("/por-rubro/{rubroId}")
    public ResponseEntity<List<SubRubro>> listarSubRubrosPorRubro(@PathVariable String rubroId) {
        List<SubRubro> subRubros = subRubroServicio.obtenerPorRubro(rubroId);
        return ResponseEntity.ok(subRubros);
    }

}
