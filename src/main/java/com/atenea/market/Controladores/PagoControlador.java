
package com.atenea.market.Controladores;

import com.atenea.market.Config.ProcesadorPago;
import com.atenea.market.Entidades.PagoRequest;
import com.atenea.market.Entidades.PagoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagoControlador {
    
    @PostMapping("/market/pago")
    public ResponseEntity<PagoResponse> procesarPago(@RequestBody PagoRequest pagoRequest) {
        try {
            // Lógica de pago aquí usando el ProcesadorPago
            ProcesadorPago.procesar(pagoRequest);
           return ResponseEntity.ok(new PagoResponse(true, "Pago procesado con éxito controlador"));
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(new PagoResponse(false, "Error en el procesamiento del pago"));
        }
    }
    


    
}
