
package com.atenea.market.Repositorios;

import com.atenea.market.Entidades.SubRubro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRubroRepositorio extends JpaRepository<SubRubro, String>{
    
    boolean existsByRubroId(String rubroId);
    
    List<SubRubro> findByRubroId(String rubroId);
    
}
