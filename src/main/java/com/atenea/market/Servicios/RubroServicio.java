
package com.atenea.market.Servicios;

import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Repositorios.RubroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroServicio {

    @Autowired
    private RubroRepositorio rubroRepositorio;

    // Guarda un nuevo rubro
    public Rubro guardarRubro(Rubro rubro) {
        // Puedes agregar validaciones aquí si es necesario
        if (rubro.getNombre() == null || rubro.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del rubro no puede estar vacío");
        }
        return rubroRepositorio.save(rubro); // Guarda el rubro en la base de datos
    }
    public Optional<Rubro> buscarPorId(String id) {
        return rubroRepositorio.findById(id);
    }

    // Obtiene todos los rubros
    public List<Rubro> obtenerTodos() {
        return rubroRepositorio.findAll();
    }
}
