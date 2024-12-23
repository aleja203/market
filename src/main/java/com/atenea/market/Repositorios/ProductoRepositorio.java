
package com.atenea.market.Repositorios;

import com.atenea.market.Entidades.Producto;
import com.atenea.market.Entidades.Rubro;
import com.atenea.market.Entidades.SubRubro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, String>{
    
Producto findByCodigo(String codigo);
    
boolean existsByRubroId(String rubroId);
boolean existsBySubRubroId(String subRubroId);

@Query("SELECT l FROM Producto l WHERE l.descripcion = :descripcion")
public Producto buscarPorDescripcion(@Param("descripcion")String descripcion);

    @Query("SELECT p.descripcion, p.existencia FROM Producto p")
    List<Object[]> obtenerExistenciasActuales();


long countByRubro(Rubro rubro);
long countBySubRubro(SubRubro subRubro);

@Query("SELECT p FROM Producto p")
List<Producto> obtenerTodos();

 @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.imagenPrincipal WHERE p.id = :id")
    Optional<Producto> obtenerProductoConImagenPrincipal(@Param("id") String id);
    
}


