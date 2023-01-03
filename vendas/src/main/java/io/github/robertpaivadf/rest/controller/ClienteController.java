package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    RepCliente repCliente;

    @GetMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = repCliente.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/clientes")
    @ResponseBody
    public ResponseEntity saveCliente(@RequestBody Cliente cliente){
            Cliente clienteSalvo = repCliente.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }


}
