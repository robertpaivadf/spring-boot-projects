package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.domain.entities.Produto;
import io.github.robertpaivadf.domain.repositories.RepPedido;
import io.github.robertpaivadf.rest.dto.PedidoDTO;
import io.github.robertpaivadf.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor //injeta as dependencias
public class PedidoController {


    private final PedidoService service;
    private final RepPedido repPedido;

//    public PedidoController(PedidoService service){
//        this.service = service;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
       Pedido pedido =  service.salvar(dto);
       return pedido.getId();
    }


    @GetMapping("{id}")
    public Pedido getPedidoById(@PathVariable Integer id) {
        return repPedido
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }


}

