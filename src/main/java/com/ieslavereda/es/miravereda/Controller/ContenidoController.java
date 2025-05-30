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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ContenidoController {

    @Autowired
    private ContenidoService service;

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
    @GetMapping("/carrito/{id}")
    public ResponseEntity<?> getAllCarrito(@PathVariable int id) {
        try {
            List<Contenido> carrito = service.getAllCarrito(id);
            // Devuelve una lista vacía si no hay contenido en el carrito
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





}
