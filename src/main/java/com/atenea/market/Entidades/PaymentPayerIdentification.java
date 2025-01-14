
package com.atenea.market.Entidades;

public class PaymentPayerIdentification {

    private String type; // Tipo de identificación (por ejemplo, DNI, CUIT, etc.)
    private String number; // Número de identificación

    // Constructor vacío (necesario para frameworks como Jackson)
    public PaymentPayerIdentification() {
    }

    // Constructor privado para el patrón Builder
    private PaymentPayerIdentification(Builder builder) {
        this.type = builder.type;
        this.number = builder.number;
    }

    // Getters y Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PaymentPayerIdentification{" +
                "type='" + type + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    // Método estático builder
    public static Builder builder() {
        return new Builder();
    }

    // Clase interna Builder
    public static class Builder {
        private String type;
        private String number;

        // Métodos para setear atributos
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        // Método para construir el objeto
        public PaymentPayerIdentification build() {
            return new PaymentPayerIdentification(this);
        }
    }
    
}
