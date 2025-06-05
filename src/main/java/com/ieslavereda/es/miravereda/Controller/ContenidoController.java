package com.ieslavereda.es.miravereda.Controller;

import com.ieslavereda.es.miravereda.Model.CarritoRequest;
import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST que gestiona las operaciones relacionadas con contenidos,
 * carritos de compra, votaciones y procesos de compra.
 * Proporciona endpoints para realizar operaciones CRUD sobre contenidos,
 * así como funcionalidades adicionales como añadir al carrito o votar un contenido.
 *
 * @author
 * Andrii, Cristobal, Dario, Leonardo, Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

    /**
     * Obtiene un contenido por su ID.
     *
     * @param id ID del contenido a obtener.
     * @return ResponseEntity con el contenido si se encuentra, o un mensaje de error si no existe.
     */
    @GetMapping("/contenido/{id}")
    public ResponseEntity<?> getContenido(@PathVariable int id) {
        try {
            Contenido contenido = service.getContenido(id);
            if (contenido == null) {
                return new ResponseEntity<>("CONTENIDO NOT FOUND", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(contenido, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todos los contenidos disponibles.
     *
     * @return ResponseEntity con la lista de todos los contenidos, o mensaje de error.
     */
    @GetMapping("/contenido/")
    public ResponseEntity<?> getAllContenidos() {
        try{
            return new ResponseEntity<>(service.getAllContenidos(),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Añade un nuevo contenido al sistema.
     *
     * @param contenido Objeto Contenido a añadir.
     * @return ResponseEntity con el contenido creado o mensaje de error si falla.
     */
    @PostMapping("/contenido/")
    public ResponseEntity<?> addContenido(@RequestBody Contenido contenido) {
        try {
            Contenido result = service.addContenido(contenido);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un contenido existente.
     *
     * @param contenido Objeto Contenido con la información a actualizar.
     * @return ResponseEntity con el contenido actualizado o mensaje de error si no se encuentra.
     */
    @PutMapping("/contenido/")
    public ResponseEntity<?> updateContenido(@RequestBody Contenido contenido) {
        try {
            Contenido updated = service.updateContenido(contenido);
            if (updated == null) {
                return new ResponseEntity<>("CONTENIDO NOT FOUND", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un contenido dado su ID.
     *
     * @param id ID del contenido a eliminar.
     * @return ResponseEntity con el contenido eliminado o mensaje de error si no se encuentra.
     */
    @DeleteMapping("/contenido/{id}")
    public ResponseEntity<?> deleteContenido(@PathVariable int id) {
        try {
            Contenido contenido = service.deleteContenido(id);
            if (contenido == null) {
                return new ResponseEntity<>("CONTENIDO NOT FOUND", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(contenido, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene todos los contenidos del carrito de un cliente por su ID.
     *
     * @param id ID del cliente.
     * @return ResponseEntity con la lista de contenidos del carrito.
     */
    @GetMapping("/carrito/{id}")
    public ResponseEntity<?> getAllCarrito(@PathVariable int id) {
        try {
            List<Contenido> carrito = service.getAllCarrito(id);
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            return new ResponseEntity<>(carrito, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Añade un contenido al carrito de un cliente.
     *
     * @param carritoRequest Objeto con el ID del cliente y del contenido.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @PostMapping("/carrito/")
    public ResponseEntity<?> anyadirCarrito(@RequestBody CarritoRequest carritoRequest) {
        try {
            service.anyadirCarrito(carritoRequest.getClienteId(), carritoRequest.getContenidoId());
            return new ResponseEntity<>("Contenido añadido correctamente", HttpStatus.OK);
        } catch (SQLException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Procesa la compra de todos los contenidos en el carrito de un cliente.
     *
     * @param clienteId ID del cliente que realiza la compra.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @PostMapping("/comprar/{clienteId}")
    public ResponseEntity<?> comprar(@PathVariable int clienteId) {
        try {
            service.comprar(clienteId);
            return new ResponseEntity<>("Compra realizada con éxito", HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Registra una votación por parte de un cliente sobre un contenido.
     *
     * @param request Mapa con las claves: clienteId, contenidoId, valor.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @PostMapping("/votar")
    public ResponseEntity<?> votar(@RequestBody Map<String, Object> request) {
        try {
            int clienteId = ((Number) request.get("clienteId")).intValue();
            int contenidoId = ((Number) request.get("contenidoId")).intValue();
            int valor = ((Number) request.get("valor")).intValue();

            service.votar(clienteId, contenidoId, valor);
            return ResponseEntity.ok("Votación registrada correctamente");
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Datos de entrada incorrectos o incompletos");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Elimina un contenido del carrito de un cliente.
     *
     * @param clienteId ID del cliente.
     * @param contenidoId ID del contenido a eliminar del carrito.
     * @return ResponseEntity con mensaje de éxito o error.
     */
    @DeleteMapping("/carrito/{clienteId}/{contenidoId}")
    public ResponseEntity<?> quitarProducto(@PathVariable int clienteId, @PathVariable int contenidoId) {
        try {
            service.quitarProducto(clienteId, contenidoId);
            return ResponseEntity.ok("Producto eliminado del carrito");
        } catch (SQLException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Datos incorrectos o error interno");
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}