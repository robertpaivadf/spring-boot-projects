package io.github.robertpaivadf.rest.controller;

import io.github.robertpaivadf.domain.entities.ItemPedido;
import io.github.robertpaivadf.domain.entities.Pedido;
import io.github.robertpaivadf.domain.entities.Produto;
import io.github.robertpaivadf.domain.enums.StatusPedido;
import io.github.robertpaivadf.domain.repositories.RepPedido;
import io.github.robertpaivadf.rest.dto.AtuStatusPedidoDTO;
import io.github.robertpaivadf.rest.dto.InformacoesItemPedidoDTO;
import io.github.robertpaivadf.rest.dto.InformacoesPedidoDTO;
import io.github.robertpaivadf.rest.dto.PedidoDTO;
import io.github.robertpaivadf.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor //injeta as dependencias
public class PedidoController {


    private final PedidoService service;

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
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service
                .obterPedidoCompleto(id)
                .map( p -> converter(p) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status((pedido.getStatus().name())) //name para transformar anum em string
                .itens(converterItens(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converterItens(List<ItemPedido> itensPedido) {
        if(CollectionUtils.isEmpty(itensPedido)){
            return Collections.emptyList();
        }

        return itensPedido
                .stream()
                .map(item -> InformacoesItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList()); //pega essa strem e transforma numa lista
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id,
                             @RequestBody AtuStatusPedidoDTO dto){

        String novoStatus = dto.getNovoStatus();
        service.atuStatusPedido(id, StatusPedido.valueOf(novoStatus));

    }


}

