package com.ieslavereda.es.miravereda.Controller;

import com.ieslavereda.es.miravereda.Model.CompraCliente;
import com.ieslavereda.es.miravereda.Model.Contenido;
import com.ieslavereda.es.miravereda.Model.Credenciales;
import com.ieslavereda.es.miravereda.Service.ContenidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ieslavereda.es.miravereda.Model.Credenciales;

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

//    @PostMapping("/carrito/ver/")
//    public ResponseEntity<?> getCarrito(@RequestBody Credenciales credenciales) {
//        try {
//            List<Contenido> carrito = service.getCarrito(credenciales);
//            return new ResponseEntity<>(carrito, HttpStatus.OK);
//        } catch (SQLException e) {
//            Map<String,Object> response = new HashMap<>();
//            response.put("code", e.getErrorCode());
//            response.put("message", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @PostMapping("/carrito/")
//    public ResponseEntity<?> addCarrito(@RequestBody CompraCliente compraCliente) {
//        try {
//            int id = service.addCarrito(compraCliente);
//            return new ResponseEntity<>(id, HttpStatus.OK);
//        } catch (SQLException e) {
//            Map<String,Object> response = new HashMap<>();
//            response.put("code", e.getErrorCode());
//            response.put("message", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @DeleteMapping("/carrito/")
//    public ResponseEntity<?> deleteCarrito(@RequestBody CompraCliente compraCliente) {
//        try {
//            int id = service.deleteCarrito(compraCliente);
//            return new ResponseEntity<>(id, HttpStatus.OK);
//        } catch (SQLException e) {
//            Map<String,Object> response = new HashMap<>();
//            response.put("code", e.getErrorCode());
//            response.put("message", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
