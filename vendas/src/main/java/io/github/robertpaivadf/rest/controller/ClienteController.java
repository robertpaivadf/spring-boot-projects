package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    @DeleteMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity deleteCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente = repCliente.findById(id);
        if(cliente.isPresent()){
            repCliente.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PutMapping("/clientes/{id}")
    @ResponseBody
    public ResponseEntity atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        return repCliente
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repCliente.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/clientes")
    public ResponseEntity findCliente ( Cliente filtro){ //pode passar as propriedades nome, cpf... na URL que ele assume como obj java TOP

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = repCliente.findAll(example);
        return ResponseEntity.ok(lista);

    }



}
