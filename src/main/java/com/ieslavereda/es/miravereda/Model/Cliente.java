package com.ieslavereda.es.miravereda.Model;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa un cliente del sistema.
 * Contiene información personal y datos relevantes para la gestión
 * de clientes, como datos de contacto y tarjeta.
 *
 * Utiliza Lombok para generar getters y un builder para la creación de instancias.
 *
 * @author Andrii, Cristobal, Dario, Leonardo
 * @version 1.0
 */
@Getter
@Builder
public class Cliente {
    /**
     * Identificador único del cliente.
     */
    private int id;

    /**
     * Contraseña del cliente para autenticación.
     */
    private String contrasenya;

    /**
     * Nombre del cliente.
     */
    private String nombre;

    /**
     * Apellidos del cliente.
     */
    private String apellidos;

    /**
     * Dirección física del cliente.
     */
    private String domicilio;

    /**
     * Código postal asociado a la dirección del cliente.
     */
    private String cod_postal;

    /**
     * Correo electrónico del cliente.
     */
    private String email;

    /**
     * Fecha de nacimiento del cliente.
     */
    private Date fecha_nac;

    /**
     * Número de tarjeta asociado al cliente (posiblemente para pagos).
     */
    private String num_tarjeta;
}