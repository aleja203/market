
package com.atenea.market.Servicios;

import com.atenea.market.DTO.SubRubroDTO;
import com.atenea.market.Entidades.Producto;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import com.atenea.market.Repositorios.ProductoRepositorio;
import com.atenea.market.Repositorios.RubroRepositorio;
import com.atenea.market.Repositorios.SubRubroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public Producto guardar(Producto producto) {
        return productoRepositorio.save(producto);
    }
    

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }
}
