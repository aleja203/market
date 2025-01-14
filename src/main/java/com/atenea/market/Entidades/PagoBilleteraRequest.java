
package com.atenea.market.Entidades;

import com.atenea.market.DTO.ProductoDTO;
import java.math.BigDecimal;
import java.util.List;

public class PagoBilleteraRequest {

    private List<ProductoDTO> productos;
    private BigDecimal saldoDisponible;

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }


    
}
