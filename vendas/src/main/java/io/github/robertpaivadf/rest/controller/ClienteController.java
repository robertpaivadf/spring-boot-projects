package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.domain.entities.Cliente;
import io.github.robertpaivadf.domain.repositories.RepCliente;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    @Autowired
    RepCliente repCliente;

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')") //Todos os usuários das Roles ADMIN  e USER poderão consultar um cliente
    @GetMapping("{id}")
    @ApiOperation("Obter detalhes de um cliente por id")
    @ApiResponses({
            @ApiResponse(code=200, message = "Cliente localizado"),
            @ApiResponse(code=404, message = "Cliente não Encontrado")
    })
    public Cliente getClienteById(
                                    @PathVariable
                                    @ApiParam("Id do Cliente") Integer id) {
        return repCliente
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Apenas usuários que possuem a Role ADMIN poderão incluir novo cliente
    @PostMapping
    //@ApiOperation("Incluir novo cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return repCliente.save(cliente);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Apenas usuários que possuem a Role ADMIN poderão excluir um cliente
    @DeleteMapping("{id}")
    //@ApiOperation("Excluir cliente")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repCliente.findById(id)
                .map(cliente -> {
                    repCliente.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //Apenas usuários que possuem a Role ADMIN poderão Alterar um cliente
    //@ApiOperation("Alterar cliente")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        repCliente
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    repCliente.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')") //Todos os usuários das Roles ADMIN  e USER poderão consultar clientes
    @GetMapping
    public List<Cliente> find(Cliente filtro) { //pode passar as propriedades nome, cpf... na URL que ele assume como obj java TOP

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return repCliente.findAll(example);
    }


}
