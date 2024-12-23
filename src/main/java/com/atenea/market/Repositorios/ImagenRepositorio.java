
package com.atenea.market.Repositorios;

import com.atenea.market.Entidades.Imagen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String>{
    
    List<Imagen> findByProductoCodigo(String codigo);
    
}
