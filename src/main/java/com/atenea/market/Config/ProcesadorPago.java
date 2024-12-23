package com.atenea.market.Config;

import com.atenea.market.Entidades.PagoRequest;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import java.math.BigDecimal;

public class ProcesadorPago {

    public static String procesar(PagoRequest pagoRequest) {
        // Configurar el AccessToken de MercadoPago
        MercadoPagoConfig.setAccessToken("TEST-845569333948328-121620-b893e62e1d5a4f55eb32e604c7327a45-252234869");

        PaymentClient client = new PaymentClient();

        BigDecimal monto;
        if ("billetera".equalsIgnoreCase(pagoRequest.getFormaDePago())) {
            monto = new BigDecimal(pagoRequest.getSaldoBilletera()); // Usar saldo de billetera
        } 
        PaymentCreateRequest createRequest = 
            PaymentCreateRequest.builder()
                .token(pagoRequest.getNumeroTarjeta()) 
                .description("Pago utilizando tarjeta " + pagoRequest.getFormaDePago()) 
                .installments(1)
                .paymentMethodId(pagoRequest.getFormaDePago()) 
                .payer(PaymentPayerRequest.builder().email(pagoRequest.getNombreTitular()).build())
                .build();
        
System.out.println("Datos recibidos en PagoRequest:");
System.out.println("Número de tarjeta: " + pagoRequest.getNumeroTarjeta());
System.out.println("Forma de pago: " + pagoRequest.getFormaDePago());
System.out.println("Email del titular: " + pagoRequest.getNombreTitular());
System.out.println("Saldo de billetera: " + pagoRequest.getSaldoBilletera());
        

        try {
            Payment payment = client.create(createRequest);
            System.out.println(payment);
            return "Pago procesado con éxito: " + payment.toString();
        } catch (MPApiException ex) {
            return String.format("MercadoPago Error. Status: %s, Content: %s",
                    ex.getApiResponse().getStatusCode(), ex.getApiResponse().getContent());
        } catch (MPException ex) {
            return "Error en el procesamiento del pago: " + ex.getMessage();
        }
    }

}  // Cierre de la clase ProcesadorPago
