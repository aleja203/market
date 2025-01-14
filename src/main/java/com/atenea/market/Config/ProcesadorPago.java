package com.atenea.market.Config;

import com.atenea.market.DTO.ProductoDTO;
import com.atenea.market.Entidades.PagoRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class ProcesadorPago {
  
    // Token de acceso de Mercado Pago (debe ser configurado adecuadamente)
    private static final String ACCESS_TOKEN = "TEST-808446729355221-122916-0b1f548047815a69110a3abfcdb49979-252234869";
    private static final String BASE_URL = "https://api.mercadopago.com/v1/payments/";

    public static String obtenerEstadoDePago(String payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        
        try {
            // Leer el payload como un árbol de nodos JSON
            JsonNode rootNode = objectMapper.readTree(payload);
            
            // Extraer el nodo 'data' del JSON
            JsonNode dataNode = rootNode.path("data");
            
            // Verificar si el nodo 'data' existe y tiene el campo 'id'
            if (dataNode.isMissingNode() || dataNode.path("id").isMissingNode()) {
                System.err.println("Estructura del payload inesperada: " + payload);
                throw new IllegalArgumentException("El campo 'id' no se encuentra en el payload.");
            }
            
            // Obtener el ID del pago
            String id = dataNode.path("id").asText();
            
            // Obtener el estado real del pago desde la API de Mercado Pago
            String estado = obtenerEstadoDesdeAPI(id);
            
            if (estado == null || estado.isEmpty()) {
                System.err.println("El estado del pago no se pudo obtener o está vacío para el ID: " + id);
                throw new IllegalArgumentException("El estado del pago está vacío.");
            }
            
            return estado;
            
        } catch (Exception e) {
            // Imprimir detalles del error y el payload problemático
            System.err.println("Error al deserializar el payload. Detalles:");
            System.err.println("Payload recibido: " + payload);
            e.printStackTrace();
            return null;
        }
    }

    // Método para obtener el estado del pago desde la API de Mercado Pago
    private static String obtenerEstadoDesdeAPI(String id) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(BASE_URL + id + "?access_token=" + ACCESS_TOKEN))
            .GET()
            .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Verificar si la respuesta es exitosa
        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode responseNode = objectMapper.readTree(response.body());
            
            // Extraer el campo 'status' del JSON de la respuesta
            return responseNode.path("status").asText();
        } else {
            throw new RuntimeException("Error en la solicitud a Mercado Pago: " + response.statusCode());
        }
    }
    


}
