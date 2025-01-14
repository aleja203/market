package com.atenea.market.Controladores;

import static com.atenea.market.Config.ProcesadorPago.obtenerEstadoDePago;
import com.atenea.market.Entidades.PagoRequest;
import com.atenea.market.Entidades.Producto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/mercadopago")
public class PagoControlador {
    
    private final ConcurrentHashMap<String, SseEmitter> clientes = new ConcurrentHashMap<>();

    @GetMapping("/sse/notificaciones")
    public SseEmitter notificacionesSSE() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        String clienteId = "cliente-�nico"; // Aquí generas un ID único para cada cliente
        clientes.put(clienteId, emitter);

        System.out.println("Nueva conexión SSE para cliente: " + clienteId);

        emitter.onCompletion(() -> clientes.remove(clienteId));
        emitter.onTimeout(() -> clientes.remove(clienteId));

        return emitter;
    }
       
    @PostMapping("/crear-preferencia")
    public ResponseEntity<Map<String, String>> crearPreferencia(@RequestBody PagoRequest pagoRequest) {
        try {
            // Configurar el Access Token
            MercadoPagoConfig.setAccessToken("TEST-808446729355221-122916-0b1f548047815a69110a3abfcdb49979-252234869");
            MercadoPagoConfig.setIntegratorId("dev_24c65fb163bf11ea96500242ac130004");

            // Verificación del precio de los productos
            for (Producto producto : pagoRequest.getProductos()) {
                if (producto.getPrecioVenta() <= 1) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "El precio del producto debe ser superior a US$1"));
                }
            }

            // Crear los ítems de la preferencia
            List<PreferenceItemRequest> items = new ArrayList<>();
            for (Producto producto : pagoRequest.getProductos()) {
                PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                        .id(producto.getCodigo()) // Código único del producto
                        .title(producto.getNombre()) // Nombre del producto
                        .description(producto.getDescripcion())
                        .pictureUrl("https://0ccf-152-169-102-59.ngrok-free.app/market/productos/cerradura_candex_114.jpg")
                        .quantity(producto.getCantidad()) // Cantidad
                        .currencyId("BRL") // Moneda
                        .unitPrice(new BigDecimal(producto.getPrecioVenta())) // Precio unitario
                        .build();
                items.add(itemRequest);
            }

            // Configuración de métodos de pago
            PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
                            .installments(12) // Número máximo de cuotas
                            .build();

            // Crear la preferencia con los datos necesarios, incluyendo el external_reference
            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .paymentMethods(paymentMethods)
                    .externalReference("alejandrogdiaz5@gmail.com") // Agregar external_reference aquí
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            System.out.println("Preferencia creada: ID = " + preference.getId() + ", InitPoint = " + preference.getInitPoint());

            // Verificar si los valores son válidos
            if (preference.getId() == null) {
                System.err.println("Error: La preferencia no contiene un ID válido.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Collections.singletonMap("error", "No se pudo crear una preferencia válida."));
            }

            // Crear la respuesta con el preferenceId
            Map<String, String> response = new HashMap<>();
            response.put("id", preference.getId()); // ID de la preferencia

            // Devolver el mapa con el preferenceId
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Manejo de errores con logs detallados
            e.printStackTrace(); // Imprime el stacktrace completo para depuración
            System.err.println("Error al crear la preferencia: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error al crear la preferencia: " + e.getMessage()));
        }
    }



    @PostMapping("/webhook")
    public ResponseEntity<String> recibirNotificacionWebhook(@RequestBody String payload) {
        try {
            // Procesar el payload de Mercado Pago
            System.out.println("Notificación de Webhook recibida: " + payload);
            
            // Deserializar el JSON para obtener el payment ID
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(payload);
            String paymentId = rootNode.path("data").path("id").asText();

            // Imprimir el payment ID en la consola
            System.out.println("Payment ID: " + paymentId);
            String estadoPago = obtenerEstadoDePago(payload);

            System.out.println("Estado del pago recibido: " + estadoPago);

            // Determinar la URL de redirección basada en el estado del pago
            String redireccionUrl;
            if ("approved".equals(estadoPago)) {
                redireccionUrl = "http://localhost:3000/components/success";
            } else if ("in_process".equals(estadoPago)) {
                redireccionUrl = "http://localhost:3000/components/pending";
            } else {
                redireccionUrl = "http://localhost:3000/components/failure";
            }

            System.out.println("Redirigiendo a: " + redireccionUrl);

            // Aquí se debería tener la lógica para asociar el pago con un cliente específico
            // Si solo tienes un cliente o un único pago, puedes omitir esta parte
            String clienteId = "cliente-�nico"; // Este es un ejemplo, ajusta según tu sistema

            // Obtener el emisor correspondiente para el cliente
            SseEmitter emitter = clientes.get(clienteId);

            if (emitter != null) {
                emitter.send(redireccionUrl); // Enviar la URL de redirección al cliente
                System.out.println("URL enviada a través de SSE: " + redireccionUrl);
            } else {
                System.out.println("No se encontró un cliente con ID " + clienteId + " para enviar la notificación.");
            }

            // Responder al webhook de Mercado Pago para confirmar que recibimos la notificación
            return ResponseEntity.ok("Notificación recibida correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar el webhook: " + e.getMessage());
        }
    }
}
