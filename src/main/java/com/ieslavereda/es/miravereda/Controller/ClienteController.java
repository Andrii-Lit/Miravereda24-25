package com.ieslavereda.es.miravereda.Controller;

import com.ieslavereda.es.miravereda.Model.Cliente;
import com.ieslavereda.es.miravereda.Model.Credenciales;
import com.ieslavereda.es.miravereda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClienteController extends BaseController {
    @Autowired
    private ClienteService service;

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?>  getCliente(@PathVariable int id) {

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
        @PostMapping("/cliente/")
        public ResponseEntity<?> addCliente(@RequestBody Cliente cliente) {
            try{
                Cliente usuario = service.addCliente(cliente);
                if(cliente == null)
                    return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
            catch (SQLException e) {
                Map<String, Object> response = new HashMap<>();
                response.put("code", e.getErrorCode());
                response.put("message", e.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id) {
        try{
            Cliente cliente = service.deleteCliente(id);
            if(cliente==null)
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/cliente/")
    public ResponseEntity<?> getAllClientes() {
        try{
            return new ResponseEntity<>(service.getAllClientes(),HttpStatus.OK);
        }  catch (SQLException e){
            Map<String,Object> response = new HashMap<>();
            response.put("code",e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("/login/")
    public ResponseEntity<?> login(@RequestBody Credenciales credenciales) {
        try{
            Cliente cliente = service.login(credenciales.getEmail(), credenciales.getContrasenya());
            if(cliente == null)
                return new ResponseEntity<>("USER NOT FOUND", HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        catch (SQLException e){
            Map<String, Object> response = new HashMap<>();
            response.put("code", e.getErrorCode());
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }



