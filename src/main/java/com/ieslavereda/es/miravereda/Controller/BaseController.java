package com.ieslavereda.es.miravereda.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase base abstracta para los controladores de la aplicación.
 *
 * <p>Proporciona métodos comunes que pueden ser utilizados por
 * los controladores que hereden de esta clase, como la generación
 * de respuestas de error estandarizadas.</p>
 *
 * @author Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
public abstract class BaseController {

    /**
     * Genera una respuesta HTTP con código 500 (INTERNAL_SERVER_ERROR)
     * en caso de excepción, incluyendo información del error y su causa.
     *
     * @param e la excepción que ha sido lanzada.
     * @return un objeto {@link ResponseEntity} con un cuerpo JSON que contiene
     *         el mensaje de error y su causa.
     */
    protected ResponseEntity<?> getResposeError(Exception e) {
        Map<String, String> errors = new HashMap<>();

        // Se registra la causa de la excepción (puede ser null)
        errors.put("error", String.valueOf(e.getCause()));

        // Se registra el mensaje de la excepción
        errors.put("message", e.getMessage());

        // Se devuelve la respuesta con código 500
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}