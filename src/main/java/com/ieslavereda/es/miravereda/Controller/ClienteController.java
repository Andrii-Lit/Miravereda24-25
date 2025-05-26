package com.ieslavereda.es.miravereda.Controller;

import com.ieslavereda.es.miravereda.Model.Cliente;
import com.ieslavereda.es.miravereda.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class ClienteController {
    @Autowired
    private ClienteService userService;

    @GetMapping("/cliente/{id}")
    public Cliente getCliente(@PathVariable int id) {
        Cliente c=getCliente(id);
       try{

       }

    }


}
