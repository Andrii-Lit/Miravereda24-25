package com.ieslavereda.es.miravereda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca la aplicación Spring Boot "Miravereda".
 *
 * <p>Esta clase contiene el método main que inicia el contexto de Spring y
 * lanza el servidor embebido para la aplicación.</p>
 *
 * <p>Autores: Andrii, Cristobal, Dario, Leonardo</p>
 *
 * @version 1.0
 */
@SpringBootApplication
public class MiraveredaApplication {

    /**
     * Método principal que inicia la ejecución de la aplicación.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SpringApplication.run(MiraveredaApplication.class, args);
    }

}