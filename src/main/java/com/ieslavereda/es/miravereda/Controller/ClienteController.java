package com.ieslavereda.es.miravereda.Controller;

import com.ieslavereda.es.miravereda.Model.Cliente;
import com.ieslavereda.es.miravereda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST que gestiona las operaciones relacionadas con los clientes.
 *
 * <p>Incluye funcionalidades para obtener, crear, actualizar, eliminar y autenticar clientes.</p>
 *
 * @author Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class ClienteController extends BaseController {

    @Autowired
    private ClienteService service;
    @GetMapping("/cliente")
    public ResponseEntity<?> getClientePorEmail(@RequestParam String email) {
        try {
            Cliente c = service.getClientePorEmail(email);
            if (c == null) {
                return new ResponseEntity<>("Cliente Not Found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene un cliente por su identificador.
     *
     * @param id el ID del cliente.
     * @return un {@link ResponseEntity} con el cliente encontrado o mensaje de error.
     */
    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> getCliente(@PathVariable int id) {
        try {
            Cliente c = service.getCliente(id);
            if (c == null)
                return new ResponseEntity<>("Cliente Not Found", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param cliente el objeto cliente con los datos actualizados.
     * @return un {@link ResponseEntity} con el cliente actualizado o mensaje de error.
     */
    @PutMapping("/cliente")
    public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente) {
        try {
            Cliente updated = service.updateCliente(cliente);
            if (updated == null)
                return new ResponseEntity<>("CLIENTE NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Añade un nuevo cliente al sistema.
     *
     * @param cliente el nuevo cliente a registrar.
     * @return un {@link ResponseEntity} con el cliente añadido o mensaje de error.
     */
    @PostMapping("/cliente/")
    public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
        try {
            Cliente usuario = service.addCliente(cliente);
            if (usuario == null) // Corregido: se debe comprobar si "usuario" es null, no "cliente"
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un cliente dado su ID.
     *
     * @param id el identificador del cliente.
     * @return un {@link ResponseEntity} con el cliente eliminado o mensaje de error.
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        try {
            Cliente cliente = service.deleteCliente(id);
            if (cliente == null)
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene la lista de todos los clientes.
     *
     * @return un {@link ResponseEntity} con la lista de clientes o mensaje de error.
     */
    @GetMapping("/cliente/")
    public ResponseEntity<?> getAllClientes() {
        try {
            return new ResponseEntity<>(service.getAllClientes(), HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Inicia sesión de un cliente mediante correo electrónico y contraseña.
     *
     * @param credenciales objeto cliente que contiene el email y la contraseña.
     * @return un {@link ResponseEntity} con los datos del cliente o mensaje de error.
     */
    @PostMapping("/login/")
    public ResponseEntity<?> login(@RequestBody Cliente credenciales) {
        try {
            Cliente cliente = service.login(credenciales.getEmail(), credenciales.getContrasenya());
            if (cliente == null)
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}