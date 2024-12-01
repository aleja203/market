
package com.atenea.market.Servicios;

import com.atenea.market.DTO.SubRubroDTO;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.Repositorios.RubroRepositorio;
import com.atenea.market.Repositorios.SubRubroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubRubroServicio {

    @Autowired
    private SubRubroRepositorio subRubroRepositorio;

    @Autowired
    private RubroRepositorio rubroRepositorio;

    // Guardar un subrubro
    public SubRubro guardarSubRubro(SubRubroDTO subRubroDTO) {
        // Verificar que el rubro exista
        Rubro rubro = rubroRepositorio.findById(subRubroDTO.getRubroId())
            .orElseThrow(() -> new RuntimeException("Rubro no encontrado"));

        SubRubro subRubro = new SubRubro();
        subRubro.setNombre(subRubroDTO.getNombre());
        subRubro.setRubro(rubro);

    
        return subRubroRepositorio.save(subRubro);
    }
    public Optional<SubRubro> buscarPorId(String id) {
        return subRubroRepositorio.findById(id);
    }

    // Obtener todos los subrubros
    public List<SubRubro> obtenerTodos() {
        return subRubroRepositorio.findAll();
    }
    
    public List<SubRubro> obtenerPorRubro(String rubroId) {
        return subRubroRepositorio.findByRubroId(rubroId);
    }
}
